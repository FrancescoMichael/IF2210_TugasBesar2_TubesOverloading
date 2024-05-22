package card.item;
import exceptionkerajaan.*;
import card.creature.Creature;

public interface ItemEffect {
    public void useEffect(Item currentCard,  Creature targetCard, int row, int col) throws BaseException;
    
}
