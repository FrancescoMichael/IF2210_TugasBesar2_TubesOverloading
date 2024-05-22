package oop.gamemaster;

import java.util.*;

import oop.player.*;

import oop.observer.*;

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
}
