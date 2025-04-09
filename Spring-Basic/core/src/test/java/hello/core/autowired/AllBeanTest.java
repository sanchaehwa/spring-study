package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        //AutoAppConfig . Application 에 등록된 Bean 객체가 ac 컨테이너 등록 , DiscountService.class
        DiscountService discountService = ac.getBean(DiscountService.class);
        //Member 객체 생성
        Member member = new Member(1L, "UserA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
        //fixDiscountpolicy 할인정책 적용
        assertThat(discountPrice).isEqualTo(1000);
        assertThat(discountService).isInstanceOf(DiscountService.class);
    }
//전략 패턴이 적용된 코드 *전략 패턴 동적으로 변경이 가능하고 확장성 있는 설계를 할수 있고 , 즉 @Component 또는 @Bean으로 각 전략을 빈으로 관리 해서 Spring의 DI 활용이 가능하다
    static class DiscountService {
    //<DisCountCode> DisCountPolicy (DisCountCode 해당하는 빈 객체)
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String ,DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies =  policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " +  policies);
        }
        public int discount(Member member,int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            System.out.println(" discountCode = " +discountCode);
            System.out.println("discountPolicy = " + discountPolicy);

            return discountPolicy.discount(member, price);
        }
    }
}
