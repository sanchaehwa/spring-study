package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
//HTTP Message 는 결국 네트워크를 통해 바이트 스트림으로 전송되기때문에 MVC 내부에서 InputStream으로 읽고 OutputStream 으로 쓴다
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream(); //http message - inputStream
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); //문자열로 변환
        log.info(messageBody);
        response.getWriter().write("ok");
    }
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
        String messageBody = httpEntity.getBody(); //응답을 HttpEntity 로 감싸서 보냄
        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("ok");
    }
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {
        //@RequestBody: HTTP message request body를 자동으로 읽고 String으로 바인딩(왜냐 String messagebody)
        log.info("messageBody={}", messageBody);
        return "ok";
    }
}

