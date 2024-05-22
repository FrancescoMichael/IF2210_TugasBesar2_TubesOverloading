package card.item;
import card.creature.*;
import exceptionkerajaan.*;


public class ConcreteDestroy implements ItemEffect{

    @Override
    public void useEffect(Item currentCard,Creature cardTarget, int row, int col) throws BaseException{

        // check if target card is not owner's, check if card target is not blank
        if (currentCard.getOwner() != cardTarget.getOwner() && !cardTarget.isEmpty()){
            cardTarget.getOwner().setBlankOnGrid(row, col);
        } else {
            throw new InvalidCardPlacementException();
        }
    }
    
}
