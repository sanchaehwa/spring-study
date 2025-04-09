package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import  static org.assertj.core.api.Assertions.assertThat;


public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(prototypeBean.class);
        prototypeBean  prototypeBean1 = context.getBean(prototypeBean.class);
        prototypeBean1.addCount(); //1
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        prototypeBean prototypeBean2 = context.getBean(prototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1); //False 1 새로 객체를 생성하고 초기화되니깐 기존에서 1이 추가되는건 아님

        context.close(); //destory 가 호출되진않음 왜 prototype 객체를 컨테이너가 관리해주지않기때문

    }
    @Scope("prototype")
    static class prototypeBean {
        private int count = 0;
        public void addCount() {
            count++;
        }
        public int getCount() {
            return count;
        }
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy" + this);
        }
    }
}
