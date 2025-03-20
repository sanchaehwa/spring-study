package hello.core.discount;

import hello.core.member.Member;
public interface DiscountPolicy {
    /*
    @return 할인 대상 금액
     */
    int discount(Member member,int price);
}
/*
DiscountPolicy 인터페이스 구현 : FixDiscountPolicy , RateDiscountPolicy
DiscoutPolicy 타입의 빈이 2개가 존재
둘다 @Component로 Bean으로 등록하면 동일한 타입의 빈이 2개가 등록되는거고 그럼 스프링이 어떤 빈을 주입해야할지 모름 (Fix를 할지, Rate를 할지)
그럼 NoUniqueBeanDefinitionException 오류가 발생
 */
