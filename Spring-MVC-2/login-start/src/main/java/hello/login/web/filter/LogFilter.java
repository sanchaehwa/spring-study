package hello.login.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("LogFilter init");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("LogFilter doFilter");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        //사용자 구별을 위한 UUID
        String uuid = UUID.randomUUID().toString();
        try {
            log.info("Request [{}] [{}]", uuid, requestURI);
            //다음으로 거칠 필터가 있으면 filter 없으면 서블릿 실행
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("Response [{}] [{}]", uuid, requestURI);

        }
    } //Http 요청이 올때마다 Dofilter
    @Override
    public void destroy() {
        log.info("LogFilter destroy");
    }
}
