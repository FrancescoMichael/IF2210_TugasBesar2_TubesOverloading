package tubes.overloading.kerajaan;

import person.Person;
import card.*;
import grid.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Person person = new Person("Marvel");
        Card what  = new Card();
        Grid what2 = new Grid();
        System.out.println(what);
        System.out.println(what2);
        System.out.println(person.getName());
    }
}
