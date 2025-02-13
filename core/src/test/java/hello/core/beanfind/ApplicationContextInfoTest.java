package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    //ac -> Container / Appconfig에 @Bean으로 붙어있는 객체를 스프링 컨테너에 등록 (@Bean 객체를 스프링 컨테이너에 등록)
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    //getBeanDefinitionNames : bean definition 객체는 스프링 컨테이너가 관리하는 빈에 대한 메타데이터를 담고 있는 객체
    //getBeanDefinitionNames : ac에 등록되어 있는 빈 이름
    @Test //스프링 프레임워크 나 사용자가 등록된 그러니깐 ac 스프링 컨테이너가 등록된 모든 빈을 출력하는 코드
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println(beanDefinitionName+bean);
        }
    }
    @Test //사용자가 등록한 빈 (MemberService , orderService, ..)
    @DisplayName("애플리케이션에 등록되어 있는 빈 출력하기 ")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println(beanDefinitionName+bean);
            }
        }
    }
}
