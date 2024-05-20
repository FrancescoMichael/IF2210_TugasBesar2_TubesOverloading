package player;
import java.util.*;
import card.*;

public class Player {
    private String name;
    private ArrayList<Card> activeDeck;

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

    // public void useItemCard(int index, Creature creature, Player enPlayer){
    //     ((Item)this.activeDeck.get(index)).useEffect(this, enPlayer);

    // }
}
