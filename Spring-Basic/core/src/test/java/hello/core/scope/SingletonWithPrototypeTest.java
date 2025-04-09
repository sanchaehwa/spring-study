package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest {
    @Test
    void singletonClientUsePrototype() {
        //스프링 컨테이너 생성 (ClientBean <-싱글톤  Prototype Bean <- 새로운 인스턴스를 생성할때 호출 )
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);
        ClientBean clientBean2 = ac.getBean(ClientBean.class); //새로 생성
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1); //싱글톤으로 빈이 한번 생성되기에 재사용 2


    }
    @Component
    static class ClientBean {
        //private final PrototypeBean prototypeBean;
        private final ObjectProvider<PrototypeBean> prototypeBeanProvider; //ProtypeBean을 주입이 아니라, 생성할때마다 새로운 인스턴스를 생성해서 사용하기 위해

        @Autowired
        public ClientBean(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }
        //Provider를 사용한 경우
        //private Provider <PrototypeBean> provider;

        public int logic() {
            //새로운 인스턴스를 요청해서 Bean 객체 생성 (필요할때 새로운 요청이 들어올때만 생성 DL )
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            //PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

    }
    @Component
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;
        public void addCount() {
            count++;
        }
        public int getCount() {
            return count;
        }
        @PostConstruct //생성
        public void init() {
            System.out.println("PrototypeBean.init"); //생성은 한번 - 빈이 싱글톤 (Component)
        }
        @PreDestroy //소멸
        public void destroy() { //Type이 Prototype이라서 스프링 컨테이너가 관리는 해주지는 않고
            System.out.println("PrototypeBean.destroy");
        }
    }






}
