package card.creature;

import java.util.*;

import card.Card;
import card.Product;
import card.food.CarnivoreFood;
import card.food.HerbivoreFood;
import player.Player;
import exceptionkerajaan.*;


// creature includes plants and animals
public class Creature extends Card {
    private static Map<String, Product> allHarvestedProduct;
    private static Map<String, Integer> allHarvestedWeightRequirement;
    private Product harvestedProduct;
    private int harvestedWeightRequirement;
    private int weight;
    private int weightAfterEffect;
    // effect attribute ?

    // constructor

    public Creature() {
        super();
    }

    public Creature(String name, int price, String pathToImg, Player owner) {
        super(name, price, pathToImg, owner);
        this.harvestedWeightRequirement = Creature.allHarvestedWeightRequirement.get(name);

        // Possibility of null value, be careful
        this.harvestedProduct = new Product(Creature.allHarvestedProduct.get(name));
        this.harvestedProduct.setOwner(owner);
        this.weight = 0;
        this.weightAfterEffect = 0;
        this.harvestedWeightRequirement = Creature.allHarvestedWeightRequirement.get(name);
        this.setHarvestedProduct(Creature.allHarvestedProduct.get(name));
    }

    // getter
    public Product getHarvestedProduct() {
        return this.harvestedProduct;
    }

    // setter
    public void setHarvestedProduct(Product copyProduct) {
        this.harvestedProduct = new Product(copyProduct);
        this.harvestedProduct.setOwner(getOwner());
    }

    public int getHarvestedWeightRequirement() {
        return this.harvestedWeightRequirement;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getWeightAfterEffect() {
        return this.weightAfterEffect;
    }

    // other functions
    public static void initializeAllCreatureStaticVariables() {
        allHarvestedProduct = Map.of(
                "Hiu Darat", new CarnivoreFood("Sirip Hiu", 500, "dummy.img", "Carnivore", 12),
                "Sapi", new CarnivoreFood("Susu", 100, "dummy.img", "Carnivore", 4),
                "Domba", new CarnivoreFood("Daging Domba", 120, "dummy.img", "Carnivore", 6),
                "Kuda", new CarnivoreFood("Daging Kuda", 150, "dummy.img", "Carnivore", 8),
                "Ayam", new CarnivoreFood("Telur", 50, "dummy.img", "Carnivore", 2),
                "Beruang", new CarnivoreFood("Daging Beruang", 500, "dummy.img", "Carnivore", 12),
                "Biji Jagung", new HerbivoreFood("Jagung", 150, "dummy.img", "Herbivore", 3),
                "Biji Labu", new HerbivoreFood("Labu", 500, "dummy.img", "Herbivore", 10),
                "Biji Stroberi", new HerbivoreFood("Stroberi", 350, "dummy.img", "Herbivore", 5));

        allHarvestedWeightRequirement = Map.of(
                "Hiu Darat", 20,
                "Sapi", 10,
                "Domba", 12,
                "Kuda", 14,
                "Ayam", 5,
                "Beruang", 25,
                "Biji Jagung", 3,
                "Biji Labu", 5,
                "Biji Stroberi", 4);

    }

    public void increaseWeight(int additionalWeight){
        this.weight += additionalWeight;
        this.weightAfterEffect += additionalWeight;
    }


    public void placeCardtoGrid(Card targetCard, int row, int col) throws BaseException{
        if (targetCard.getName() == "" && targetCard.getOwner() == this.getOwner() ){
            // place card to grid at row and col

        } else {
            throw new InvalidCardPlacementException();
        }
    }

    
}
