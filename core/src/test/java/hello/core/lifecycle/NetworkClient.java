package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Setter;

@Setter
//public class NetworkClient implements InitializingBean, DisposableBean {
// InitializingBean: 빈(Bean) 초기화 작업을 위한 인터페이스
// DisposableBean: 빈(Bean) 소멸 작업을 위한 인터페이스
public class NetworkClient {


    private String url;

    /* 객체 생성 (아직 의존관계 주입이 일어나지 않음) */
    public NetworkClient() {
        System.out.println("생성자 호출 url = " + url); // 아직 의존관계 주입 전이므로 null
    }

    /* 의존관계가 주입된 후 실행될 메서드 */
    public void connect() {
        System.out.println("connect = " + url); // 의존관계 주입 후 url 사용 가능
    }

    /* 메시지를 전송하는 기능 */
    public void call(String message) {
        System.out.println("url = " + url + " message = " + message);
    }

    /* 연결 해제 작업 */
    public void disconnect() {
        System.out.println("disconnect " + url);
    }

    /* 빈(Bean) 초기화: 의존관계 주입이 끝난 후 실행됨 */
    //@Override
//    public void afterPropertiesSet() throws Exception {
//        connect();
//        call("초기화 연결");
//    }
    @PostConstruct//객체 생성되고 의존관계 끝나면 자동 초기화 매소드 호출
    public void init() {
        System.out.println(" NetworkClient");
        connect();
        call("초기화 연결");
    }

//    /* 빈(Bean) 소멸: 컨테이너 종료 시 실행됨 */
    //@Override
//    public void destroy() throws Exception {
//        disconnect();
//    }
    @PreDestroy//빈이 소멸되기전에 소멸매서드 자동 호출
    public void close() {
        System.out.println("close " + url);
        disconnect();
    }
}
