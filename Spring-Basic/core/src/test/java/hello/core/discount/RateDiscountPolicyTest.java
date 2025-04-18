package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();
    @Test
    @DisplayName("VIP는 10%로 할인이 적용되어야한다")
    void vip_o() {
        //given
        Member member = new Member(1L,"memberVIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member,10000);
        //then : return 1000
        assertThat(discount).isEqualTo(1000);

    }
    @Test
    @DisplayName("VIP가 아니면 10%로 할인이 적용되지 않아야 한다")
    void vip_z() {
        //given
        Member member = new Member(2L,"memberBasic", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member,10000);
        //then : return 1000
        assertThat(discount).isEqualTo(0);

    }



}
