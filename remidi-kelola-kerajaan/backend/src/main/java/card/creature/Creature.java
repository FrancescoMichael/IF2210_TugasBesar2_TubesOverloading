package card.creature;

import java.util.*;

import card.Card;
import card.UsableCard;
import card.product.CarnivoreFood;
import card.product.HerbivoreFood;
import card.product.Product;
import player.Player;
import exceptionkerajaan.*;
import card.item.Item;



// creature includes plants and animals
public class Creature extends Card implements UsableCard {
    private static Map<String, Product> allHarvestedProduct;
    private static Map<String, Integer> allHarvestedWeightRequirement;
    // private Product harvestedProduct;
    private int harvestedWeightRequirement;
    private int weight;
    private int weightAfterEffect;
    private List<Item> itemEffects;
    // effect attribute ?

    // constructor

    public Creature() {
        super();
        this.harvestedWeightRequirement = 0;
        this.weight = 0;
        this.weightAfterEffect = 0;
        this.itemEffects = new ArrayList<>();
    }

    public Creature(String name, String pathToImg, Player owner) {

        super(name, pathToImg, owner);

        this.harvestedWeightRequirement = Creature.allHarvestedWeightRequirement.get(name);

        // Possibility of null value, be careful
        // this.harvestedProduct.setOwner(owner);
        this.weight = 0;
        this.weightAfterEffect = 0;
        this.harvestedWeightRequirement = Creature.allHarvestedWeightRequirement.get(name);
        // this.harvestedProduct = Creature.allHarvestedProduct.get(name);
    }

    public Creature(String name, String pathToImg) {

        super(name, pathToImg);

        this.harvestedWeightRequirement = Creature.allHarvestedWeightRequirement.get(name);

        // Possibility of null value, be careful
        // this.harvestedProduct.setOwner(owner);
        this.weight = 0;
        this.weightAfterEffect = 0;
        this.harvestedWeightRequirement = Creature.allHarvestedWeightRequirement.get(name);
        // this.harvestedProduct = Creature.allHarvestedProduct.get(name);
    }

    // getter
    public Product getHarvestedProduct() {
        
        Product pTemp = Creature.allHarvestedProduct.get(this.getName());
        if (pTemp instanceof CarnivoreFood){
            pTemp = new CarnivoreFood( (CarnivoreFood)pTemp);

        } else if (pTemp instanceof HerbivoreFood){
            pTemp = new HerbivoreFood( (HerbivoreFood)pTemp);
        }
        pTemp.setOwner(this.owner);
        return pTemp;
        
    }
    
    // setter
    // public void setHarvestedProduct(Product copyProduct) {
    //     this.harvestedProduct = new Product(copyProduct);
    //     this.harvestedProduct.setOwner(getOwner());
    // }

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

    public void increasweightAfterEffect(int additionalWeight){
        this.weightAfterEffect += additionalWeight;
    }

    public void addEffect(Item effect){
        this.itemEffects.add(effect);
    }

    // public boolean isProtected(){

    // }



    public void placeCardtoGrid(Card targetCard, int row, int col) throws BaseException{
        if (targetCard.getName() == "" && targetCard.getOwner() == this.getOwner() ){
            // place card to grid at row and col

        } else {
            throw new InvalidCardPlacementException();
        }
    }

    @Override
    public void useCard(Card targetCard, int row, int col) throws BaseException{
        if (targetCard.getName() == "" && this.getOwner() == targetCard.getOwner()){
            this.getOwner().addCardToGrid(this, row, col);
        }
    }



    
}
