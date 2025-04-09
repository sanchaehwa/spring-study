package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;
//import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
//@RequiredArgsConstructor //final 이나 @NotNull 이 붙은 필드를 매개변수로 하는 생성자가 자동으로 만들어짐
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository ; // 회원 정보를 조회 객체
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy; // 할인 정책을 조회하는 객체

//    @Autowired //(1) 생성자를 통해 의존성을 자동으로 주입 main _< Rate
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository; //
        this.discountPolicy = discountPolicy;
    } //--> RequiredArgsConstructor가 역할을 대신해줌
//    //set
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
    //할인정책 반영
   // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
            Member member = memberRepository.findById(memberId); //Id 기반으로 회원 정책 찾고
            int discountPrice = discountPolicy.discount(member, itemPrice); //해당 회원에 맞는 할인 정책을 적용하여 할인 금액 계산
            return new Order(memberId, itemName, itemPrice, discountPrice); //Order 객체


    } //동적으로 생기는 객체 이 CreateOrder 서비스가 실행될때마다 새롭게 생성되니깐 Bean으로는 등록되는거 아니고 Bean으로 등록 된디는건 OrderServcieImpl 주입 받는건 MemberRepository , Discount poliy
    //테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
