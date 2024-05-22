package card.item;
import card.creature.*;
import exceptionkerajaan.*;

import exceptionkerajaan.InvalidCardPlacementException;


public class ConcreteAccelerate implements ItemEffect {
    public void useEffect(Item currentCard,Creature cardTarget, int row, int col) throws BaseException{
        if (cardTarget instanceof Animals){
            // increase age of animals
            cardTarget.increaseWeightAfterEffect(8);
        } else if (cardTarget instanceof Plant){
            // increase age of plant
            cardTarget.increaseWeightAfterEffect(2);
        } else {
            throw new InvalidCardPlacementException();
        }
    }
}