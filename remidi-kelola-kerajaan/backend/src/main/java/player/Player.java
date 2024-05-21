package player;

import java.util.*;
import card.*;
import exceptionkerajaan.*;

import card.creature.*;
import exceptionkerajaan.ActiveDeckFullException;

public class Player {
    private String name;
    private int gulden;
    private ArrayList<Card> activeDeck;
    private ArrayList<Creature> grid;

    public Player() {
        this.name = "";
        this.activeDeck = new ArrayList<>();
        this.grid = new ArrayList<>();
        this.gulden = 0;

        for (int i = 0; i < 20; i++) {
            this.grid.add(new Creature());
        }
    }

    public Player(String name) {
        super();
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public int getGulden() {
        return this.gulden;
    }

    public void setGulden(int gulden) {
        this.gulden = gulden;
    }

    public void addCardToActiveDeck(Card card, int col) throws BaseException {
        if (this.activeDeck.size() == 6) {
            throw new ActiveDeckFullException();
        }
        this.activeDeck.add(col, card);
    }

    public void addCardToGrid(Creature card, int row, int col) throws BaseException {
        int arrayIDX = row * 5 + col;
        this.grid.add(arrayIDX, card);
    }

    public void setBlankOnGrid(int row, int col) {
        int arrayIDX = row * 5 + col;
        this.grid.add(arrayIDX, new Creature());
    }
}
