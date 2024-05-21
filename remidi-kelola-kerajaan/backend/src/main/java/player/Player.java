package player;
import java.util.*;
import card.*;
import exceptionkerajaan.*;

import card.creature.*;
import exceptionkerajaan.ActiveDeckFullException;
public class Player {
    private String name;
    private ArrayList<Card> activeDeck;
    private ArrayList<Creature> grid;

    public Player(){
        this.name = "";
        this.activeDeck = new ArrayList<>();
        this.grid = new ArrayList<>();

        for (int i = 0 ; i < 20 ; i++){
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

    public void addCardToActiveDeck(Card card) throws BaseException {
        if (this.activeDeck.size() == 6){
            throw new ActiveDeckFullException();
        }
        this.activeDeck.add(card);
    }
    public void addCardToGrid(Creature card, int row, int col) throws BaseException{

    }
}
