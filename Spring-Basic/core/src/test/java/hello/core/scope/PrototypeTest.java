package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import  static org.assertj.core.api.Assertions.assertThat;
public class PrototypeTest {

    @Test
    public void testPrototype() {
        //직접 등록한 빈을 관리하는 컨테이너가 필요하기에
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = context.getBean(PrototypeBean.class); //bean1 생성
        System.out.println("bean1 = " + bean1); //소멸
        PrototypeBean bean2 = context.getBean(PrototypeBean.class); //새로운 Bean2 객체 생성 (호출할때마다 생성하는게 prototype)
        System.out.println("bean2 = " + bean2);
        PrototypeBean bean3 = context.getBean(PrototypeBean.class);
        System.out.println("bean3 = " + bean3);
        assertThat(bean1).isNotSameAs(bean2); //True
        context.close();

    }




    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct //생성
        public void init() {
            System.out.println("PrototypeBean init");
        }
        @PreDestroy //소멸
        public void destroy() {
            System.out.println("PrototypeBean destroy");
        }
    }
}
