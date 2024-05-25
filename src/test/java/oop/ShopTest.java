package oop;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import oop.shop.*;
import oop.card.creature.Carnivore;
import oop.card.creature.Herbivore;
import oop.card.creature.Omnivore;
import oop.card.creature.Plant;
import oop.card.item.Item;
import oop.card.product.CarnivoreFood;
import oop.card.product.Product;
import oop.exceptionkerajaan.ActiveDeckFullException;
import oop.exceptionkerajaan.BaseException;
import java.util.Map;
import oop.player.*;
public class ShopTest {
    @Test
    public void test(){
        Shop shopTest = new Shop();
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        try{    
            player1.addCardToGrid(new Carnivore("Hiu Darat"), 0, 0);
            player1.addCardToActiveDeckFirstEmpty(new Carnivore("Hiu Darat"));
            player1.addCardToActiveDeckFirstEmpty(new Herbivore("Sapi"));
            player1.addCardToActiveDeckFirstEmpty(new Omnivore("Ayam"));
            player1.addCardToActiveDeck(new Item("Destroy"), 3);
            player1.addCardToActiveDeck(new Item("Instant Harvest"), 4);
            player1.addCardToActiveDeck(new Item("Accelerate"), 5);
            assertTrue(player1.isActiveDeckFull());

            player2.addCardToActiveDeckFirstEmpty(new Omnivore("Beruang"));
            player2.addCardToActiveDeckFirstEmpty(new Item("Protect"));
            player2.addCardToActiveDeckFirstEmpty(new Item("Trap"));
            player2.addCardToActiveDeckFirstEmpty(new Item("Delay"));
            player2.addCardToActiveDeckFirstEmpty(new Item("Trap"));
            player2.addCardToActiveDeckFirstEmpty(new Plant("Biji Stroberi"));

            try{
                shopTest.Sell(player1, 0);
            } catch (BaseException e){
                assertTrue( ! (player1.getCardActiveDeck(0) instanceof Product));
            }

            player1.removeCardAtActiveDeck(0);
            player1.setGulden(0);
            player1.addCardToActiveDeck( new CarnivoreFood("Susu", 100, player1, "Carnivore", 100), 0 );
            shopTest.Sell(player1, 0);
            assertTrue(player1.getGulden() == 100);
            shopTest.resetStock();
            Map<String, Integer> allStock = shopTest.getStock();
            for (Integer allStockInt : allStock.values()){
                assertTrue(allStockInt == 0);
            }
            


        }catch (BaseException e){
            assertTrue(e instanceof ActiveDeckFullException);
            System.out.println(e.getMessage());
        }
          
    }
}
