package oop;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import oop.card.UsableCard;
import oop.card.creature.Animals;
import oop.card.creature.Carnivore;
import oop.card.creature.Creature;
import oop.card.creature.Herbivore;
import oop.card.creature.Omnivore;
import oop.card.creature.Plant;
import oop.card.item.Item;
import oop.card.product.CarnivoreFood;
import oop.exceptionkerajaan.BaseException;
import oop.gamemaster.GameMaster;
import oop.player.Player;


public class DefaultTest {
    @Test
    public void test(){
        assertTrue( true );
        try{
            GameMaster gameMaster = new GameMaster();
            // initializing Players
            Player player1 = new Player("marvel");
            Player player2 = new Player("Ray");
    
            // adding cards 

            player1.addCardToGrid(new Carnivore("Hiu Darat"), 0, 0);
            player1.addCardToActiveDeckFirstEmpty(new Carnivore("Hiu Darat"));
            player1.addCardToActiveDeckFirstEmpty(new Herbivore("Sapi"));
            player1.addCardToActiveDeckFirstEmpty(new Omnivore("Ayam"));
            player1.addCardToActiveDeck(new Item("Destroy"), 3);
            player1.addCardToActiveDeck(new Item("Instant Harvest"), 4);
            player1.addCardToActiveDeck(new Item("Accelerate"), 5);

            player2.addCardToActiveDeckFirstEmpty(new Omnivore("Beruang"));
            player2.addCardToActiveDeckFirstEmpty(new Item("Protect"));
            player2.addCardToActiveDeckFirstEmpty(new Item("Trap"));
            player2.addCardToActiveDeckFirstEmpty(new Item("Delay"));
            player2.addCardToActiveDeckFirstEmpty(new Item("Trap"));
            player2.addCardToActiveDeckFirstEmpty(new Plant("Biji Stroberi"));

            player1.printGridActiveDeckTest();
            

            // // example, dragging hiu darat to row 0 and col 1;
            player1.invokeCard(0, 0, 1, player1);
            player1.removeCardAtActiveDeck(0);
            player1.printGridActiveDeckTest();
            // // add accelerate to 0 1
            player1.invokeCard(5, 0, 1, player1);
            player1.removeCardAtActiveDeck(5);
            player1.printGridActiveDeckTest();


            // System.out.println(player1.getCardGrid(0, 1));
            // player1.printGridActiveDeckTest();
            System.out.println(player1.getCardActiveDeck(4));
            player1.invokeCard(4, 0, 0, player1);
            player1.removeCardAtActiveDeck(4);
            player1.printGridActiveDeckTest();

            player2.printGridActiveDeckTest();
            player2.invokeCard(0, 0, 0, player2);
            player2.invokeCard(5, 0, 1, player2);
            player2.printGridActiveDeckTest();
            player1.invokeCard(3, 0, 0, player2);
            player1.removeCardAtActiveDeck(3);
            player2.printGridActiveDeckTest();
            // player1.printGridActiveDeckTest();



        }catch (BaseException e){

            System.out.println(e.getMessage());
        }
        // Testing using invokeCard, harvest, etc=


    }
}
