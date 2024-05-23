package oop.gamemaster;

import java.util.*;
import java.util.function.Supplier;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import static java.util.Map.entry;

import oop.player.*;
import oop.saveload.SaveLoad;
import oop.observer.*;
import oop.card.creature.*;
import oop.card.item.Item;
import oop.card.item.ItemEffect;
import oop.card.product.CarnivoreFood;
import oop.card.product.HerbivoreFood;
import oop.card.product.Product;
import oop.exceptionkerajaan.BaseException;
import oop.FieldController;
import oop.card.*;
import oop.shop.*;
import javafx.util.Duration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


import javafx.application.Preloader.StateChangeNotification;


import oop.card.item.ConcreteAccelerate;
import oop.card.item.ConcreteDelay;
import oop.card.item.ConcreteDestroy;
import oop.card.item.ConcreteInstantHarvest;
import oop.card.item.ConcreteProtect;
import oop.card.item.ConcreteTrap;

// import oop.card.product.CarnivoreFood;
// import oop.card.product.HerbivoreFood;

public class GameMaster {
    private final Random random = new Random();
    private List<Player> listPlayers;
    private Player currentFieldPlayer;
    private int currentTurn;
    private PlantService plantService;
    private Shop shop;
    private SaveLoad saveLoad;
    private boolean bearAttack;

    protected static Map<String, Supplier<Herbivore>> allHerbivoreMap = Map.of(
            "Sapi", () -> new Herbivore("Sapi"),
            "Domba", () -> new Herbivore("Domba"),
            "Kuda", () -> new Herbivore("Kuda"));

    protected static Map<String, Supplier<Carnivore>> allCarnivoreMap = Map.of(
            "Hiu Darat", () -> new Carnivore("Hiu Darat"));

    protected static Map<String, Supplier<Omnivore>> allOmnivoreMap = Map.of(
            "Ayam", () -> new Omnivore("Ayam"),
            "Beruang", () -> new Omnivore("Beruang"));

    protected static Map<String, Supplier<Plant>> allPlantMap = Map.of(
            "Biji Labu", () -> new Plant("Biji Labu"),
            "Biji Jagung", () -> new Plant("Biji Jagung"),
            "Biji Stroberi", () -> new Plant("Biji Stroberi"));

    protected static Map<String, Supplier<Item>> allItemMap = Map.of(
            "Accelerate", () -> new Item("Accelerate"),
            "Delay", () -> new Item("Delay"),
            "Instant harvest", () -> new Item("Instant harvest"),
            "Destroy", () -> new Item("Destroy"),
            "Protect", () -> new Item("Protect"),
            "Trap", () -> new Item("Trap"));

    protected static Map<String, Supplier<HerbivoreFood>> allHerbivoreFoodMap = Map.of(
            "Jagung", () -> new HerbivoreFood("Jagung", 150, "Herbivore", 3),
            "Labu", () -> new HerbivoreFood("Labu", 500, "labu.img", 10),
            "Stroberi", () -> new HerbivoreFood("Stroberi", 350, "Herbivore", 5));

    protected static Map<String, Supplier<CarnivoreFood>> allCarnivoreFoodMap = Map.of(
            "Sirip Hiu", () -> new CarnivoreFood("Sirip Hiu", 500, "Carnivore", 12),
            "Susu", () -> new CarnivoreFood("Susu", 100, "Carnivore", 4),
            "Daging Domba", () -> new CarnivoreFood("Daging Domba", 120, "Carnivore", 6),
            "Daging Kuda", () -> new CarnivoreFood("Daging Kuda", 150, "Carnivore", 8),
            "Telur", () -> new CarnivoreFood("Telur", 50, "Carnivore", 2),
            "Daging Beruang", () -> new CarnivoreFood("Daging Beruang", 500, "Carnivore", 12));

    protected static Map<String, Supplier<? extends Card>> allCardMap = Map.ofEntries(
            entry("Sapi", () -> new Herbivore("Sapi")),
            entry("Domba", () -> new Herbivore("Domba")),
            entry("Kuda", () -> new Herbivore("Kuda")),
            entry("Hiu Darat", () -> new Carnivore("Hiu Darat")),
            entry("Ayam", () -> new Omnivore("Ayam")),
            entry("Beruang", () -> new Omnivore("Beruang")),
            entry("Biji Labu", () -> new Plant("Biji Labu")),
            entry("Biji Jagung", () -> new Plant("Biji Jagung")),
            entry("Biji Stroberi", () -> new Plant("Biji Stroberi")),
            entry("Accelerate", () -> new Item("Accelerate")),
            entry("Delay", () -> new Item("Delay")),
            entry("Instant harvest", () -> new Item("Instant harvest")),
            entry("Destroy", () -> new Item("Destroy")),
            entry("Protect", () -> new Item("Protect")),
            entry("Trap", () -> new Item("Trap")),
            entry("Jagung", () -> new HerbivoreFood("Jagung", 150, "Herbivore", 3)),
            entry("Labu", () -> new HerbivoreFood("Labu", 500, "Herbivore", 10)),
            entry("Stroberi", () -> new HerbivoreFood("Stroberi", 350, "Herbivore", 5)),
            entry("Sirip Hiu", () -> new CarnivoreFood("Sirip Hiu", 500, "Carnivore", 12)),
            entry("Susu", () -> new CarnivoreFood("Susu", 100, "Carnivore", 4)),
            entry("Daging Domba", () -> new CarnivoreFood("Daging Domba", 120, "Carnivore", 6)),
            entry("Daging Kuda", () -> new CarnivoreFood("Daging Kuda", 150, "Carnivore", 8)),
            entry("Telur", () -> new CarnivoreFood("Telur", 50, "Carnivore", 2)),
            entry("Daging Beruang", () -> new CarnivoreFood("Daging Beruang", 500, "Carnivore", 12)));

    public GameMaster() {
        this.listPlayers = new ArrayList<>();
        this.currentTurn = 0;
        this.plantService = new PlantService();
        this.shop = new Shop();
        this.saveLoad = new SaveLoad();
        Player.setPlayerPlantService(plantService);
    }
    public PlantService getPlantService(){
        return plantService;
    }
    public void bearAttackProcess(Integer[] startEnd,FieldController controller, int row, int col) {
        boolean execute = true;
        controller.getDraggableMaker().removeGlowAll();
    
        // Player currPlayer = this.getCurrentPlayer();
        // for (int i = 0 ; i < row ; i++){
        //     for(int j = 0 ; j < col ; j++){
        //         try{
        //             if(currPlayer.getCardGrid(i, j).isTrap()){
        //                 execute = false;
        //                 break;
        //             }
        //         }catch(BaseException e){

        //         }

        //     }   
        // }
        // if (execute){
        //     for (int i = 0 ; i < row ; i++){
        //         for(int j = 0 ; j < col ; j++){
        //             try{
        //             if( !currPlayer.getCardGrid(i, j).isProtected()){
        //                 currPlayer.setBlankOnGrid(i, j);
        //             }
        //             }catch (BaseException e){

        //             }

        //         }   
        //     }
        // }

        // controller.getDraggableMaker().removeRedGlow(panes, row, col);
        // System.out.println("DONE DISINI");
        // controller.loadGridActiveDeck();
    


    }

    public void bearAttackTimer(Label timerLabel, FieldController controller, int row, int col) throws BaseException {
        Integer[] startEnd =  controller.simulateBearAttack(0, 0); // set red glow


        final double[] timeLeft = {2.0};  // Time in seconds for the bear attack duration
        final Timeline[] timelineWrapper = new Timeline[1];  // Wrapper to hold the timeline
        timerLabel.setText("");
        timerLabel.setVisible(true);
        timelineWrapper[0] = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            timeLeft[0] -= 0.1;  // Decrement the time
            timerLabel.setText(String.format(" %.1f seconds", timeLeft[0]));
            
            if (timeLeft[0] <= 0) {
                timelineWrapper[0].stop();
                System.out.println("BEFORE FALSE");
                timerLabel.setVisible(false);
                System.out.println("AFTER FALSE");
                bearAttackProcess(startEnd,controller, row, col);
                // Platform.runLater(() -> {
                //     try {
                //         System.out.println(row + " col : " + col);
                //         bearAttackProcess(panes,controller, row, col);


                //     } finally {
                //     }
                // });
            }
        }));
        
        timelineWrapper[0].setCycleCount(Timeline.INDEFINITE);
        timelineWrapper[0].play();
    }
    // getters
    public List<Player> getListPlayers() {
        return this.listPlayers;
    }

    public int getCurrentTurn() {
        return this.currentTurn;
    }

    public Shop getShop() {
        return this.shop;
    }

    // setters
    public void setListPlayer(List<Player> listPlayers) {
        this.listPlayers = listPlayers;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public void setPlantService(PlantService plantService) {
        this.plantService = plantService;
    }

    public SaveLoad getSaveLoad(){
        return this.saveLoad;
    }

    // other functions
    public Player getCurrentPlayer() {

        return this.listPlayers.get(this.currentTurn % 2);
    }

    public Player getPlayer(int n) {
        return this.listPlayers.get(n);
    }

    public Player getCurrentFieldPlayer() {
        return this.currentFieldPlayer;
    }

    public void setCurrentFieldPlayer(Player player) {
        this.currentFieldPlayer = player;
    }

    public void next(Label timeLabel, FieldController controller) throws BaseException {
        this.currentTurn++;
        this.bearAttack = false;
        this.plantService.increaseAgeOfPlants();
        if (random.nextBoolean()) {
            // Random random = new Random();
            // int startRow = 0;
            // int startCol = 0;
            // int count = 0;

            // while (true && count < 1000) {
            //     startRow = random.nextInt(4) + 1;
            //     startCol = random.nextInt(5) + 1; 
    
            //     // Check if the product condition is met
            //     if (startRow * startCol <= 6) {
            //         break;
            //     }
            // }
            // if (count == 1000){
            //     return;
            // }

            // this.bearAttack = true;

            // this.bearAttackTimer(timeLabel,controller,startRow,startCol);

        }       
    }
    // Random Creature

    // random
    public Carnivore generateRandomCarnivore() {
        return new Carnivore("Hiu Darat");
    }

    public Omnivore generateRandomOmnivore() {
        List<String> allOmnivoreName = List.of(
                "Ayam",
                "Beruang");

        Random random = new Random();
        int randomIndex = random.nextInt(allOmnivoreName.size());
        String omnivore = allOmnivoreName.get(randomIndex);
        return new Omnivore(omnivore);

    }

    public Herbivore generateRandomHerbivore() {
        List<String> allHerbivoreName = List.of(
                "Sapi",
                "Domba",
                "Kuda");

        Random random = new Random();
        int randomIndex = random.nextInt(allHerbivoreName.size());
        String herbivore = allHerbivoreName.get(randomIndex);
        return new Herbivore(herbivore);
    }

    public Plant generateRandomPlant() {
        List<String> allPlantName = List.of(
                "Biji Labu",
                "Biji Jagung",
                "Biji Stroberi");

        Random random = new Random();
        int randomIndex = random.nextInt(allPlantName.size());
        String plant = allPlantName.get(randomIndex);
        return new Plant(plant);
    }

    // Random ITEM
    public Item generateRandomItem() {
        List<String> allItemName = List.of(
                "Accelerate",
                "Delay",
                "Instant harvest",
                "Destroy",
                "Protect",
                "Trap");

        Random random = new Random();
        int randomIndex = random.nextInt(allItemName.size());
        String item = allItemName.get(randomIndex);
        return new Item(item);
    }

    public CarnivoreFood generateRandomCarnivoreFood() {
        List<String> allCarnivoreFoodName = List.of(
                "Sirip Hiu",
                "Susu",
                "Telur",
                "Daging Kuda",
                "Daging Domba",
                "Daging Beruang");
        Map<String, List<Integer>> carnivoreInfoMap = new HashMap<>();
        List<Integer> values = new ArrayList<>();
        values.add(500);
        values.add(12);
        carnivoreInfoMap.put("Sirip Hiu", values);
        values = new ArrayList<>();
        values.add(100);
        values.add(4);
        carnivoreInfoMap.put("Susu", values);
        values = new ArrayList<>();
        values.add(120);
        values.add(6);
        carnivoreInfoMap.put("Daging Domba", values);
        values = new ArrayList<>();
        values.add(150);
        values.add(8);
        carnivoreInfoMap.put("Daging Kuda", values);
        values = new ArrayList<>();
        values.add(50);
        values.add(2);
        carnivoreInfoMap.put("Telur", values);
        values = new ArrayList<>();
        values.add(500);
        values.add(12);
        carnivoreInfoMap.put("Daging Beruang", values);

        Random random = new Random();
        int randomIndex = random.nextInt(allCarnivoreFoodName.size());
        String carnivoreFood = allCarnivoreFoodName.get(randomIndex);
        List<Integer> info = carnivoreInfoMap.get(carnivoreFood);
        return new CarnivoreFood(carnivoreFood, info.get(0), "Carnivore", info.get(1));
    }

    public HerbivoreFood generateRandomHerbivoreFood() {
        List<String> allHerbivoreFoodName = List.of(
                "Jagung",
                "Labu",
                "Stroberi");
        Map<String, List<Integer>> herbivoreInfoMap = new HashMap<>();
        List<Integer> values = new ArrayList<>();
        values.add(150);
        values.add(3);
        herbivoreInfoMap.put("Jagung", values);
        values = new ArrayList<>();
        values.add(500);
        values.add(10);
        herbivoreInfoMap.put("Labu", values);
        values = new ArrayList<>();
        values.add(350);
        values.add(5);
        herbivoreInfoMap.put("Stroberi", values);

        Random random = new Random();
        int randomIndex = random.nextInt(allHerbivoreFoodName.size());
        String carnivoreFood = allHerbivoreFoodName.get(randomIndex);
        List<Integer> info = herbivoreInfoMap.get(carnivoreFood);
        return new HerbivoreFood(carnivoreFood, info.get(0), "Herbivore", info.get(1));
    }

    public int coordinateToIndex(String coordinate) {
        char letter = coordinate.charAt(0);
        int number = Integer.parseInt(coordinate.substring(1)) - 1;
        int row = letter - 'A';
        int col = number;
        return row * 5 + col;
    }

    public static String formatItemString(String input) {
        String lowerCaseString = input.toLowerCase();
        String[] words = lowerCaseString.split("_");
        StringBuilder formattedString = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                formattedString.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }
        return formattedString.toString().trim();
    }

    public static String saveFormatString(String input) {
        String[] words = input.split(" ");
        StringBuilder saveFormatString = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            saveFormatString.append(word.toUpperCase());
            if (i < words.length - 1) {
                saveFormatString.append("_");
            }
        }
        return saveFormatString.toString();
    }

    public void loadPlayer(int n, List<Integer> playerStatus, List<String> activeDeckString, List<String> gridString) {
        Player playerChange = getPlayer(n);
        playerChange.setGulden(playerStatus.get(0));
        playerChange.setCardDeckLeft(playerStatus.get(1));
        playerChange.emptyActiveDeck();
        for (int i = 0; i < activeDeckString.size(); i++) {
            String[] parts = activeDeckString.get(i).split(" ");
            int index = coordinateToIndex(parts[0]);
            if (index < 6) {
                Card newCard = allCardMap.get(formatItemString(parts[1])).get();
                try {
                    playerChange.addCardToActiveDeck(newCard, index);
                } catch (Exception e) {

                }
            }

        }
        
        playerChange.emptyGrid();
        for (int i = 0; i < gridString.size(); i++) {
            String[] parts = gridString.get(i).split(" ");
            System.out.println("Ini parts" + parts[0] + parts[1] + parts[2]);
            int index = coordinateToIndex(parts[0]);
            System.out.println("Ini index: " + index);
            if (index <= 19) {
                Card newCard = allCardMap.get(formatItemString(parts[1])).get();
                Creature newCreature = (Creature) newCard;
                newCreature.setWeight(Integer.parseInt(parts[2]));
                newCreature.setWeightAfterEffect(Integer.parseInt(parts[2]));
                int effectCount = Integer.parseInt(parts[3]);
                System.out.println("ngasih effect mazeh");
                for (int j = 0; j < effectCount; j++) {
                    Card newCardItem = allCardMap.get(formatItemString(parts[4 + j])).get();
                    Item newItem = (Item) newCardItem;
                    System.out.println("dikasih effect mazeh");
                    try {
                        newItem.useCard(newCreature, 0, 0);
                        System.out.println(" effect mazeh");
                    } catch (Exception e) {

                    }
                }
                System.out.println("masukkin effect mazeh");
                try {
                    playerChange.addCardToGrid(newCreature, index / 5, index % 5);

                } catch (Exception e) {

                }
            }
        }

    }

    public void load(String folderPath, String type) {
        try {
            List<String> currentShopItems = new ArrayList<>();
            List<Integer> playerStatus1 = new ArrayList<>();
            List<String> activeDeckString1 = new ArrayList<>();
            List<String> gridString1 = new ArrayList<>();
            List<Integer> playerStatus2 = new ArrayList<>();
            List<String> activeDeckString2 = new ArrayList<>();
            List<String> gridString2 = new ArrayList<>();
            this.currentTurn = saveLoad.Load(folderPath, type, currentShopItems,
                    playerStatus1, activeDeckString1, gridString1,
                    playerStatus2, activeDeckString2, gridString2) -1 ;

            this.shop.getStock().clear();
            for (int i = 0; i < currentShopItems.size(); i++) {
                String[] parts = currentShopItems.get(i).split(" ");
                this.shop.getStock().put(formatItemString(parts[0]), Integer.parseInt(parts[1]));
            }
            loadPlayer(0, playerStatus1, activeDeckString1, gridString1);
            loadPlayer(1, playerStatus2, activeDeckString2, gridString2);
            System.out.println(gridString2);
            this.plantService.getSubscribers().clear();
            List<Plant> plantP1 = this.listPlayers.get(0).getAllPlantsInGrid();
            List<Plant> plantP2 = this.listPlayers.get(1).getAllPlantsInGrid();
            for (int i = 0; i < plantP1.size(); i++) {
                this.plantService.getSubscribers().add(plantP1.get(i));
            }
            for (int i = 0; i < plantP2.size(); i++) {
                this.plantService.getSubscribers().add(plantP2.get(i));
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

    }

    // public void save(String folderPath, String type) {
    // int currentTurn;
    // List<String> currentShopItems = new ArrayList<>();
    // List<Integer> playerStatus1 = new ArrayList<>();
    // List<String> activeDeckString1 = new ArrayList<>();
    // List<String> gridString1 = new ArrayList<>();
    // List<Integer> playerStatus2 = new ArrayList<>();
    // List<String> activeDeckString2 = new ArrayList<>();
    // List<String> gridString2 = new ArrayList<>();

    // // loading in the stock map
    // for (Map.Entry<String, Integer> entry : this.shop.getStock().entrySet()) {
    // String item = entry.getKey() + " " + entry.getValue();
    // currentShopItems.add(item);
    // }

    // playerStatus1.add(this.getPlayer(0).getGulden());
    // playerStatus1.add(this.getPlayer(0).getCardDeckLeft());

    // for(Card card)

    // playerStatus1.add(this.getPlayer(1).getGulden());
    // playerStatus1.add(this.getPlayer(1).getCardDeckLeft());

    // }

}
