package oop.card.item;

import oop.card.creature.*;
import oop.exceptionkerajaan.BaseException;
import oop.exceptionkerajaan.InvalidCardPlacementException;;

public class ConcreteTrap implements ItemEffect{
    @Override
    public void useEffect(Item currentCard, Creature cardTarget, int row, int col) throws BaseException{
        // check if cardTarget is owner's and not blank

        if (currentCard.getOwner() == cardTarget.getOwner() && !cardTarget.isEmpty()){
            cardTarget.setTrap(true);
        } else {
            throw new InvalidCardPlacementException();
        }
    }
    
}