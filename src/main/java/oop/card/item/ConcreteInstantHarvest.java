package oop.card.item;
import oop.card.creature.*;
import oop.exceptionkerajaan.*;


public class ConcreteInstantHarvest implements ItemEffect, GoodEffect{

    @Override
    public void useEffect(Item currentCard,Creature cardTarget, int row, int col) throws BaseException{
        // Asumsikan hanya dapat digunakan pada kartu sendiri

        // check if the same owner
        // check if target card is a plant
        if (  (cardTarget instanceof Creature) && !cardTarget.isEmpty()){

        // check if owner active deck is full

            // Product finalHarvest, add to active deck
            int i = currentCard.getOwner().getCardIndexAtActiveDeck(currentCard);

            // set active deck blank
            currentCard.getOwner().removeCardAtActiveDeck(i);
            cardTarget.getOwner().addCardToActiveDeckFirstEmpty(cardTarget.getHarvestedProduct()); // 
            cardTarget.getOwner().setBlankOnGrid(row, col); // set blank on target at grid row and col
        } else {
            throw new InvalidCardPlacementException();
        }
    }
    
}
