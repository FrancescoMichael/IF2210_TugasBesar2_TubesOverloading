// package oop;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import org.junit.jupiter.api.Test;

// import oop.card.UsableCard;
// import oop.card.creature.Animals;
// import oop.card.creature.Carnivore;
// import oop.card.creature.Creature;
// import oop.card.creature.Herbivore;
// import oop.card.creature.Omnivore;
// import oop.card.creature.Plant;
// import oop.card.item.Item;
// import oop.card.product.CarnivoreFood;
// import oop.exceptionkerajaan.BaseException;
// import oop.player.Player;

// public class AppTest {

//     public void test(){
//           assertTrue( true );
//         // System.out.println("HELLO");

//         // simulating Active deck 

//         Player player1 = new Player("Marvel Pangondian");
//         Player player2 = new Player("Maximilian");

//         // try catch
//         try{
//             // INI ADALAH TESTING !!!!!!
//             // Creature Testing
//             // 2 PLAYER

            
//             // generate di active deck 6 Kartu untuk masing masing player
//             // player 1

//             // one carnivore, one herbivore, one omnivore, one plant 
//             // addCardToActiveDexk otomatis set card owner ke dirinya sendiri

//             player1.addCardToActiveDeck( new Carnivore("Hiu Darat","damn.png") );
//             player1.addCardToActiveDeck(new Herbivore("Sapi","what.png"));
//             player1.addCardToActiveDeck(new Omnivore("Ayam","ayam.png"));
//             player1.addCardToActiveDeck(new Plant("Biji Jagung","Biji jagung.png"));
            
//             player1.addCardToActiveDeck(  ((Creature)player1.getCardActiveDeck(0)).getHarvestedProduct() );
//             player1.addCardToActiveDeck(  ((Creature)player1.getCardActiveDeck(1)).getHarvestedProduct()    );

//             // add one more, out of bounds exception
//             // player1.addCardToActiveDeck(  ((Creature)player1.getCardActiveDeck(1)).getHarvestedProduct()    );
        
//             // Initialize active dek in player2

//             player2.addCardToActiveDeck( new Carnivore("Hiu Darat","damn.png") );
//             player2.addCardToActiveDeck(new Herbivore("Sapi","what.png"));
//             player2.addCardToActiveDeck(new Omnivore("Ayam","ayam.png"));
//             player2.addCardToActiveDeck(new Plant("Biji Jagung","Biji jagung.png"));
            
//             player2.addCardToActiveDeck(  ((Creature)player2.getCardActiveDeck(0)).getHarvestedProduct() );
//             player2.addCardToActiveDeck(  ((Creature)player2.getCardActiveDeck(3)).getHarvestedProduct());


//             // STARTING WITH EMPTY GRID
//             System.out.println(" FIRST INITIALIZING");

//             player1.printGridActiveDeckTest();
//             player2.printGridActiveDeckTest();


//             // adding to grid, adding all creature to grid
//             // Player1
//             // in this example, the 0'th index of activeDeck, which is "hiu darat" is being dragged to grid at row 1, col 2
//             System.out.println("ADD HIU DARAT IN ROW 1 COL 2");
//             ((UsableCard) player1.getCardActiveDeck(0) ).useCard( player1.getCardGrid(1, 2) , 1, 2);

//             // Jika berhasil maka GUI akan menghapus deck pada active deck
//             player1.removeCardAtActiveDeck(0);

//             player1.printGridActiveDeckTest();

//             assertTrue(  (player1.getCardGrid(1, 2) instanceof Animals) && (player1.getCardGrid(1, 2) instanceof Carnivore) );
            
//             // TRYING TO FEED CARNIVORE at grid 1 - 2 (row - col)  , index 4 of active deck
//             ((UsableCard) player1.getCardActiveDeck(3)).useCard( player1.getCardGrid(1, 2), 1, 2);
            
//             // GUI will remove card at index 3 of active index, card 3 is carnivore food Sirip Hiu
//             player1.removeCardAtActiveDeck(3);
//             player1.printGridActiveDeckTest();


//             // Trying to feed carnivore at grid 1,2 with AD index 3 susu
//             ((UsableCard) player1.getCardActiveDeck(3)).useCard( player1.getCardGrid(1, 2), 1, 2);
//             player1.removeCardAtActiveDeck(3);
//             System.out.println(player1.getCardGrid(1, 2));
            
//             player1.addCardToActiveDeck( new CarnivoreFood("Susu", 100, "dummy.img", "Carnivore", 4));
//             player1.addCardToActiveDeck( new CarnivoreFood("Susu", 100, "dummy.img", "Carnivore", 4));

//             player1.printGridActiveDeckTest();

//             ((UsableCard) player1.getCardActiveDeck(3)).useCard( player1.getCardGrid(1, 2), 1, 2);
//             player1.removeCardAtActiveDeck(3);
//             player1.getCardGrid(1, 2).harvestCreature(1, 2); /// Row dan col adalah lokasi hewan tersebut pada grid, tujuannya untuk menghapus hewan dari grid setelagh 
//             player1.printGridActiveDeckTest();

//             // test tumbuhan
//             ( (UsableCard)player1.getCardActiveDeck(2) ).useCard( player1.getCardGrid(3, 4), 3, 4); // taro tumbuhan\
//             player1.removeCardAtActiveDeck(2); // remove from active deck, GUI 
//             player1.addCardToActiveDeck(new Item("Accelerate","damn.img"));
//             player1.addCardToActiveDeck(new Item("Accelerate","damn.img"));
//             player1.printGridActiveDeckTest( );

//             //use item card
//             ( (UsableCard)player1.getCardActiveDeck(4) ).useCard( player1.getCardGrid(3, 4), 3, 4); 
//             player1.removeCardAtActiveDeck(4);
//             ( (UsableCard)player1.getCardActiveDeck(4) ).useCard( player1.getCardGrid(3, 4), 3, 4);
//             player1.removeCardAtActiveDeck(4);
//             System.out.println(player1.getCardGrid(3, 4));

//             player1.addCardToActiveDeck(new Item("Delay","damn.img"));
//             player1.addCardToActiveDeck(new Item("Delay","damn.img"));

//             ( (UsableCard)player1.getCardActiveDeck(4) ).useCard( player1.getCardGrid(3, 4), 3, 4); 
//             player1.removeCardAtActiveDeck(4);
//             ( (UsableCard)player1.getCardActiveDeck(4) ).useCard( player1.getCardGrid(3, 4), 3, 4);
//             player1.removeCardAtActiveDeck(4);
//             player1.printGridActiveDeckTest();
//             System.out.println(player1.getCardGrid(3, 4));


//         }catch(BaseException e){
//             System.out.println(e.getMessage());
//         }

//     }


// }
