package hello.core.singleton;

public class StatefulService {
    private int price;

    public int order(String name, int price) {
        System.out.println("name + \", price = \" + price = "+name +", price = "+price);
    return price;}
    //this.price = price;

//    public int getPrice() {
//        return price;
//    }
}
