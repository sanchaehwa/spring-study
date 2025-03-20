package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
//같은 타입의 빈이 존재하면 어떤 빈을 주입할지 결정하기 위해 구분자를 만들어주는거 "구분자" 이지 빈 이름을 바꾸는 것은 아님
//@Qualifier("mainDiscountPolicy")
//@Primary 가 붙으면 이 DiscountPolicy타입의 빈의 기본 빈은 RateDiscountPolicy 그래서 RateDiscountPolicy가 자동 주입되고 없으면 Fix가
@MainDiscountPolicy
@Component
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPercent = 10;
//할인정책
    @Override
    public int discount(Member member, int price){
        if (member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }else{ //VIP가 아닌 경우
            return 0;
        }
    }


}
