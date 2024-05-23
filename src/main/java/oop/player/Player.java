package oop.player;

import java.util.*;
import oop.card.*;
import oop.exceptionkerajaan.*;
import oop.card.creature.Creature;
import oop.card.creature.Plant;
import oop.card.item.*;
import oop.observer.*;

public class Player {
    private String name;
    private int gulden;
    private Card[] activeDeck = new Card[6];
    private ArrayList<Creature> grid;
    private Creature emptyCreature = new Creature("", this);
    private Card emptyCard = new Card("", this);
    private int cardDeckLeft;
    private static PlantService plantService;

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
    public void emptyActiveDeck() {
        try {
            for (int i = 0; i < 6; i++) {
                removeCardAtActiveDeck(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

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

    // public void setCardGrid(Creature card, int row, int col) {}

    // other methods

    public static void setPlayerPlantService(PlantService plantService2) {
        Player.plantService = plantService2;
    }

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
        System.out.println("ADD TO ACTIVE DECK FIRST");
        if (this.isActiveDeckFull()) {
            throw new ActiveDeckFullException();
        }
        card.setOwner(this);
        this.activeDeck[this.firstEmptyActiveDeck()] = card;
    }

    public void addCardToActiveDeck(Card card, int index) {
        card.setOwner(this);
        this.activeDeck[index] = card;
    }

    public void addCardToGrid(Creature card, int row, int col) throws BaseException {
        int arrayIDX = row * 5 + col;
        card.setOwner(this);
        this.grid.set(arrayIDX, card);
        if (card instanceof Plant) {
            // subscribe plant card
            Player.plantService.subscribe((Plant) card);
        }
    }

    public void setBlankOnGrid(int row, int col) throws BaseException{
        int arrayIDX = row * 5 + col;
        // this.grid.add(arrayIDX, new Creature("","",this));
        if (this.getCardGrid(row, col) instanceof Plant) {

            // unsubscribe plant
            Player.plantService.unsubscribe((Plant) this.getCardGrid(row, col));
        }
        this.grid.set(arrayIDX, this.emptyCreature);

    }

    public void removeCardAtActiveDeck(int index) throws BaseException {
        if (index < 0 || index >= 6) {
            throw new ActiveDeckOutOfBoundsException();
        }
        this.activeDeck[index] = this.emptyCard;
    }

    public boolean isActiveDeckFull() {
        return this.firstEmptyActiveDeck() == -1;
    }

    public Card[] getActiveDeck() {
        return this.activeDeck;
    }

    public int numOfActiveDeck() {
        int num = 0;
        for (Card card : this.activeDeck) {
            if (!card.isEmpty()) {
                num++;
            }
        }
        return num;
    }

    // main methods for grid and activeDeck
    public Card getCardActiveDeck(int index) {
        return this.activeDeck[index];
    }

    public Creature getCardGrid(int row, int col) throws BaseException{
        if (row < 0 || row > 3 || col < 0 || col > 4){
            throw new GridOutOfBoundsException();
        }
        int arrayIDX = row * 5 + col;
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
        for (Card card : this.activeDeck) {
            if (!card.isEmpty()) {
                System.out.print(card.getName().replace(" ", "-") + " ");
            } else {
                System.out.print("EMPTY ");
            }

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

            // Handle if active fullx
            if ( currCard instanceof Item ){
                if ( ((Item)currCard).getEffect() instanceof ConcreteInstantHarvest ){
                   return; 
                }
            }
            // System.out.println("MAU REMOVE MASS");
            this.removeCardAtActiveDeck(activeCardIndex);
        } else {
            // System.out.println("EXCEPTION THROWN");
            throw new InvalidCardPlacementException();
        }

    }

    public void moveCardGridtoGrid(int rowSource, int colSource, int rowTarget, int colTarget,
            Player targetGridPlayer)
            throws BaseException {
        Creature currCard = this.getCardGrid(rowSource, colSource);
        Creature targetCard = targetGridPlayer.getCardGrid(rowTarget, colTarget);
        // check if usable and not a blank card
        if (currCard instanceof UsableCard && !currCard.isEmpty() && targetCard.isEmpty() && this == targetGridPlayer) {

            // ((UsableCard) currCard).useCard(targetCard, rowTarget, colTarget);
            this.grid.set(rowTarget * 5 + colTarget , currCard);
            this.grid.set(rowSource * 5 + colSource, targetCard);

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

    public ArrayList<Plant> getAllPlantsInGrid() {
        ArrayList<Plant> temp = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (this.grid.get(i) instanceof Plant && !this.grid.get(i).isEmpty()) {
                temp.add((Plant) grid.get(i));
            }
        }
        return temp;
    }

    public List<String> getAllEffecArrayList(int row, int col) throws BaseException {
        Creature card = this.getCardGrid(row, col);
        Map<String, Integer> countMap = new HashMap<>();
        for (Item item : card.getItemEffects()) {
            countMap.put(item.getName(), countMap.getOrDefault(item.getName(), 0) + 1);
        }
        List<String> resultList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            resultList.add(entry.getKey() + " ( " + entry.getValue() + " ) ");
        }
        return resultList;
    }


    public int getCardIndexAtActiveDeck(Card card){
        for (int i = 0 ; i < 6 ; i++){
            if (this.getCardActiveDeck(i) == card){
                return i;
            }
        }
        return -1;
    }

    public long getNumberOfEmptyCardsActiveDeck(){
        return this.grid.stream().filter(card -> !card.isEmpty()).count();
    }

    public void emptyGrid(){
        try {
            for (int i = 0; i < 4; i++) {
                for(int j = 0; j < 5; j++){
                    setBlankOnGrid(i,j);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void decrementCardDeckLeft(int amount){
        this.cardDeckLeft -= amount;
        if (this.cardDeckLeft <= 0){
            this.cardDeckLeft = 0;
        }
    }

}
