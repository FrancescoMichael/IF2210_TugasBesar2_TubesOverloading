package oop.card.item;
import oop.exceptionkerajaan.*;
import oop.card.creature.Creature;

public interface ItemEffect {
    public void useEffect(Item currentCard,  Creature targetCard, int row, int col) throws BaseException;
    
}
