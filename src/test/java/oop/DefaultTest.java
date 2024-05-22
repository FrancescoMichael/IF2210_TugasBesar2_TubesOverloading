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
            player1.addCardToActiveDeckFirstEmpty(new Carnivore("Hiu Darat"));
            player1.addCardToActiveDeckFirstEmpty(new Herbivore("Sapi"));
            player1.addCardToActiveDeckFirstEmpty(new Omnivore());

        }catch (BaseException e){
            System.out.println(e.getMessage());
        }
        // Testing using invokeCard, harvest, etc=


    }
}
