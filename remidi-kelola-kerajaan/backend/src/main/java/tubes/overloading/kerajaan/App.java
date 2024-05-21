package tubes.overloading.kerajaan;


import java.util.ArrayList;

import card.*;
import exceptionkerajaan.BaseException;
import player.*;
import card.creature.*;
import card.product.Food;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        Creature.initializeAllCreatureStaticVariables();
        ArrayList<Creature> grid = new ArrayList<>();
        ArrayList<Card> activeDeck = new ArrayList<>();

        // simulasikan active deck dimana dia hanya List of Card
        Card animal = new Carnivore("Hiu Darat", 400, "", new Player("Marvel"));
        Card plant = new Plant("Biji Stroberi",400, "", new Player(""));

        // Simulasikan Grid dimana terdiri atas list of Creature

        
        // Simulasi active deck
        activeDeck.add(animal);
        activeDeck.add(plant);

        // semisal nge drag animal ke grid
        if (animal instanceof Creature){
            grid.add((Creature)animal);
        }
        if (plant instanceof Creature){
            grid.add((Creature)plant);
        }
        activeDeck.remove(animal);
        activeDeck.remove(plant);

        // Contoh saja, ada product di dalam active deck
        activeDeck.add( grid.get(0).getHarvestedProduct() );
        activeDeck.add(grid.get(1).getHarvestedProduct());


        // Contoh kasih makan 
        try {
            if (activeDeck.get(0) instanceof Food){
                // Hiu makan sirip hiu
                ((Food)activeDeck.get(0)).beEaten( (Animals)grid.get(0));
            }
            if (activeDeck.get(1) instanceof Food){

                // Hiu makan stroberi
                ((Food)activeDeck.get(1)).beEaten( (Animals)grid.get(0));
            }




        } catch (BaseException e) {
            System.out.println("DAMNNNN");
            System.out.println(e.getMessage());
        }
    }
}