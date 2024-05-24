package oop.gamemaster;

import java.util.*;
import java.util.function.Supplier;

import javafx.application.Platform;
import javafx.scene.control.Label;

import static java.util.Map.entry;

import oop.player.*;
import oop.saveload.SaveLoad;
import oop.observer.*;
import oop.card.creature.*;
import oop.card.item.Item;
import oop.card.product.CarnivoreFood;
import oop.card.product.HerbivoreFood;
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
    private ArrayList<Card> currentShuffle;
    private int numberOfPickedCards;

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
            entry("Instant Harvest", () -> new Item("Instant Harvest")),
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
        this.bearAttack = false;
        this.numberOfPickedCards = 0;
        this.currentShuffle = new ArrayList<>();
        Player.setPlayerPlantService(plantService);
    }

    public PlantService getPlantService() {
        return plantService;
    }

    public void bearAttackProcess(Integer[] startEnd, FieldController controller)
            throws BaseException {
        boolean execute = true;
        System.out.println("IT IS TIME TO ATTACK");
        Player currPlayer = this.getCurrentPlayer();
        controller.getDraggableMaker().removeGlowAll();
        int startRow = startEnd[0];
        int startCol = startEnd[1];
        int rowsAdd = startEnd[2];
        int colsAdd = startEnd[3];
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> cols = new ArrayList<>();
        for (int i = startRow; i < startRow + rowsAdd; i++) {
            for (int j = startCol; j < startCol + colsAdd; j++) {
                // System.out.println("ROW : " + i);
                // System.out.println("COL" + j);
                try {
                    Creature card = currPlayer.getCardGrid(i, j);
                    if (card.isTrap()) {
                        System.out.println("IS A TRAPPPP");
                        execute = false;
                        break;
                    } else if (card.isProtected()) {

                    } else {
                        rows.add(i);
                        cols.add(j);
                    }
                } catch (BaseException e) {

                }

            }
        }
        if (execute) {
            for (int i = 0; i < rows.size(); i++) {
                try {
                    currPlayer.setBlankOnGrid(rows.get(i), cols.get(i));
                } catch (BaseException e) {
                    System.out.println(e.getMessage());
                }

            }
        } else {

            currPlayer.addCardToActiveDeckFirstEmpty(new Omnivore("Beruang"));

        }
        this.bearAttack = false;
        controller.loadGridActiveDeck();

    }

    public void bearAttackTimer(Label timerLabel, FieldController controller) throws BaseException {
        Integer[] startEnd = controller.simulateBearAttack(); // Assuming this method is thread-safe

        Platform.runLater(() -> {
            timerLabel.setText("");
            timerLabel.setVisible(true);
        });

        new Thread(() -> {
            // final double[] timeLeft = {2.0}; // Time in seconds for the bear attack
            // duration
            // Randomize time left
            double timeLeft = 30 + (random.nextDouble() * (60 - 30));
            timeLeft = 10;

            try {
                while (timeLeft > 0) {
                    Thread.sleep(100); // Sleep for 1f00 milliseconds
                    timeLeft -= 0.1;
                    double finalTimeLeft = timeLeft;

                    // TODO : Possible race condition, check properly later....
                    Platform.runLater(() -> {
                        timerLabel.setText(String.format("%.1f seconds", finalTimeLeft));
                    });
                }

                Platform.runLater(() -> {
                    timerLabel.setVisible(false);
                    try {
                        bearAttackProcess(startEnd, controller);
                    } catch (BaseException e) {
                        System.out.println(e.getMessage());
                        this.bearAttack = false;
                    }

                });
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt(); // Handle thread interruption properly
            }
        }).start();
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

    public SaveLoad getSaveLoad() {
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

    public void shuffle() {
        System.out.println("SHUFFLING");
        Player currentPlayer = this.getCurrentPlayer();
        List<Map.Entry<String, Supplier<? extends Card>>> entries = new ArrayList<>(allCardMap.entrySet());
        entries.removeIf(entry -> entry.getValue().get().getName().equals("Beruang"));
        Collections.shuffle(entries);
        this.currentShuffle.clear();
        int min = 4;
        if (min > currentPlayer.getNumberOfEmptyCardsActiveDeck()) {
            min = currentPlayer.getNumberOfEmptyCardsActiveDeck();
            // System.out.println(min);
        }
        int numLeft = this.getCurrentPlayer().getCardDeckLeft();
        min = Math.min(min, numLeft);
        if (numLeft >= 0) {
            List<Map.Entry<String, Supplier<? extends Card>>> selected = entries.subList(0, min);
            for (Map.Entry<String, Supplier<? extends Card>> entry : selected) {
                Card card = entry.getValue().get();
                card.setOwner(currentPlayer);
                this.currentShuffle.add(card);
                // System.out.println(card);
            }
        }
        this.numberOfPickedCards = min;

    }

    public void doneShuffling(Label timeLabel, FieldController controller) throws BaseException {
        // System.out.println("ENTERING DONE SHUFFLING");
        Player player = this.getCurrentPlayer();

        // decrementing deck left
        this.getCurrentPlayer().decrementCardDeckLeft(this.numberOfPickedCards);
        // System.out.println("ABOUT TO ENTER TIMER BEAR");
        if (Math.random() < 0.30) {

            this.bearAttack = true;
            // System.out.println("RUNNING TIMER BEAR");
            this.bearAttackTimer(timeLabel, controller);

        }
        // System.out.println("NUMBERS OF PICKED " + this.numberOfPickedCards);
        // System.out.println("ARRAY SIZE : " + this.currentShuffle.size());
        for (Card card : this.currentShuffle) {
            player.addCardToActiveDeckFirstEmpty(card);
        }
        System.out.println("DECK LEFT: " + player.getCardDeckLeft());
        controller.loadGridActiveDeck();
    }

    public void next() throws BaseException {
        this.currentTurn++;
        this.bearAttack = false;
        this.plantService.increaseAgeOfPlants();

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

    public boolean isBearAttack() {
        return this.bearAttack;
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
                    playerStatus2, activeDeckString2, gridString2) - 1;

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

    public String indexToCoordinate(int index) {
        int row = index / 5;
        int col = index % 5;
        char letter = (char) ('A' + row);
        int number = col + 1;
        return "" + letter + number;
    }

    public void save(String folderPath, String type) {
        List<String> currentShopItems = new ArrayList<>();
        List<Integer> playerStatus1 = new ArrayList<>();
        List<String> activeDeckString1 = new ArrayList<>();
        List<String> gridString1 = new ArrayList<>();
        List<Integer> playerStatus2 = new ArrayList<>();
        List<String> activeDeckString2 = new ArrayList<>();
        List<String> gridString2 = new ArrayList<>();

        // loading in the stock map
        for (Map.Entry<String, Integer> entry : this.shop.getStock().entrySet()) {
            if (entry.getValue() > 0) {
                String item = entry.getKey().toUpperCase() + " " + entry.getValue();
                currentShopItems.add(item);
            }
        }

        playerStatus1.add(this.getPlayer(0).getGulden());
        playerStatus1.add(this.getPlayer(0).getCardDeckLeft());

        for (Card card : this.getPlayer(0).getActiveDeck()) {
            if (!card.getName().equals("")) {
                activeDeckString1
                        .add(indexToCoordinate(this.getPlayer(0).searchActiveCardIndex(card)) + " "
                                + card.getName().toUpperCase());
            }
        }

        for (Creature creature : this.getPlayer(0).getGrid()) {
            if (!creature.getName().equals("")) {
                StringBuilder effectNamesBuilder = new StringBuilder();
                int numOfEffect = 0;
                for (Item item : creature.getItemEffects()) {
                    effectNamesBuilder.append(item.getName().toUpperCase()).append(" ");
                    numOfEffect++;
                }
                String effectNames = effectNamesBuilder.toString().trim();
                gridString1
                        .add(indexToCoordinate(this.getPlayer(0).searchGridIndex(creature)) + " "
                                + saveFormatString(creature.getName().toUpperCase()) + " " + creature.getWeight() + " "
                                + numOfEffect
                                + " "
                                + effectNames);

            }
        }
        playerStatus2.add(this.getPlayer(1).getGulden());
        playerStatus2.add(this.getPlayer(1).getCardDeckLeft());

        for (Card card : this.getPlayer(1).getActiveDeck()) {
            if (!card.getName().equals("")) {
                activeDeckString2
                        .add(indexToCoordinate(this.getPlayer(1).searchActiveCardIndex(card)) + " "
                                + card.getName().toUpperCase());
            }

        }

        for (Creature creature : this.getPlayer(1).getGrid()) {
            if (!creature.getName().equals("")) {
                StringBuilder effectNamesBuilder = new StringBuilder();
                int numOfEffect = 0;
                for (Item item : creature.getItemEffects()) {
                    effectNamesBuilder.append(item.getName().toUpperCase()).append(" ");
                    numOfEffect++;
                }
                String effectNames = effectNamesBuilder.toString().trim();
                gridString2
                        .add(indexToCoordinate(this.getPlayer(1).searchGridIndex(creature)) + " "
                                + saveFormatString(creature.getName().toUpperCase()) + " " + creature.getWeight() + " "
                                + numOfEffect
                                + " "
                                + effectNames);

            }
        }
        try {
            saveLoad.Save(folderPath, type, this.currentTurn, currentShopItems, playerStatus1, activeDeckString1,
                    gridString1,
                    playerStatus2, activeDeckString2, gridString2);
        } catch (Exception e) {
            // TODO: handle exceptionf
        }

    }

    public ArrayList<Card> getCurrentShuffle() {
        return this.currentShuffle;
    }

}
