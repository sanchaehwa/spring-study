package hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.springframework.context.annotation.ComponentScan.*;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan() {
        //컨테이너 생성
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        //Bean  Component 스캔되어 빈으로 등록
        BeanA beanA = ac.getBean("beanA",BeanA.class);
        //등록되니깐 비면 안되고 존재해야하는거고
        assertThat(beanA).isNotNull();

        //Bean Component 붙은건 스캔안하는거니깐 빈으로 등록되어 있는게 없어야 할거다 -> 예외 발생
        Assertions.assertThrows(
                NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB",BeanB.class)
        );



    }
    @Configuration
    @ComponentScan(
            //  MyIncludeComponent 붙은건 Bean Scan
            includeFilters = @Filter(type =FilterType.ANNOTATION,classes=MyIncludeComponent.class),
            // @MyExcludeComponent가 붙은 클래스 Scan
            excludeFilters = @Filter(type = FilterType.ANNOTATION,classes=MyExcludeComponent.class)

    )
    static class ComponentFilterAppConfig {

    }
}
