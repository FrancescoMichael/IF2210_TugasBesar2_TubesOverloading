package oop.gamemaster;

import java.util.*;

import oop.player.*;
import oop.card.creature.Carnivore;
import oop.card.creature.Herbivore;
import oop.card.creature.Omnivore;
import oop.observer.*;
import oop.card.creature.*;;

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
    // public Carnivore generateRandomCarnivore(){

    // }
    // public Omnivore generateRandomOmnivore(){

    // }
    // public Herbivore generateRandomHerbivore(){

    // }
    // public Plant generateRandomPlant(){
        
    // }
    

    // // Random ITEM
    // public Item generateRandomItem(){
        
    // }

    // Random product
    // CarnivoreFood, HerbivoreFood



}
