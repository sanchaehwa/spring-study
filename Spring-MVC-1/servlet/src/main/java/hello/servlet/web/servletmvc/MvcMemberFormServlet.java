package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


//서블릿 등록
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // /servlet-mvc/members/new-form 요청이 들어오면 사용자에게 view new-form.jsp를 보여주게 하는데
        String viewPath = "/WEB-INF/views/new-form.jsp";
        //클라이언트가 jsp에 직접 접근해서 보는게 아니라 서블릿이 내부적으로 jsp를 호출하여 응답 생성
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //클라이언트는 URL 변하지않고 /servlet-mvc/members/new-form 그대로 유지
        dispatcher.forward(request, response);

    }
}
