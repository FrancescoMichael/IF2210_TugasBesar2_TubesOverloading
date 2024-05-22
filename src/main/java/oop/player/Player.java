package oop.player;

import java.util.*;
import oop.card.*;
import oop.exceptionkerajaan.*;
import oop.card.creature.Creature;

public class Player {
    private String name;
    private int gulden;
    private Card[] activeDeck = new Card[6];
    private ArrayList<Creature> grid;
    private Creature emptyCreature = new Creature("", this);
    private Card emptyCard = new Card("", this);
    private int cardDeckLeft;

    // Constructor
    public Player() {
        this.name = "";

        this.grid = new ArrayList<>();
        this.gulden = 0;

        for (int i = 0; i < 6; i++) {
            this.activeDeck[i] = emptyCard;
        }
        for (int i = 0; i < 20; i++) {
            this.grid.add(this.emptyCreature);
        }
        this.cardDeckLeft = 40;
    }

    public Player(String name) {
        this();
        this.name = name;
    }

    // getter
    public String getName() {
        return name;

    }

    public ArrayList<Creature> getGrid() {
        return this.grid;
    }

    public int getGulden() {
        return this.gulden;
    }

    public int getCardDeckLeft() {
        return this.cardDeckLeft;
    }

    // setter
    public void setName(String name) {
        this.name = name;

    }

    public void setGulden(int gulden) {
        this.gulden = gulden;
    }

    public void setCardDeckLeft(int cardDeckLeft) {
        this.cardDeckLeft = cardDeckLeft;
    }

    // other methods

    // find the index firstEmpty in active deck
    public int firstEmptyActiveDeck() {
        for (int i = 0; i < 6; i++) {
            if (this.activeDeck[i].isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    //
    public void addCardToActiveDeckFirstEmpty(Card card) throws BaseException {
        if (this.isActiveDeckFull()) {
            throw new ActiveDeckFullException();
        }
        card.setOwner(this);
        this.activeDeck[this.firstEmptyActiveDeck()] = card;
    }

    //
    public void addCardToActiveDeck(Card card, int index) throws BaseException {
        card.setOwner(this);
        this.activeDeck[index] = card;

    }

    public void addCardToGrid(Creature card, int row, int col) throws BaseException {
        int arrayIDX = row * 5 + col;
        this.grid.add(arrayIDX, card);
    }

    public void setBlankOnGrid(int row, int col) {
        int arrayIDX = row * 5 + col;
        // this.grid.add(arrayIDX, new Creature("","",this));
        this.grid.set(arrayIDX, this.emptyCreature);
    }

    public void removeCardAtActiveDeck(int index) throws BaseException {
        if (index < 0 || index >= 6) {
            throw new ActiveDeckFullException();
        }
        this.activeDeck[index] = this.emptyCard;
    }

    public boolean isActiveDeckFull() {
        return this.firstEmptyActiveDeck() == -1;
    }

    // main methods for grid and activeDeck
    public Card getCardActiveDeck(int index) throws BaseException {
        if (index < 0 || index >= 6) {
            throw new ActiveDeckOutOfBoundsException();
        }
        return this.activeDeck[index];
    }

    public Creature getCardGrid(int row, int col) throws BaseException {
        int arrayIDX = row * 5 + col;
        if (arrayIDX > 20 || arrayIDX < 0) {
            throw new GridOutOfBoundsException();
        }
        return this.grid.get(arrayIDX);

    }

    public long getNumberOfCardsInGrid() {

        return this.grid.stream().filter(card -> !card.isEmpty()).count();
    }

    public String toString() {
        String temp = "";
        temp = "Name: " + this.name + "\n";
        temp = temp + "Gulden: " + this.gulden + "\n";
        temp = temp + "Number of cards in active deck: " + this.getNumberOfCardsInActiveDeck() + "\n";
        temp = temp + "Number of cards not blank in grid: " + this.getNumberOfCardsInGrid() + "\n";
        return temp;
    }

    public void printGridActiveDeckTest() throws BaseException {
        System.out.println(this);
        System.out.println("GRID");
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                if (this.getCardGrid(row, col).isEmpty()) {
                    System.out.print("EMPTY ");
                } else {
                    System.out.print(this.getCardGrid(row, col).getName().replace(" ", "-") + " ");
                }

            }
            System.out.println();
        }
        System.out.println();
        System.out.println("ACTIVE DECK");
        for (Card card : activeDeck) {
            System.out.print(card.getName().replace(" ", "-") + " ");
        }
        System.out.println("\n");
        System.out.println("=================================");

    }

    public void invokeCard(int activeCardIndex, int rowTarget, int colTarget, Player targetGridPlayer)
            throws BaseException {
        Card currCard = this.getCardActiveDeck(activeCardIndex);

        // check if usable and not a blank card
        if (currCard instanceof UsableCard && !currCard.isEmpty()) {
            Creature targetCard = targetGridPlayer.getCardGrid(rowTarget, colTarget);
            ((UsableCard) currCard).useCard(targetCard, rowTarget, colTarget);

        } else {
            throw new InvalidCardPlacementException();
        }

    }

    public void harvestCard(int rowCardGrid, int colCardGrid, Player targetGridPlayer) throws BaseException {
        Creature currCreature = this.getCardGrid(rowCardGrid, colCardGrid);
        if (this != targetGridPlayer) {
            throw new InvalidCardPlacementException();
        } else {
            currCreature.harvestCreature(rowCardGrid, colCardGrid);
        }

    }

    public long getNumberOfCardsInActiveDeck() {

        return this.grid.stream().filter(card -> !card.isEmpty()).count();
    }

    public void increaseCardDeckLeft(int additionalCardDeck) {
        this.cardDeckLeft += additionalCardDeck;
    }

}
