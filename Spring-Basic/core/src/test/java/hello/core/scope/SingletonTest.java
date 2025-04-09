package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {
    @Test
        public void test() {
        //Container 생성 Bean 등록
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SingletonBean.class); //SingletonBean init
        SingletonBean singletonBean1 = context.getBean(SingletonBean.class); //같은 객체로 출력되는 이유 : 스프링이 기본적으로 싱글톤으로 관리하여 동일한 인스턴스를 반환함
        SingletonBean singletonBean2 = context.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);

        context.close(); // SingletonBean Destroy

    }




    @Scope("singleton")
        static class SingletonBean {
        @PostConstruct //Singleton Bean 생성
        public void init() {
            System.out.println("SingletonBean init");
        }
        @PreDestroy //Singleton Bean 소멸
        public void destroy() {
            System.out.println("SingletonBean destroy");
        }
    }




}
