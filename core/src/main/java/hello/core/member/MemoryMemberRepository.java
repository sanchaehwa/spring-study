package hello.core.member;
import org.springframework.stereotype.Component;

import java.util.*;
//저장소 구현체
//implements 는 MemberRepository (인터페이스)의 구현체
@Component
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long,Member> store = new HashMap<>(); //HashMap 대신 동시성 이슈 발생하면 CurrentHashMap
    //저장
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }
    //조회
    @Override
    public Member findById(Long memberId){
        return store.get(memberId);
    }
}
