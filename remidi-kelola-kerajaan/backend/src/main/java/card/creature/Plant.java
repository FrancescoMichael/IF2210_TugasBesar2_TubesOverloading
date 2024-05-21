package card.creature;
import card.UsableCard;
import exceptionkerajaan.BaseException;
import player.Player;
import card.Card;

public class Plant extends Creature implements UsableCard {

    // constructors
    public Plant() {

    }

    public Plant(String name, int price, String pathToImg, Player owner) {
        super(name, price, pathToImg, owner);
    }

    public void useCard(Card targetCard,int row, int col) throws BaseException{
        
    }


}
