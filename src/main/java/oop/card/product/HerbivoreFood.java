package oop.card.product;

import oop.card.UsableCard;
import oop.card.creature.Animals;

import oop.exceptionkerajaan.BaseException;
import oop.exceptionkerajaan.InvalidCardPlacementException;
import oop.player.Player;
import oop.card.Card;

public class HerbivoreFood extends Product implements Food, UsableCard {
    //// contructors
    public HerbivoreFood() {
        super();
    }

    // without owner
    public HerbivoreFood(String name, int price, String type, int additionalWeight) {
        super(name, price, type, additionalWeight);
    }

    // with owner
    public HerbivoreFood(String name, int price, Player owner, String type, int additionalWeight) {
        super(name, price, owner, type, additionalWeight);
    }

    public HerbivoreFood(HerbivoreFood copyFood) {
        this.name = copyFood.name;
        this.price = copyFood.price;
        this.pathToImg = copyFood.pathToImg;
        this.owner = copyFood.owner;
        this.type = copyFood.type;
        this.additionalWeight = copyFood.additionalWeight;
    }

    // other methods
    public void beEaten(Animals creature) throws BaseException {
        creature.eat(this);
    }

    @Override
    public void useCard(Card targetCard, int row, int col) throws BaseException {
        System.out.println("SADASDASD");
        if (targetCard instanceof Animals) {
            this.beEaten((Animals) targetCard);
        } else {
            throw new InvalidCardPlacementException();
        }
    }

}
