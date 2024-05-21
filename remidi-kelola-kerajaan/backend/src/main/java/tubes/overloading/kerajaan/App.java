package tubes.overloading.kerajaan;


import java.util.ArrayList;

import card.*;
import exceptionkerajaan.BaseException;
import player.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Creature.initializeAllCreatureStaticVariables();
        Card what = new Carnivore("Hiu Darat", 400, "", new Player("Marvel"));

        ArrayList<Creature> card = new ArrayList<>();
        if (what instanceof Creature) {
            card.add((Creature) what);
        }

        System.out.println("DISINI");

        Product whatProduct = card.get(0).getHarvestedProduct();
        System.out.println(whatProduct.getName());

        try {
            if (whatProduct instanceof CarnivoreFood) {
                ((CarnivoreFood) whatProduct).beEaten((Animals) card.get(0));
            } else if (whatProduct instanceof HerbivoreFood) {
                ((HerbivoreFood) whatProduct).beEaten((Animals) card.get(0));
            } else {
                System.out.println("Unknown product type.");
            }
        } catch (BaseException e) {
            System.out.println("DAMNNNN");
            System.out.println(e.getMessage());
        }
    }
}