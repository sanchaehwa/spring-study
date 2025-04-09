package hello.servlet.domain.member;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//데이터 계층하고 연결되어 있는 Meber 도메인 객체 - 객체 값을 주입
public class MemberRepository {
    //동시성 문제 : 동시에 필드에 접근해서 필드값을 수정할때 생기는 문제같은걸 해결하기 위해 HashMap 대신 ConcurrentHashMap 사용
    //CocurrentHashMap 은 동기화 처리가 되어 있어서 멀티 쓰레드 환경에서 안전하게 사용이 가능
    private static Map<Long, Member> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;
    //final : 싱글톤 방식에서  객체를 공유해서 사용한다 할때 값이 변하면 안되기에 final
    private static final MemberRepository instance = new MemberRepository();
//싱글톤 인스턴스 변환
    public static MemberRepository getInstance() {
        return instance;
    }
    //외부에서 인스턴스를 생성하지 못하도록 함
    private MemberRepository() {}
    //회원 저장
    public Member save(Member member) {
        //자동 증가하는 형태
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    //회원 조회(id 값으로 찾는)
    public Member findById(Long id) {
        return store.get(id);
    }
    //회원 전체 조회(List)
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    //회원 저장소 초기화
    public void clearStore() {
        store.clear();
    }

}
