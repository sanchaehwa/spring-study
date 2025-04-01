package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
//서블릿 기반의 MVC 패턴
public class MemberFormControllerV1 implements ControllerV1 {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response ) throws ServletException , IOException {
        //이 인터페이스를 구현하는 구현체 (클래스)가 동일한 형식으로 매서드를 구현해야함.
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
        //URL은 변하지않음 forward라서
    }
}
