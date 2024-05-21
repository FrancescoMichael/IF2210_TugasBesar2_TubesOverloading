package card.creature;
import card.product.CarnivoreFood;
import card.product.HerbivoreFood;
import exceptionkerajaan.BaseException;
import exceptionkerajaan.FoodException;
import player.*;
import card.*;


public class Herbivore extends Creature implements Animals{
    
    public Herbivore() {
        super();
    }

    public Herbivore(String name, String pathToImg, Player owner) {
        super(name, pathToImg, owner);
    }
    
    public void eat(CarnivoreFood food) throws BaseException{
        throw new FoodException(this);
    }

    public void eat(HerbivoreFood food) {
        System.out.println("Eating..");
        
    }

    public void useCard(Card targetCard,int row, int col) throws BaseException{
        
    }


}
