package hello.core.scan.filter;

//사용자 정의 애노테이션 * Service Annotation이면 Service 기능을 수행하는것처럼, 스프링에서 사용자가 정의한 기능을 수행하는 쿨래스를 해당 Annotation이 붙여서 만들면되고

//컴포넌트 스캔에서 제외할 대상
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
