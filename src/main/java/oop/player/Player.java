package player;

import java.util.*;
import card.*;
import exceptionkerajaan.*;

import card.creature.*;

public class Player {
    private String name;
    private int gulden;
    private ArrayList<Card> activeDeck;
    private ArrayList<Creature> grid;
    private Creature emptyCreature = new Creature("","",this);


    // Constructor
    public Player() {
        this.name = "";
        this.activeDeck = new ArrayList<>();
        this.grid = new ArrayList<>();
        this.gulden = 0;

        for (int i = 0; i < 20; i++) {
            this.grid.add(this.emptyCreature  ) ;
        }
    }

    public Player(String name) {
      this();
      this.name = name;


    }

    // getter
    public String getName() {
        return name;
        
    }
    public int getGulden() {
        return this.gulden;
    }


    // setter
    public void setName(String name) {
        this.name = name;

    }

    public void setGulden(int gulden) {
        this.gulden = gulden;
    }

    // other methods

    public void addCardToActiveDeck(Card card) throws BaseException {
        if (this.activeDeck.size() == 6) {
            throw new ActiveDeckFullException();
        }
        card.setOwner(this);
        this.activeDeck.add(card);
    }

    public void addCardToGrid(Creature card, int row, int col) throws BaseException {
        int arrayIDX = row * 5 + col;
        this.grid.add(arrayIDX, card);
    }

    public void setBlankOnGrid(int row, int col) {
        int arrayIDX = row * 5 + col;
        // this.grid.add(arrayIDX, new Creature("","",this));
        this.grid.set(arrayIDX,  this.emptyCreature);
    }

    public void removeCardAtActiveDeck(int index){
        if (index >= 0 && index < this.activeDeck.size()){
            System.out.println(this.activeDeck.get(index));
            this.activeDeck.remove(index);
        }
    }

    public boolean isActiveDeckFull(){
        return (this.activeDeck.size() >= 6);
    }


    // main methods for grid and activeDeck
    public Card getCardActiveDeck(int index)throws BaseException{
        if (index < 0 || index >= this.activeDeck.size()  ){
            throw new ActiveDeckOutOfBoundsException();
        }
        return this.activeDeck.get(index);
    }

    public Creature getCardGrid(int row, int col)throws BaseException{
        int arrayIDX = row * 5 + col;
        if (arrayIDX > 20 || arrayIDX < 0){
            throw new GridOutOfBoundsException();
        }
        return this.grid.get(arrayIDX);

    }

    public long getNumberOfCardsInGrid(){

        return this.grid.stream().filter(card -> !card.isEmpty()).count();
    }

    public String toString(){
        String temp = "";
        temp = "Name: " + this.name + "\n";
        temp = temp + "Gulden: " + this.gulden + "\n";
        temp = temp + "Number of cards in active deck: " + this.activeDeck.size() + "\n";
        temp = temp + "Number of cards not blank in grid: " + this.getNumberOfCardsInGrid() + "\n"; 
        return temp;
    }


    public void printGridActiveDeckTest() throws BaseException{
        System.out.println(this);
        System.out.println("GRID");
        for (int row = 0 ; row < 4 ; row++){
            for (int col = 0 ; col < 5 ; col++){
                if (this.getCardGrid(row, col).isEmpty() ){
                    System.out.print("EMPTY ");
                } else {
                    System.out.print(this.getCardGrid(row, col).getName().replace(" ", "-") + " ");
                }
                
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("ACTIVE DECK");
        for (Card card : activeDeck){
            System.out.print(card.getName().replace(" ", "-") +" ");
        }
        System.out.println("\n");
        System.out.println("=================================");
        
    }

}
