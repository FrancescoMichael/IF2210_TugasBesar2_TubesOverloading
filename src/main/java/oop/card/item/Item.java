package oop.card.item;

import java.util.Map;

import oop.card.Card;
import oop.card.UsableCard;
import oop.card.creature.Creature;
import oop.exceptionkerajaan.*;
import oop.player.Player;

public class Item extends Card implements UsableCard {

    // public void useEffect(Creature creature, int row, int col) throws
    // BaseException;
    private ItemEffect effect;
    protected static Map<String, ItemEffect> allEffectMap = Map.of(
            "Accelerate", new ConcreteAccelerate(),
            "Delay", new ConcreteDelay(),
            "Instant Harvest", new ConcreteInstantHarvest(),
            "Destroy", new ConcreteDestroy(),
            "Protect", new ConcreteProtect(),
            "Trap", new ConcreteTrap());

    // Constructors
    public Item() {
        super();
        // set default effect to accelerate
        this.effect = Item.allEffectMap.get("Accelerate");
    }

    // constructor with owner and effect
    public Item(String name, Player owner, ItemEffect effect) {
        super(name, owner);
        this.effect = effect;
    }

    // constructor with owner
    public Item(String name, Player owner) {
        super(name, owner);
        if (Item.allEffectMap.containsKey(name)) {
            this.effect = Item.allEffectMap.get(name);
        }

    }

    // constructor without owner
    public Item(String name) {
        super(name);
        if (Item.allEffectMap.containsKey(name)) {
            this.effect = Item.allEffectMap.get(name);
        }

    }

    // getters
    public ItemEffect getEffect() {
        return this.effect;
    }

    // other methods
    @Override
    public void useCard(Card targetCard, int row, int col) throws BaseException {
        if (targetCard instanceof Creature && !targetCard.isEmpty()) {
            if ( (this.effect instanceof GoodEffect && this.getOwner() == targetCard.getOwner())  || ( this.effect instanceof BadEffect && this.getOwner() != targetCard.getOwner()) ){
                this.effect.useEffect(this, (Creature) targetCard, row, col);
                ((Creature) targetCard).addEffect(this);
            } else {
                throw new InvalidCardPlacementException();
            }
        } else {
            throw new InvalidCardPlacementException();
        }

    }

}
