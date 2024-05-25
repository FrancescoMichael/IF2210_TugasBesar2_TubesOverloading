package oop;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import oop.card.creature.*;
import oop.card.product.CarnivoreFood;
import oop.card.product.Food;
import oop.card.product.HerbivoreFood;
import oop.gamemaster.GameMaster;
import oop.observer.PlantService;
import oop.player.Player;
import oop.shop.Shop;
import oop.card.Card;
import oop.card.item.*;;

public class GameMasterTest {
    @Test
    public void test(){
        assertTrue(true);
        GameMaster gameMaster = new GameMaster();
        Player player1 = new Player("Player1");
        Player player2= new Player("Player2");
        List<Player> allPlayer = new ArrayList<>();
        allPlayer.add(player1);
        allPlayer.add(player2);
        gameMaster.setListPlayer(allPlayer);
        PlantService service = gameMaster.getPlantService();
        service.increaseAgeOfPlants();
        List<Plant> allPlant = service.getSubscribers();
        int currTurn = gameMaster.getCurrentTurn();
        try{
            gameMaster.next();
        } catch( Exception e){
            System.out.println(e);
        }
        assertTrue(gameMaster.getCurrentTurn() == currTurn + 1);

        gameMaster.save("src/test/resources", ".txt");
        Creature card = gameMaster.generateRandomCarnivore();
        assertTrue(card instanceof Carnivore);
        System.out.println(card.getName());
        gameMaster.load("src/test/resources", ".txt");
        Food cardCarnivore = gameMaster.generateRandomCarnivoreFood();
        assertTrue(cardCarnivore instanceof CarnivoreFood);
        Food cardHerbivore =  gameMaster.generateRandomHerbivoreFood();
        assertTrue(cardHerbivore instanceof HerbivoreFood);
        gameMaster.shuffle();
        String tempbefore = "MAKANAN_BERUANG";
        String temp = GameMaster.formatItemString(tempbefore);
        assertTrue(temp.compareTo("Makanan Beruang") == 0);
        try{
            gameMaster.bearAttackProcess(new Integer[10], null, false);
        }catch (Exception e){
            System.out.println(e);
        }
        String str = GameMaster.saveFormatString("damnaDSA.txt");
        assertTrue(str.compareTo("DAMNADSA.TXT") == 0);
        Player tempPlayer = new Player("Love");
        List<Integer> temp2 = new ArrayList<>();
        temp2.add(1);
        temp2.add(2);
        temp2.add(3);
        List<String> grid = new ArrayList<>();
        grid.add("FOOD");
        grid.add("FOOD");
        grid.add("FOOD");
        grid.add("FOOD");

        List<String> active = new ArrayList<>();
        active.add("ACCELERATE");
        active.add("ACCELERATE");
        active.add("ACCELERATE");
        try {
            gameMaster.loadPlayer(currTurn,  temp2, active, grid);
      
        } catch( Exception e){

        }
        Creature tempRandom = gameMaster.generateRandomHerbivore();
        assertTrue(tempRandom instanceof Herbivore);
        Creature plant = gameMaster.generateRandomPlant();
        assertTrue(plant instanceof Plant);
        Animals omniAnimals = gameMaster.generateRandomOmnivore();
        assertTrue(omniAnimals instanceof Omnivore);
        Card item = gameMaster.generateRandomItem();
        assertTrue(item instanceof Item);
        Player winner = gameMaster.getWinner();
        assertTrue(winner.getGulden() >= player1.getGulden() && winner.getGulden() >= player2.getGulden());
        String coor = gameMaster.indexToCoordinate(10 , 5);
        System.err.println(coor);
        Integer f = gameMaster.coordinateToIndex(coor, 5);
        assertTrue(f == 10);
        gameMaster.setCurrentTurn(100);
        assertTrue(gameMaster.getCurrentTurn()  == 100); 
        Shop newShow = gameMaster.getShop();
        try{
            gameMaster.doneShuffling(null, null);
        }catch (Exception e){
            
        }

        Player newPlayer = new Player("WINNER");
        newPlayer.setGulden(1000000);
        allPlayer.set(1, newPlayer);
        gameMaster.setListPlayer(allPlayer);
        Player winner2 = gameMaster.getWinner();
        assertTrue(winner2 == newPlayer);

      




    }
    
}
