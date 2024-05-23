package oop.card.item;
import oop.card.creature.*;
import oop.exceptionkerajaan.*;


public class ConcreteInstantHarvest implements ItemEffect{

    @Override
    public void useEffect(Item currentCard,Creature cardTarget, int row, int col) throws BaseException{
        // Asumsikan hanya dapat digunakan pada kartu sendiri

        // check if the same owner
        // check if target card is a plant
        if (  (cardTarget instanceof Creature) && !cardTarget.isEmpty()){

            // check if owner active deck is full
            if (cardTarget.getOwner().isActiveDeckFull() && cardTarget.getOwner() != currentCard.getOwner()){
                throw new ActiveDeckFullException();
            } else {

                // Product finalHarvest, add to active deck
                System.out.println("HEREEE");
                int i = currentCard.getOwner().getCardIndexAtActiveDeck(currentCard);

                // set active deck blank
                currentCard.getOwner().removeCardAtActiveDeck(i);
                System.out.println(i);
                cardTarget.getOwner().addCardToActiveDeckFirstEmpty(cardTarget.getHarvestedProduct()); // 
                System.out.println(cardTarget.getHarvestedProduct());
                cardTarget.getOwner().setBlankOnGrid(row, col); // set blank on target at grid row and col
            }
        } else {
            throw new InvalidCardPlacementException();
        }
    }
    
}
