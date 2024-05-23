package oop;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.function.Supplier;

import oop.card.*;

import org.junit.jupiter.api.Test;
import oop.card.creature.Carnivore;
import oop.card.creature.Creature;
import oop.card.creature.Herbivore;
import oop.card.creature.Omnivore;
import oop.card.creature.Plant;
import oop.card.item.Item;
import oop.card.product.CarnivoreFood;
import oop.card.product.HerbivoreFood;
import oop.exceptionkerajaan.BaseException;
import oop.player.Player;
import static java.util.Map.entry;
 

public class DefaultTest {
    protected static Map<String, Supplier<? extends Card>> allCardMap = Map.ofEntries(
            entry("Sapi", () -> new Herbivore("Sapi")),
            entry("Domba", () -> new Herbivore("Domba")),
            entry("Kuda", () -> new Herbivore("Kuda")),
            entry("Hiu Darat", () -> new Carnivore("Hiu Darat")),
            entry("Ayam", () -> new Omnivore("Ayam")),
            entry("Beruang", () -> new Omnivore("Beruang")),
            entry("Biji Labu", () -> new Plant("Biji Labu")),
            entry("Biji Jagung", () -> new Plant("Biji Jagung")),
            entry("Biji Stroberi", () -> new Plant("Biji Stroberi")),
            entry("Accelerate", () -> new Item("Accelerate")),
            entry("Delay", () -> new Item("Delay")),
            entry("Instant harvest", () -> new Item("Instant harvest")),
            entry("Destroy", () -> new Item("Destroy")),
            entry("Protect", () -> new Item("Protect")),
            entry("Trap", () -> new Item("Trap")),
            entry("Jagung", () -> new HerbivoreFood("Jagung", 150, "Herbivore", 3)),
            entry("Labu", () -> new HerbivoreFood("Labu", 500, "Herbivore", 10)),
            entry("Stroberi", () -> new HerbivoreFood("Stroberi", 350, "Herbivore", 5)),
            entry("Sirip Hiu", () -> new CarnivoreFood("Sirip Hiu", 500, "Carnivore", 12)),
            entry("Susu", () -> new CarnivoreFood("Susu", 100, "Carnivore", 4)),
            entry("Daging Domba", () -> new CarnivoreFood("Daging Domba", 120, "Carnivore", 6)),
            entry("Daging Kuda", () -> new CarnivoreFood("Daging Kuda", 150, "Carnivore", 8)),
            entry("Telur", () -> new CarnivoreFood("Telur", 50, "Carnivore", 2)),
            entry("Daging Beruang", () -> new CarnivoreFood("Daging Beruang", 500, "Carnivore", 12)));
    @Test
    public void test(){
        assertTrue( true );
        try{
            // initializing Players
            Player player1 = new Player("marvel");
            Player player2 = new Player("Ray");
    
            // adding cards 
            Card creatureCard = allCardMap.get("Hiu Darat").get();
            player1.addCardToGrid((Creature)creatureCard, 0, 0);
            assertTrue( player1.getCardGrid(0, 0) instanceof Carnivore);

            player1.addCardToActiveDeckFirstEmpty( allCardMap.get("Hiu Darat").get() );
            assertTrue( player1.getCardActiveDeck(0) instanceof Carnivore);
            player1.addCardToActiveDeckFirstEmpty(allCardMap.get("Sapi").get());
            assertTrue( player1.getCardActiveDeck(1) instanceof Herbivore);
            player1.addCardToActiveDeckFirstEmpty(allCardMap.get("Ayam").get());
            assertTrue( player1.getCardActiveDeck(2) instanceof Omnivore);
            player1.addCardToActiveDeck(allCardMap.get("Destroy").get(), 3);
            assertTrue( player1.getCardActiveDeck(3) instanceof Item &&  ((Item)player1.getCardActiveDeck(3)).getEffect() != null );
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
