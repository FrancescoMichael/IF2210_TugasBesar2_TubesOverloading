package oop.card.product;

import oop.card.Card;
import oop.player.*;

public class Product extends Card {
    protected String type;
    protected int price;
    protected int additionalWeight;

    // constructors
    public Product() {
        super();
    }

    public Product(String name, int price, Player owner, String type, int additionalWeight) {
        super(name, owner);
        this.type = type;
        this.additionalWeight = additionalWeight;
        this.price = price;
    }

    public Product(String name, int price, String type, int additionalWeight) {
        super(name);
        this.type = type;
        this.additionalWeight = additionalWeight;
        this.price = price;
    }

    public Product(Product copyProduct) {
        super(copyProduct.getName());
        this.type = copyProduct.getType();
        this.price = copyProduct.price;
    }

    // getters
    public String getType() {
        return this.type;
    }

    public int getAdditionalWeight() {
        return this.additionalWeight;
    }

    public int getPrice() {
        return this.price;
    }

    // setters
    public void setType(String type) {
        this.type = type;
    }

    public void setAdditionalWeight(int additionalWeight) {
        this.additionalWeight = additionalWeight;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
