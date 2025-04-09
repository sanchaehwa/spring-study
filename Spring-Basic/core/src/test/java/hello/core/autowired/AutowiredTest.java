package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Optional;


public class AutowiredTest {

    @Test
    void  AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }
    static class TestBean {
        private Member member;
        //Bean이 없으면 매서드 자체가 호출되지않음
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }
        //빈이 없어도 호출되지만 Null로
        @Autowired(required = false)
        public void setNoBean2(@Nullable Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }
        //Optional.empty()
        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }
    }



}
