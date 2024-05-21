package card.creature;
import card.product.CarnivoreFood;
import card.product.HerbivoreFood;
import exceptionkerajaan.*;
import player.*;
import card.*;

public class Carnivore extends Creature implements Animals, UsableCard{
    public Carnivore() {
        super();
    }

    public Carnivore(String name, int price, String pathToImg, Player owner) {
        super(name, price, pathToImg, owner);
    }
    public void eat(CarnivoreFood food) {
        System.out.println("Eating ...");
        
    }

    public void eat(HerbivoreFood food) throws BaseException {
            throw new FoodException(this);

    }

    public void useCard(Card targetCard,int row, int col) throws BaseException{
        
    }

}
