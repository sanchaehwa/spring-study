package hello.core.annotation;


import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD,ElementType.PARAMETER,ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME) //런타임까지 유지되어 스프링이 실행중에도 감지할 수 있도록 설정
@Inherited //이 에너테이션을 상속받은 클래스에서도 적용할 수 있게 해줌
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
