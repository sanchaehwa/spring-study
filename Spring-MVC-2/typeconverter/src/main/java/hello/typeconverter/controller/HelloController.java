package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {

        String data = request.getParameter("data"); //문자 타입으로 조회
        Integer intValue = Integer.valueOf(data);//숫자 타입으로 변환 (HTTP 요청은 모두 문자로 처리 그래서 타입 변환 필요)

        System.out.println("intValue = " + intValue);

        return "ok";
    }
    @GetMapping("/hello-v2")
    public String heeloV2(@RequestParam Integer data) {
        System.out.println("data = " + data);
        return "ok";
    }
    @GetMapping("/ip-port")
    public  String ipPort(@RequestParam IpPort ipPort) {
        System.out.println("ipPort = " + ipPort.getIp());
        System.out.println("ipPort.getPort() = " + ipPort.getPort());
        return "ok";

    }

}

