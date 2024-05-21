package card.creature;
import card.product.CarnivoreFood;
import card.product.HerbivoreFood;
import exceptionkerajaan.BaseException;
import player.*;
import card.*;


public class Omnivore extends Creature implements Animals{
    
    public Omnivore() {
        super();
    }

    public Omnivore(String name, String pathToImg, Player owner) {
        super(name, pathToImg, owner);
    }
    
    // without owner
    public Omnivore(String name, String pathToImg) {
        super(name, pathToImg);
    }
    public void eat(CarnivoreFood food) throws BaseException{
       System.out.println("Eating...");
    }

    public void eat(HerbivoreFood food) throws BaseException{
        System.out.println("Eating..");
        
    }

    public void useCard(Card targetCard,int row, int col) throws BaseException{
        
    }


}
