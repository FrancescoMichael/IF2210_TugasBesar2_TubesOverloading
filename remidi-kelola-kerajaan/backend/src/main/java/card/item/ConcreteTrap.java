package card.item;

import card.creature.*;
import exceptionkerajaan.BaseException;
import exceptionkerajaan.InvalidCardPlacementException;;

public class ConcreteTrap implements ItemEffect{
    @Override
    public void useEffect(Creature currentCard, Creature cardTarget, int row, int col) throws BaseException{
        // check if cardTarget is owner's and not blank

        if (currentCard.getOwner() == cardTarget.getOwner() && !cardTarget.isEmpty()){
            cardTarget.setTrap(true);
        } else {
            throw new InvalidCardPlacementException();
        }
    }
    
}
