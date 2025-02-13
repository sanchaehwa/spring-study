package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {
    //컨테이너 생성
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    void findApplicationBean() {
        //ac에 등록된 빈의 정보를 모두 다 가져오고
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //각 Bean의 Metadata
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            //Application은 개발자가 등록한 빈만 출력하는 방식
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println(beanDefinitionName+beanDefinition);
            }
        }
    }
}
