package card.item;
import card.creature.*;
import exceptionkerajaan.*;


public class ConcreteInstantHarvest implements ItemEffect{

    @Override
    public void useEffect(Creature currentCard,Creature cardTarget, int row, int col) throws BaseException{
        // Asumsikan hanya dapat digunakan pada kartu sendiri
        if (currentCard.getOwner() == cardTarget.getOwner() && (cardTarget instanceof Plant)){
            // if (currentCard.getOwner()){

            // }
        } else {
            throw new InvalidCardPlacementException();
        }
    }
    
}
