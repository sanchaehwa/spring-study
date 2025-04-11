package hello.springmvc.basic;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@Slf4j
@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";
        log.trace("trace log={}", name); //개발자 디버깅 (디테일 Log)
        log.debug("debug log={}", name); //디버깅
        log.info(" info log={}", name); //일반적인 정보 로그
        log.warn(" warn log={}", name); //경고 로그
        log.error("error log={}", name); //에러 로그
//로그를 사용하지 않아도 a+b 계산 로직이 먼저 실행됨, 이런 방식으로 사용하면 X
        //-> log.debug("String concat log=" + name);
        return "ok";
    }
}
