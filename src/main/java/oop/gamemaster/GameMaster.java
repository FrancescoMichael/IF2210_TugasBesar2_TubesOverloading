package oop.gamemaster;

import java.util.*;

import oop.player.*;
import oop.observer.*;
import oop.card.creature.*;
import oop.card.item.ConcreteAccelerate;
import oop.card.item.ConcreteDelay;
import oop.card.item.ConcreteDestroy;
import oop.card.item.ConcreteInstantHarvest;
import oop.card.item.ConcreteProtect;
import oop.card.item.ConcreteTrap;
import oop.card.item.ItemEffect;;

public class GameMaster {
    private List<Player> listPlayers;
    private int currentTurn;
    private PlantService plantService;

    public GameMaster(){
        this.listPlayers = new ArrayList<>();
        this.currentTurn = 0;
        this.plantService = new PlantService();
    }


    // getters
    public List<Player> getListPlayers(){
        return this.listPlayers;
    }

    public int getCurrentTurn() {
        return this.currentTurn;
    }

    //setters
    public void setListPlayer( List<Player> listPlayers){
       this.listPlayers  = listPlayers;
    }

    public void setCurrentTurn(int currentTurn){
        this.currentTurn = currentTurn;
    }

    public void setPlantService(PlantService plantService){
        this.plantService = plantService;
    }

    // other functions
        public Player getCurrentPlayer() {
        
        return this.listPlayers.get(this.currentTurn % 2);
    }

    public void next() {
        this.currentTurn++;
        ArrayList<Plant> arr = listPlayers.get(0).getAllPlantsInGrid();
        arr.addAll(listPlayers.get(2).getAllPlantsInGrid());
        this.plantService.setPlants(arr);
        this.plantService.increaseAgeOfPlants();

    }

    // Random Creature

    // random
    public Carnivore generateRandomCarnivore() {
        return new Carnivore("Hiu Darat");
    }

    // public Omnivore generateRandomOmnivore() {
    // protected static Map<String, List<ItemEffect>> allEffectMap = Map.of(
    // "Accelerate", List.of(new ConcreteAccelerate()),
    // "Delay", List.of(new ConcreteDelay()),
    // "Instant harvest", List.of(new ConcreteInstantHarvest()),
    // "Destroy", List.of(new ConcreteDestroy()),
    // "Protect", List.of(new ConcreteProtect()),
    // "Trap", List.of(new ConcreteTrap()));

    // }

    // public Herbivore generateRandomHerbivore() {

    // }

    // public Plant generateRandomPlant() {

    // }

    // // Random ITEM
    // public Item generateRandomItem(){

    // }

    // Random product
    // CarnivoreFood, HerbivoreFood

}
