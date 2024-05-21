package tubes.overloading.kerajaan;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import card.creature.Animals;
import card.creature.Carnivore;
import card.creature.Creature;
import card.creature.Herbivore;
import card.creature.Omnivore;
import card.creature.Plant;
import exceptionkerajaan.BaseException;
import player.Player;

import card.*;
    // Creature.initializeAllCreatureStaticVariables();
    // ArrayList<Creature> grid = new ArrayList<>();
    // ArrayList<Card> activeDeck = new ArrayList<>();

    // // simulasikan active deck dimana dia hanya List of Card
    // Card animal = new Carnivore("Hiu Darat", "", new Player("Marvel"));
    // Card plant = new Plant("Biji Stroberi", "", new Player("Marve"));

    // // Simulasikan Grid dimana terdiri atas list of Creature


    // // Simulasi active deck
    // activeDeck.add(animal);
    // activeDeck.add(plant);

    // // semisal nge drag animal ke grid
    // if (animal instanceof Creature){
    //     grid.add((Creature)animal);
    // }
    // if (plant instanceof Creature){
    //     grid.add((Creature)plant);
    // }
    // activeDeck.remove(animal);
    // activeDeck.remove(plant);

    // // Contoh saja, ada product di dalam active deck
    // activeDeck.add( grid.get(0).getHarvestedProduct() );
    // activeDeck.add(grid.get(1).getHarvestedProduct());


    // // Contoh kasih makan 
    // try {
    //     if (activeDeck.get(0) instanceof Food){
    //         // Hiu makan sirip hiu
    //         ((Food)activeDeck.get(0)).beEaten( (Animals)grid.get(0));
    //         System.out.println(activeDeck.get(0)   );
    //         System.out.println(activeDeck.get(1)   );
    //         System.out.println(grid.get(0)   );
    //     }
    //     if (activeDeck.get(1) instanceof Food){

    //         // Hiu makan stroberi
    //         ((Food)activeDeck.get(1)).beEaten( (Animals)grid.get(0));
    //     }




    // } catch (BaseException e) {
    //     System.out.println("DAMNNNN");
    //     System.out.println(e.getMessage());
    // }



/**
 * Unit test for simple App.
 */


public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
        // System.out.println("HELLO");

        // simulating Active deck 

        Player player1 = new Player("Marvel Pangondian");
        Player player2 = new Player("Maximilian");

        // try catch
        try{
            // INI ADALAH TESTING !!!!!!
            // Creature Testing
            // 2 PLAYER

            
            // generate di active deck 6 Kartu untuk masing masing player
            // player 1

            // one carnivore, one herbivore, one omnivore, one plant 
            // addCardToActiveDexk otomatis set card owner ke dirinya sendiri
            player1.addCardToActiveDeck( new Carnivore("Hiu Darat","damn.png") );
            player1.addCardToActiveDeck(new Herbivore("Sapi","what.png"));
            player1.addCardToActiveDeck(new Omnivore("Ayam","ayam.png"));
            player1.addCardToActiveDeck(new Plant("Biji Jagung","Biji jagung.png"));
            
            player1.addCardToActiveDeck(  ((Creature)player1.getCardActiveDeck(0)).getHarvestedProduct() );
            player1.addCardToActiveDeck(  ((Creature)player1.getCardActiveDeck(1)).getHarvestedProduct()    );

            // add one more, out of bounds exception
            // player1.addCardToActiveDeck(  ((Creature)player1.getCardActiveDeck(1)).getHarvestedProduct()    );
        
            // Initialize active dek in player2

            player2.addCardToActiveDeck( new Carnivore("Hiu Darat","damn.png") );
            player2.addCardToActiveDeck(new Herbivore("Sapi","what.png"));
            player2.addCardToActiveDeck(new Omnivore("Ayam","ayam.png"));
            player2.addCardToActiveDeck(new Plant("Biji Jagung","Biji jagung.png"));
            
            player2.addCardToActiveDeck(  ((Creature)player1.getCardActiveDeck(0)).getHarvestedProduct() );
            player2.addCardToActiveDeck(  ((Creature)player1.getCardActiveDeck(1)).getHarvestedProduct()    );


            // STARTING WITH EMPTY GRID
            System.out.println(" FIRST INITIALIZING");

            player1.printGridActiveDeckTest();
            player2.printGridActiveDeckTest();


            // adding to grid, adding all creature to grid
            // Player1
            // in this example, the 0'th index of activeDeck, which is "hiu darat" is being dragged to grid at row 1, col 2
            System.out.println("ADD HIU DARAT IN ROW 1 COL 2");
            ((UsableCard) player1.getCardActiveDeck(0) ).useCard( player1.getCardGrid(1, 2) , 1, 2);

            // Jika berhasil maka GUI akan menghapus deck pada active deck
            player1.removeCardAtActiveDeck(0);

            player1.printGridActiveDeckTest();

            assertTrue("FAIL LINE 153",  (player1.getCardGrid(1, 2) instanceof Animals) && (player1.getCardGrid(1, 2) instanceof Carnivore) );
            
            // TRYING TO FEED CARNIVORE at grid 1 - 2 (row - col)  , index 4 of active deck
            ((UsableCard) player1.getCardActiveDeck(4)).useCard( player1.getCardGrid(1, 2), 0, 0);
            
            // GUI will remove card at index 4 of active index
            player1.removeCardAtActiveDeck(4);
            System.out.println(player1.getCardGrid(1, 2));
            player1.printGridActiveDeckTest();




        }catch(BaseException e){
            System.out.println(e.getMessage());
        }

    }


}
