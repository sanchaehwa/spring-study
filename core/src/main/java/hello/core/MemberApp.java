package hello.core;


import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
       // AppConfig appConfig = new AppConfig();
      //  MemberService memberService = appConfig.memberService();
        // MemberService memberService = new MemberServiceImpl();
        //Appconfig에 저장된 Bean 객체를 스프링 컨테이너에 저장
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //ApplicationContext : 스프링 컨테이너(Appconfig.기반으로
        MemberService memberService = applicationContext.getBean("memberService",MemberServiceImpl.class);
        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member); //회원가입

        Member findMember = memberService.findMember(1L);
        //MemberId 로 회원 조회
        System.out.println(member.getName()); //memberA
        System.out.println(findMember.getName()); //memberA
    }
}
