## DispatcherServlet

DispatcherServlet : HTTP 프로토콜로 들어오는 모든 요청을 가장 먼저 받아. 적합한 컨트롤러에 위임해주는 프론트 컨트롤러라고 한다.

DispatcherServlet 가 모든 요청을 처리하다보니 이미지나 HTML / CSS / JavaScript 등과 같은 정적파일에 대한 요청마져 모두 가로채는 바람에, 정적자원을 불러오지 못하는 상황도 발생

→ 정적 자원 요청과 애플리케이션 요청을 분리함

- /apps 의 URL로 접근한다 하면 DispatcherServlet이 담당하고
- /resources 의 URL로 접근한다 하면 DispatcherServlet이 컨트롤 할 수 없기 때문에 담당하지 않는다.

→ 모든 요청에 대한 /URL를 붙여주어야 하므로 직관적인 설계가 될 수 없음 

- Dispatcher Servlet  이 요청 처리할 컨트롤러를 먼저 찾고, 요청에 대한 컨트롤러를 찾을 수 없는 경우에 2차적으로 설정된 자원 경로를 탐색하여 자원을 탐색.

### Spring Security 가 적용된 경우

- Post /Login 요청이 들어왔을때, 일반적으로 Dispatcher ervlet으로 가서 적합한 컨트롤러와 매서드를 찾아 요청을 위임해야함. 하지만 스프링 시큐리티가 적용되면 이 요청을 AuthenticationFilter 가 가로채 인증 과정을 진행함.
- AuthenticationFilter는 Request 정보를 통해 UsernamePasswordAuthenticationToken - 인증용 객체를 만듬.
- 이 인증용 객체를 AuthenticationManager에 전달. AuthenticationManager → AuthenticationProvider로 인증 위임.
인증 과정에서 필요한 사용자의 정보를 UserDetailsService 로 부터 UserDetails 객체를 넘겨받아 인증을 진행함. (사용자 정보 비교)
- 인증이 Success 하면 Authentication 객체가 반환되는데, 이때 반환되면서 발급된 토큰을 헤더값에 넣어 사용자 상태를 유지시킴.

### Intercepter

1.	**요청 수신**: DispatcherServlet이 클라이언트의 요청을 받음

2.	**HandlerMapping 조회**: HandlerMapping이 요청 URL에 매핑된 컨트롤러를 찾음

3.	**Interceptor 체인 생성**: 해당 컨트롤러와 관련된 Interceptor들을 체인으로 구성.

4.	**preHandle() 실행**: Interceptor의 preHandle() 메서드가 실행되어 요청을 가로채고, 인증, 권한 검사, 로깅 등의 전처리를 수행.

5.	**컨트롤러 실행**: preHandle()에서 true를 반환하면 컨트롤러의 메서드가 실행되어 요청을 처리.

6.	**postHandle() 실행**: 컨트롤러의 처리가 완료된 후, postHandle() 메서드가 실행되어 응답에 대한 후처리를 수행.

7.	**뷰 렌더링**: postHandle() 이후에 뷰가 렌더링.

8.	**afterCompletion() 실행**: 요청 처리가 완료된 후, afterCompletion() 메서드가 실행되어 리소스 정리나 로깅 등의 작업을 수 있음.