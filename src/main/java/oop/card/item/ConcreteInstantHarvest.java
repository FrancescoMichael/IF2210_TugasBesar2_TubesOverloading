package oop.card.item;
import oop.card.creature.*;
import oop.exceptionkerajaan.*;


public class ConcreteInstantHarvest implements ItemEffect{

    @Override
    public void useEffect(Item currentCard,Creature cardTarget, int row, int col) throws BaseException{
        // Asumsikan hanya dapat digunakan pada kartu sendiri

        // check if the same owner
        // check if target card is a plant
        if (currentCard.getOwner() == cardTarget.getOwner() && (cardTarget instanceof Plant)){

            // check if owner active deck is full
            if (currentCard.getOwner().isActiveDeckFull() ){
                throw new ActiveDeckFullException();
            } else {

                // Product finalHarvest, add to active deck
                currentCard.getOwner().addCardToActiveDeck( cardTarget.getHarvestedProduct()); // 
                currentCard.getOwner().setBlankOnGrid(row, col); // set blank on target at grid row and col
            }
        } else {
            throw new InvalidCardPlacementException();
        }
    }
    
}
