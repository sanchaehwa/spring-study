package hello.servlet.basic;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
//(1) 서블릿 등록 : /hello 경로로 요청이 들어오면 서블릿 실행
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    //HttpServletRequest 객체 : Client로 부터 URL 요청을 받는 객체
    //HttpServletResponse 객체 : Client한테 결과를 보내는 객체
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        System.out.println(" Hello Servlet");
        System.out.println("request = " + request);
        System.out.println("response = " + response);
        String username = request.getParameter("username"); //쿼리에서 데이터를 가져오는 get aotjem
        System.out.println("username = " + username);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("Hello " + username);
    }
}
