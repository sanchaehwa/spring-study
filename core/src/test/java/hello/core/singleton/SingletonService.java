package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();
        //static: SingletonService 에서 SingletonService 인스턴스는 하나만 존재하도록
        // final : 한번 객체 할당하고 변수의 값을 변경하지 못하게 (_> 싱글톤 패턴에서 공유변수로 사용할수있게 하려고 )
    //----> 미리 초기화 방식 : 항상 같은 인스턴스 유지 (Eager Initialization) : 싱글톤 패턴 보장
    public static SingletonService getInstance() {
        //getInstance 매서드를 통해 객체가 새로 생성되는 것이 아닌, 같은 인스턴스 공유
        return instance;
    }
    private SingletonService() {
//new 키워드를 사용하지 못하게 (Private) 새로운 인스턴스 만들지 못하게 막기
    }
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출 ");

    }








}
