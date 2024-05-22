package card.item;
import card.creature.*;
import exceptionkerajaan.*;

public class ConcreteDelay implements ItemEffect {

    public void useEffect(Item currentCard,Creature cardTarget, int row, int col) throws BaseException{
        if (cardTarget instanceof Animals){
            // increase age of animals
            cardTarget.increaseWeightAfterEffect(-5);
        } else if (cardTarget instanceof Plant){
            // increase age of plant
            cardTarget.increaseWeightAfterEffect(-2);
        } else {
            throw new InvalidCardPlacementException();
        }
    }
}