package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //설정
public class AppConfig {
    @Bean //Spring Container에 저장
    public MemberService memberService() {
        //MemberServiceImpl에 구현체 MemoryMemberRepository()를 주입시켜주는것
        return new MemberServiceImpl(memberRepository());
    }

    @Bean //@Bean을 사용하여 빈을 등록하는 방식은 팩토리 메서드 방식 : FactoryMethod *개발자가 @Bean 직접 등록 ->> DI 의존성 주입이 명확해야 하는경우 : 개발자가 명시적으로 관리
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
/*
MemberService -> new memory member repository
OrderService -> new memory member repository -
 */
