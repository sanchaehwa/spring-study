package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


//서블릿 등록
@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")

public class RequestBodyJsonServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletInputStream inputStream = request.getInputStream();
        //Json 형태의 데이터를 String 형식으로 변환
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = " + messageBody);
        //  JSON 문자열을 HelloData 객체로 변환 (매핑)
        //HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("helloData.Username() = " + helloData.getUsername());
        System.out.println("helloData.age"+ helloData.getAge());

        response.getWriter().write("ok");
    }


}
