package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        //컨테이너 생성
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        //사용
        NetworkClient client = ac.getBean(NetworkClient.class);//생성자 호출 url : null
        //컨테이너 종료
        ac.close();

    }




    @Configuration
    static class LifeCycleConfig{
        //직접 빈 등록 (빈생성) , 빈초기화 (매서드지정)
        @Bean
        public NetworkClient networkClient(){
            //NetworkClient 빈생성
            NetworkClient networkClient = new NetworkClient();
            //의존관계 주입(setter 를 통한 url 값 설정)
            networkClient.setUrl("http://hello-spring.dev");
            //반환
            return networkClient;
        }

    }




}
