package oop.gamemaster;

import java.util.*;
import java.util.function.Supplier;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import static java.util.Map.entry;

import oop.player.*;
import oop.saveload.SaveLoad;
import oop.observer.*;
import oop.card.creature.*;
import oop.card.item.Item;
import oop.card.product.CarnivoreFood;
import oop.card.product.HerbivoreFood;
import oop.exceptionkerajaan.ActiveDeckFullException;
import oop.exceptionkerajaan.PrematureBearAttackException;
import oop.exceptionkerajaan.BaseException;
import oop.FieldController;
import oop.card.*;
import oop.shop.*;

// import oop.card.product.CarnivoreFood;
// import oop.card.product.HerbivoreFood;

public class GameMaster {
    private Random random = new Random();
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

    public synchronized void bearAttackProcess(Integer[] startEnd, FieldController controller, boolean finisih)
            throws BaseException {
        if (!this.bearAttack){
            return;
        }
        boolean execute = true;
        Player currPlayer = this.getCurrentPlayer();
        if (finisih){
            controller.getDraggableMaker().removeGlowAll();
        }
        int startRow = startEnd[0];
        int startCol = startEnd[1];
        int rowsAdd = startEnd[2];
        int colsAdd = startEnd[3];
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> cols = new ArrayList<>();
        for (int i = startRow; i < startRow + rowsAdd; i++) {
            for (int j = startCol; j < startCol + colsAdd; j++) {
                try {
                    Creature card = currPlayer.getCardGrid(i, j);
                    if (card.isTrap()) {
                        execute = false;
                        break;
                    } else if (card.isProtected()) {

                    } else {
                        rows.add(i);
                        cols.add(j);
                    }
                } catch (Exception e) {

                }

            }
        }
        if (execute && finisih) {
            // bear attack semua di grid setelah timer
            for (int i = 0; i < rows.size(); i++) {
                try {
                    currPlayer.setBlankOnGrid(rows.get(i), cols.get(i));
                } catch (BaseException e) {
                    System.out.println(e.getMessage());
                }

            }
        } else {
                // Kena trap
            if (!execute){
                if (!finisih){
                    controller.getDraggableMaker().removeGlowAll();
                }
                currPlayer.addCardToActiveDeckFirstEmpty(new Omnivore("Beruang"));
                this.bearAttack = false;
                controller.loadGridActiveDeck();
                if (!finisih){
                    throw new PrematureBearAttackException();
                }


            }

        }
        if (finisih){
            this.bearAttack = false;
            controller.loadGridActiveDeck();
        }

    }

    public void bearAttackTimer(Label timerLabel, FieldController controller, ImageView clock) throws BaseException {
        Integer[] startEnd = controller.simulateBearAttack(); // Assuming this method is thread-safe
        this.bearAttack = true;
        Platform.runLater(() -> {
            timerLabel.setText("");
            timerLabel.setVisible(true);
            clock.setVisible(true);
        });

        new Thread(() -> {
            double timeLeft = 30 + (random.nextDouble() * (60 - 30));
            try {

                while (timeLeft > 0 && this.bearAttack) {
                    Thread.sleep(100); // Sleep for 100 milliseconds
                    timeLeft -= 0.1;
                    double finalTimeLeft = timeLeft;
                    Platform.runLater(() -> {
                        timerLabel.setText(String.format("%.1f seconds", finalTimeLeft));
                        try {
                            bearAttackProcess(startEnd, controller, false);
                        } catch (BaseException e) {
                            System.out.println(e.getMessage());
                            if (e instanceof ActiveDeckFullException || e instanceof PrematureBearAttackException) {
                                this.bearAttack = false;
                                timerLabel.setVisible(false);
                                System.out.println(e.getMessage());
                            }
                        }
                    });
                }
        
                // Final call to make UI changes and possibly a final bear attack process call
                Platform.runLater(() -> {
                    timerLabel.setVisible(false);
                    clock.setVisible(false);
                    if (this.bearAttack) {
                        try {
                            bearAttackProcess(startEnd, controller, true);
                        } catch (BaseException e) {
                            System.out.println(e.getMessage());
                            this.bearAttack = false;
                        }
                    }
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
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
        Player currentPlayer = this.getCurrentPlayer();
        List<Map.Entry<String, Supplier<? extends Card>>> entries = new ArrayList<>(allCardMap.entrySet());
        entries.removeIf(entry -> entry.getValue().get().getName().equals("Beruang"));
        Collections.shuffle(entries);
        this.currentShuffle.clear();
        int min = 4;
        if (min > currentPlayer.getNumberOfEmptyCardsActiveDeck()) {
            min = currentPlayer.getNumberOfEmptyCardsActiveDeck();
        }
        int numLeft = this.getCurrentPlayer().getCardDeckLeft();
        min = Math.min(min, numLeft);
        if (numLeft >= 0) {
            List<Map.Entry<String, Supplier<? extends Card>>> selected = entries.subList(0, min);
            for (Map.Entry<String, Supplier<? extends Card>> entry : selected) {
                Card card = entry.getValue().get();
                card.setOwner(currentPlayer);
                this.currentShuffle.add(card);
            }
        }
        this.numberOfPickedCards = min;

    }

    public void doneShuffling(Label timeLabel, FieldController controller, ImageView clock) throws BaseException {
        Player player = this.getCurrentPlayer();

        // decrementing deck left
        this.getCurrentPlayer().decrementCardDeckLeft(this.numberOfPickedCards);
        if (Math.random() < 0.25) {

            this.bearAttack = true;
            controller.BearMediaPlayerPlay();
            this.bearAttackTimer(timeLabel, controller, clock);

        }

        for (Card card : this.currentShuffle) {
            player.addCardToActiveDeckFirstEmpty(card);
        }
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
            int index = coordinateToIndex(parts[0]);
            if (index <= 19) {
                Card newCard = allCardMap.get(formatItemString(parts[1])).get();
                Creature newCreature = (Creature) newCard;
                newCreature.setWeight(Integer.parseInt(parts[2]));
                newCreature.setWeightAfterEffect(Integer.parseInt(parts[2]));
                int effectCount = Integer.parseInt(parts[3]);
                for (int j = 0; j < effectCount; j++) {
                    Card newCardItem = allCardMap.get(formatItemString(parts[4 + j])).get();
                    Item newItem = (Item) newCardItem;
                    try {
                        newItem.useCard(newCreature, 0, 0);
                    } catch (Exception e) {
                    }
                }
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

            this.shop.resetStock();
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
                String item = saveFormatString(entry.getKey().toUpperCase()) + " " + entry.getValue();
                currentShopItems.add(item);
            }
        }

        playerStatus1.add(this.getPlayer(0).getGulden());
        playerStatus1.add(this.getPlayer(0).getCardDeckLeft());

        for (Card card : this.getPlayer(0).getActiveDeck()) {
            if (!card.getName().equals("")) {
                activeDeckString1
                        .add(indexToCoordinate(this.getPlayer(0).searchActiveCardIndex(card)) + " "
                                + saveFormatString(card.getName().toUpperCase()));
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
                                + saveFormatString(card.getName().toUpperCase()));
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

    public Player getWinner() {
        if (this.listPlayers.get(0).getGulden() > this.listPlayers.get(1).getGulden()) {
            return this.listPlayers.get(0);
        } else {
            return this.listPlayers.get(1);
        }
    }

}
