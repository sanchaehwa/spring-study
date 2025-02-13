package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

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
