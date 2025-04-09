package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class MemberServiceImpl implements MemberService {
   // memberrepository 필드 (-> 초기화)
   private final MemberRepository memberRepository ;
   //구현체 MemoryMemberRepository()는 Appconfig에서 주입시켜줌
//의존성 주입 (DI) : MemberRepository 객체를 받는걸 memberRepository 필드에 할당
    @Autowired //생성자 주입 MemberService 객체가 생성될때 MemberRepository 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //new MemoryMemberRepository 설계도를 이용해서 memberRepository를 만들고 -> 필드 (저장소)
    //객체 활용 매서드
//    public MemberServiceImpl(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//join 매서드
    @Override
    public void join(Member member){
        memberRepository.save(member);
    }
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트용

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
