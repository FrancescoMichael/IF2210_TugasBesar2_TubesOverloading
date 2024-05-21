package gamemaster;

import java.util.*;

import player.*;

import observer.*;

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
