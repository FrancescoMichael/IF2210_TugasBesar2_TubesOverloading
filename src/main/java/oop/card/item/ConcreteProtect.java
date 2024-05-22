package oop.card.item;
import oop.card.creature.Creature;
import oop.exceptionkerajaan.*;
public class ConcreteProtect implements ItemEffect {

    @Override
 public void useEffect(Item currentCard,Creature cardTarget, int row, int col) throws BaseException{
    // Check if target card is not blank and owners are the same
    if (currentCard.getOwner() == cardTarget.getOwner() && !cardTarget.isEmpty()){
        cardTarget.setProtected(true);
    } else {
        throw new InvalidCardPlacementException();
    }
}
    
}
