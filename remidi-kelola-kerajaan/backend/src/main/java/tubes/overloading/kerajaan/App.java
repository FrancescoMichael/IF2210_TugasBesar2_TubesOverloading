package tubes.overloading.kerajaan;


import java.util.ArrayList;

import card.*;
import card.creature.*;
import player.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Creature.initializeAllCreatureStaticVariables();
        Card what = new Creature("Hiu Darat",400,"",new Player("Marvel"));

        ArrayList<Creature> card = new ArrayList<>();
        if (what instanceof Creature){
            card.add(  (Creature)what);
        }
        Product whatProduct  = card.get(0).getHarvestedProduct();
        System.out.println("INI NAMANYA");
        System.out.println(whatProduct.getName());




    
    }
}
