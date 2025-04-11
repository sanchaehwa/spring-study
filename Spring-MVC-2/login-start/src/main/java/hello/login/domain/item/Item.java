package hello.login.domain.item;

import lombok.Data;

@Data //Getter Setter 어노테이션을 따로 쓰지않고 한번에 처리할수 있도록 하는 Annotation
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
