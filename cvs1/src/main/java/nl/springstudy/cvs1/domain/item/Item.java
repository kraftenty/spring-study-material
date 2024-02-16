package nl.springstudy.cvs1.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {

    private Long id;
    private String name;
    private Integer price;

    public Item() {

    }

    public Item(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

}
