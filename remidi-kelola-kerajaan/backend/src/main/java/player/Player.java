package player;
import java.util.*;
import card.*;

public class Player {
    private String name;
    private ArrayList<Card> activeDeck;

    public Player(){
        this.name = "";
        this.activeDeck = new ArrayList<>();
    }
    public Player(String name) {
        this.name = name;
        this.activeDeck = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
        
    }

    public String getName() {
        return name;
    }

    public void addCardToActiveDeck(Card card){
        this.activeDeck.add(card);
    }
}
