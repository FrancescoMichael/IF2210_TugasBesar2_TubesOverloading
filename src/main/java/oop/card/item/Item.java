package oop.card.item;

import java.util.Map;

import oop.card.Card;
import oop.card.UsableCard;
import oop.card.creature.Creature;
import oop.exceptionkerajaan.*;
import oop.player.Player;



public class Item extends Card implements UsableCard {

    // public void useEffect(Creature creature, int row, int col) throws BaseException;
    private ItemEffect effect;
    protected static Map<String, ItemEffect> allEffectMap = Map.of(
        "Accelerate" ,new ConcreteAccelerate(),
        "Delay", new ConcreteDelay(),
        "Instant harvest", new ConcreteInstantHarvest(),
        "Destroy", new ConcreteDestroy(),
        "Protect", new ConcreteProtect(),
        "Trap", new ConcreteTrap()  
        );

    // Constructors
    public Item(){
        super();
        // set default effect to accelerate
        this.effect = Item.allEffectMap.get("Accelerate");
    }

    // constructor with owner and effect
    public Item(String name,String pathToImg, Player owner, ItemEffect effect){
        super(name, pathToImg, owner);
        this.effect = effect;
    }

    // constructor with owner 
    public Item(String name,String pathToImg, Player owner){
        super(name, pathToImg, owner);
        if (Item.allEffectMap.containsKey(name)){
            this.effect = Item.allEffectMap.get(name);
        }

    }

    // constructor without owner
    public Item(String name , String pathToImg){
        super(name, pathToImg);
        if (Item.allEffectMap.containsKey(name)){
            this.effect = Item.allEffectMap.get(name);
        }

    }


    // getters
    public ItemEffect getEffect(){
        return this.effect;
    }
    
    // other methods
    @Override
    public void useCard(Card targetCard, int row, int col) throws BaseException{
        if (targetCard instanceof Creature){

            this.effect.useEffect(this, (Creature)targetCard, row, col) ;

            // add effect to target
            ((Creature)targetCard).addEffect(this);
        } else {
            throw new InvalidCardPlacementException();  
        }

    }

    
} 
