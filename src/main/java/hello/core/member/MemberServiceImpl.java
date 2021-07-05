package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component//컴포넌트 스캔으로 자동으로 빈에 등록하면 의존관계를 주입할 방법이 없기때문에 Autowired로 의존관계 주입`
public class MemberServiceImpl implements MemberService {
    //구현체가 하나밖에 없을경우 Impl을 붙이는게 관례
    private final MemberRepository memberRepository;
    //2) = new MemoryMemberRepository(); AppConfig으로 인하여 필요없어짐, DIP만족
    //1)MemoryMemberRepository에서 만든 메서드들을 이용하기 위해서
    //위의 객체선언은 MemberRepository(인터페이스)와도 의존적이고, MemmoryMemberRepository(구현체)와도 의존적이다.
    //final은 왜 붙였지? final은 상속못시키는데, 다시한번 내용 공부하자

    //3) 생성자형성 (생성자주입)
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; //AppConfig로부터 MemoryMemberRepository를 받아서 할당시켜줌
    }

    @Override
    public void join(Member member) {
    memberRepository.save(member);//new MemoryMemberRepository()에서 가져온 메서드
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //싱글톤 테스트용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

}
