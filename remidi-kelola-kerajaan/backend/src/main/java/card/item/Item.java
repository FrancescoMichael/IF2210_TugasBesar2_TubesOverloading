package card.item;

import card.Card;
import card.creature.Creature;

public interface Item {
    public void useEffect(Creature creature, int row, int col);
} 
