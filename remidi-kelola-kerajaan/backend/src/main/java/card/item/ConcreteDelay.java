package card.item;
import card.creature.*;
import exceptionkerajaan.*;

public class ConcreteDelay implements ItemEffect {

    public void useEffect(Item currentCard,Creature cardTarget, int row, int col) throws BaseException{
        if (cardTarget instanceof Animals){
            // increase age of animals
            cardTarget.increasweightAfterEffect(-5);
        } else if (cardTarget instanceof Plant){
            // increase age of plant
            cardTarget.increaseWeight(-2);
        } else {
            throw new InvalidCardPlacementException();
        }
    }
}