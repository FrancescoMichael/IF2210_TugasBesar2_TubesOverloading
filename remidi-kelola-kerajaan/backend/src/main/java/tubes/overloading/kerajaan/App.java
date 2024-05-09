package tubes.overloading.kerajaan;

import person.Person;
import card.*;

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
        System.out.println(what);
        System.out.println(person.getName());
    }
}
