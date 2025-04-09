package hello.servlet.basic.response;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

//서블릿 등록
@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")


public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK); //200

        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //최신 데이터를 제공하기 위해 : 사용자의 주문 상태, 실시간 알림, 로그인 상태 등과 같이 데이터가 자주 변하는 경우에 최신 데이터를 가져오기 위해 캐시 무효회
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header","hello");
//[Header 편의 메서드]
        content(response);
        cookie(response);
        redirect(response);
//[message body]
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
//Location: /basic/hello-form.html
//response.setStatus(HttpServletResponse.SC_FOUND); //302
//response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
//response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
//Content-Length: 2
//response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
//response.setContentLength(2); //(생략시 자동 생성)
    }
}
