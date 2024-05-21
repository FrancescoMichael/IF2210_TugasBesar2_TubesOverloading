package card.item;

import card.Card;

public interface ItemEffect {
    public void useEffect(Card targetCard, int row, int col);
    
}
