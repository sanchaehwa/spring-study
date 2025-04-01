package hello.servlet.basic.request;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("전체 파라미터 조회");
    // 여러 개의 key-value를 가져오기 위해 사용
    // getParameterNames()는 Enumeration만 지원하기 때문에
    // asIterator() + forEachRemaining()을 사용해서 key를 순회하며
    // getParameter(paramName)으로 해당 key의 첫 번째 value를 가져옴        r
        request.getParameterNames().asIterator().forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println(" 단일 파라미터 조회");
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        String age = request.getParameter("age");
        System.out.println("age = " + age);
        System.out.println();

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        System.out.println("request.getParameterValues(username)");
        //배열로 조회
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println(name);
        }
        response.getWriter().write("ok");

    }

}
