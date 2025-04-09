package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

public class StatefulServiceTest {

    @Test
    void statfulServiceSingleton() {
        //컨테이너 생성
        ApplicationContext ac = new AnnotationConfigApplicationContext(StatefulService.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        int userAprice = statefulService1.order("userA",10000);
        int userBprice = statefulService2.order("userB",20000);

        //int price = statefulService1.getPrice();
        //20000(UserA) 값이 바뀜 -> 공유 필드에 상태를 유지하는 경우
       //System.out.println("price = " + price);

//        assertThat(statefulService1.getPrice()).isEqualTo(price);
        System.out.println("userBprice = " + userBprice);
        System.out.println("userAprice = " + userAprice);
    }
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}
