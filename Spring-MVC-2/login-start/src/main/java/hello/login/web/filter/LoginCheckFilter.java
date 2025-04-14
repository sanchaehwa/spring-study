package hello.login.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import java.io.IOException;
import hello.login.web.SessionConst;

@Slf4j
public class LoginCheckFilter implements Filter {

    // 로그인 없이 접근 가능한 URI 목록
    private static final String[] whitelist = {
            "/", "/members/add", "/login", "/logout", "/css/*"
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        try {
            log.info("인증 체크 필터 시작: {}", requestURI);

            // 로그인 체크가 필요한 URI인지 확인
            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행: {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                // 세션이 없거나(SessionConst.Login_MEMBER) == null  로그인 정보가 없으면 로그인 페이지로 이동
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청: {}", requestURI);
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    return; // 인증 안됐으므로 더 이상 진행하지 않음
                }
            }

            // 다음 필터 또는 컨트롤러 호출
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e; // 예외 로깅 가능하지만 톰캣까지 예외를 보내야 함
        } finally {
            log.info("인증 체크 필터 종료: {}", requestURI);
        }
    }

    /**
     * 화이트리스트에 포함되지 않은 요청만 로그인 체크 대상
     */
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
//http://localhost:8080/login?redirectURL=/items : redirectURL 은 login 하면 items 페이지로 리다렉트
