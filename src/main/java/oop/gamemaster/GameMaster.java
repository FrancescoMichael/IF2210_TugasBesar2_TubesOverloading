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
    private Player currentFieldPlayer;
    private int currentTurn;
    private PlantService plantService;

    

    public int getCurrentTurn() {
        return currentTurn;
    }

    public Player getCurrentPlayer() {
        return this.listPlayers.get(this.currentTurn % 2);
    }

    public Player getCurrentFieldPlayer() {
        return currentFieldPlayer;
    }

    public void next() {
        this.currentTurn++;
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
