package card.item;

import card.Card;
import card.UsableCard;
import player.Player;


public class Item extends Card implements UsableCard {

    // public void useEffect(Creature creature, int row, int col) throws BaseException;

    // Constructors
    public Item(){
        super();
    }

    public Item(String name,String pathToImg, Player owner){
        super(name, pathToImg, owner);
    }

    public Item(String name , String pathToImg){
        super(name, pathToImg);
    }

    public void useCard(Card targetCard, int row, int col){

    }

    
} 
