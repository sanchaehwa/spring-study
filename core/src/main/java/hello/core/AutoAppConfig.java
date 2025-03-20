package hello.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import static org.springframework.context.annotation.ComponentScan.*;

@Configuration //자동 빈 등록
@ComponentScan( //@Component (@Service ,)
        //basePackages 라고 하면 해당 하위 패키지들만 등록되는거
        //basePackages = "hello.core.member",
        excludeFilters = @Filter(type = FilterType.ANNOTATION, value =Configuration.class))
        //Configuration Annotaiton이 붙은 클래스는 제외 Component 붙은거만
public class AutoAppConfig {
    //수동으로 Bean 등록
//        @Bean(name="memoryMemberRepository")
//        public MemberRepository memberRepository(){
//            return new MemoryMemberRepository();
//        }
}
