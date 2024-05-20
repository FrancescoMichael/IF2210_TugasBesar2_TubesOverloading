package tubes.overloading.kerajaan;

import person.Person;
import card.*;
import grid.*;
import player.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Person person = new Person("Marvel");
        Item what  = new Item();
        Grid what2 = new Grid();
        System.out.println(what);
        System.out.println(what2);
        System.out.println(person.getName());
        Player player = new Player("Marvel");
        System.out.println(player.getName());
        player.addCardToActiveDeck(what);
        // player.useItemCard(0);
        System.out.println(player.getName());
    }
}
