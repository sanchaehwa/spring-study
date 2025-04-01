package hello.servlet.domain.member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.*;
class MemberRepositoryTest {
    //
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    void save() {
        //given
        Member member = new Member("hello", 20);
        //when(회원 저장)
        Member savedMember = memberRepository.save(member);
        //then (ID로 회원 조회)
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }
    //전체 조회
    @Test
    void findAll() {
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);
        List<Member> members = memberRepository.findAll();

        //then
        assertThat(members.size()).isEqualTo(2); //2개가 저장되니깐
        assertThat(members).contains(member1, member2);


    }
}
