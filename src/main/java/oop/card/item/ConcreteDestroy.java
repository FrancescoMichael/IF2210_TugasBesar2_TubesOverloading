package oop.card.item;
import oop.card.creature.*;
import oop.exceptionkerajaan.*;


public class ConcreteDestroy implements ItemEffect, BadEffect{

    @Override
    public void useEffect(Item currentCard,Creature cardTarget, int row, int col) throws BaseException{

        // check if target card is not owner's, check if card target is not blank
        if (!cardTarget.isProtected()){
            cardTarget.getOwner().setBlankOnGrid(row, col);
        } else {
            throw new InvalidProtected();
        }
    }
    
}
