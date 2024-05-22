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

    public int getCurrentTurn() {
        return currentTurn;
    }

    public Player getCurrentPlayer() {
        return this.listPlayers.get(this.currentTurn % 2);
    }

    public void next() {
        this.currentTurn++;
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
