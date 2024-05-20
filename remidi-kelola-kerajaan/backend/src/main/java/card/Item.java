package card;

import player.Player;

public class Item extends Card{
    // private String effect;
    private Effect effect; // design patter using effect


    // constructors
    public Item(){
        super();
    }
    public Item(String name,int price, String pathToImg, String effect, Player owner){
        super(name,price,pathToImg, owner);
        // this.effect = effect;
    }


    // other methods
    // public void useEffect(Player player, Player playerEnemy){
    //     player.setName("MAX FOREVER BITCHLESS");
    // }
    
    @Override
    public void useCard(Card cardTarget){
        this.effect.useCard(cardTarget);
    }
    
    
}
