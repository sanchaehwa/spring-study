package hello.core.member;
//implements 라는 키워드처럼 인터페이스에 정의된 메서드를 각 클래스의 목적에 맞게 기능을 구현하는 느낌
public interface MemberRepository {
    void save(Member member); //회원가입
    Member findById(Long memberid);
    //회원정보 찾을때
}
