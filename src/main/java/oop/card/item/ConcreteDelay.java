package oop.card.item;
import oop.card.creature.*;
import oop.exceptionkerajaan.*;

public class ConcreteDelay implements ItemEffect, BadEffect {

    public void useEffect(Item currentCard,Creature cardTarget, int row, int col) throws BaseException{
        if (cardTarget instanceof Animals && !cardTarget.isProtected()){
            // increase age of animals
            cardTarget.increaseWeightAfterEffect(-5);
        } else if (cardTarget instanceof Plant && !cardTarget.isProtected()){
            // increase age of plant
            cardTarget.increaseWeightAfterEffect(-2);
        } else if (cardTarget.isProtected()){
            throw new InvalidProtected();
        }
        else {
            throw new InvalidCardPlacementException();
        }
    }
}