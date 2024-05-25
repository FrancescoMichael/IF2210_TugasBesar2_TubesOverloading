package oop.card.item;
import oop.card.creature.Creature;
import oop.exceptionkerajaan.*;
public class ConcreteProtect implements ItemEffect, GoodEffect {

    @Override
 public void useEffect(Item currentCard,Creature cardTarget, int row, int col) throws BaseException{
    // Check if target card is not blank and owners are the same
    cardTarget.setProtected(true);
    // if (currentCard.getOwner() == cardTarget.getOwner() && !cardTarget.isEmpty()){

    // } else {
    //     throw new InvalidCardPlacementException();
    // }
}
    
}
