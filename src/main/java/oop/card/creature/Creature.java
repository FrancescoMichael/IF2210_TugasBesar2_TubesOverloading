package oop.card.creature;

import java.util.*;

import oop.card.Card;
import oop.card.UsableCard;
import oop.card.product.CarnivoreFood;
import oop.card.product.HerbivoreFood;
import oop.card.product.Product;
import oop.player.Player;
import oop.exceptionkerajaan.*;
import oop.card.item.Item;

// creature includes plants and animals
public class Creature extends Card implements UsableCard {
    protected static Map<String, Product> allHarvestedProduct = Map.of(
            "Hiu Darat", new CarnivoreFood("Sirip Hiu", 500, "Carnivore", 12),
            "Sapi", new CarnivoreFood("Susu", 100, "Carnivore", 4),
            "Domba", new CarnivoreFood("Daging Domba", 120, "Carnivore", 6),
            "Kuda", new CarnivoreFood("Daging Kuda", 150, "Carnivore", 8),
            "Ayam", new CarnivoreFood("Telur", 50, "Carnivore", 2),
            "Beruang", new CarnivoreFood("Daging Beruang", 500, "Carnivore", 12),
            "Biji Jagung", new HerbivoreFood("Jagung", 150, "Herbivore", 3),
            "Biji Labu", new HerbivoreFood("Labu", 500, "labu.img", 10),
            "Biji Stroberi", new HerbivoreFood("Stroberi", 350, "Herbivore", 5));

    protected static Map<String, Integer> allHarvestedWeightRequirement = Map.of(
            "Hiu Darat", 20,
            "Sapi", 10,
            "Domba", 12,
            "Kuda", 14,
            "Ayam", 5,
            "Beruang", 25,
            "Biji Jagung", 3,
            "Biji Labu", 5,
            "Biji Stroberi", 4);

    // private Product harvestedProduct;
    protected int harvestedWeightRequirement;
    protected int weight;
    protected int weightAfterEffect;
    protected List<Item> itemEffects;
    protected boolean trap;
    protected boolean protect;
    // effect attribute ?

    // constructor

    public Creature() {
        super();
        this.harvestedWeightRequirement = 0;
        this.weight = 0;
        this.weightAfterEffect = 0;
        this.itemEffects = new ArrayList<>();
        this.trap = false;
        this.protect = false;
    }

    // creature with owner
    public Creature(String name, Player owner) {

        super(name, owner);
        this.weight = 0;
        this.weightAfterEffect = 0;
        if (Creature.allHarvestedProduct.containsKey(name)) {
            this.harvestedWeightRequirement = Creature.allHarvestedWeightRequirement.get(name);
        } else {
            this.harvestedWeightRequirement = 0;
        }
        this.trap = false;
        this.protect = false;
        this.itemEffects = new ArrayList<>();

    }

    // creature without owner
    public Creature(String name) {
        super(name);
        this.weight = 0;
        this.weightAfterEffect = 0;
        if (Creature.allHarvestedProduct.containsKey(name)) {
            this.harvestedWeightRequirement = Creature.allHarvestedWeightRequirement.get(name);
        } else {
            this.harvestedWeightRequirement = 0;
        }
        this.itemEffects = new ArrayList<>();
        this.trap = false;
        this.protect = false;

        // this.harvestedProduct = Creature.allHarvestedProduct.get(name);
    }

    // getter
    public Product getHarvestedProduct() {

        Product pTemp = Creature.allHarvestedProduct.get(this.getName());
        if (pTemp instanceof CarnivoreFood) {
            pTemp = new CarnivoreFood((CarnivoreFood) pTemp);

        } else if (pTemp instanceof HerbivoreFood) {
            pTemp = new HerbivoreFood((HerbivoreFood) pTemp);
        }
        pTemp.setOwner(this.owner);
        return pTemp;

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

    public List<Item> getItemEffects() {
        return this.itemEffects;
    }

    public boolean isTrap() {
        return this.trap;
    }

    public boolean isProtected() {
        return this.protect;
    }

    public static Map<String, Product> getAllHarvestedProduct() {
        return Creature.allHarvestedProduct;
    }

    public static Map<String, Integer> getallHarvestedWeightRequirement() {
        return Creature.allHarvestedWeightRequirement;
    }

    // Setter
    public void setHarvestedWeightRequirement(int harvestedWeightRequirement) {
        this.harvestedWeightRequirement = harvestedWeightRequirement;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setWeightAfterEffect(int weightAfterEffect) {
        this.weightAfterEffect = weightAfterEffect;
    }

    public void setItemEffects(List<Item> item) {
        this.itemEffects = item;
    }

    public void setTrap(boolean trap) {
        this.trap = trap;
    }

    public void setProtected(boolean protect) {
        this.protect = protect;
    }

    // other functions

    public void increaseWeight(int additionalWeight) {
        this.weight += additionalWeight;
        this.weightAfterEffect += additionalWeight;
        if (this.weight  < 0){
            this.weight = 0;
        }
        if (this.weightAfterEffect < 0){
            this.weightAfterEffect = 0;
        }
    }

    public void increaseWeightAfterEffect(int additionalWeight) {
        this.weightAfterEffect += additionalWeight;
        if (this.weightAfterEffect < 0){
            this.weightAfterEffect = 0;
        }
    }

    public void addEffect(Item effect) {
        this.itemEffects.add(effect);
    }

    public void placeCardtoGrid(Card targetCard, int row, int col) throws BaseException {
        if (targetCard.isEmpty() && targetCard.getOwner() == this.getOwner()) {
            // place card to grid at row and col

        } else {
            throw new InvalidCardPlacementException();
        }
    }

    @Override
    public void useCard(Card targetCard, int row, int col) throws BaseException {

        if (targetCard.isEmpty() && this.getOwner() == targetCard.getOwner()) {
            this.getOwner().addCardToGrid(this, row, col);

        } else {
            throw new InvalidCardPlacementException();
        }
    }

    public boolean isHarvestable() {
        return this.getWeightAfterEffect() >= this.getHarvestedWeightRequirement();
    }
    
    // get harvested product from Creature
    public void harvestCreature(int row, int col) throws BaseException {
        if (isHarvestable()) {
            // remove creature from grid, add product in active deck
            // addCardToActiveDeck already checks if activeDeck is full

            this.getOwner().addCardToActiveDeckFirstEmpty(this.getHarvestedProduct());
            this.getOwner().setBlankOnGrid(row, col);

        } else {
            throw new NotReadyToHarvest();
        }
    }

    @Override
    public String toString() {
        String temp = super.toString();
        temp = temp + "Weight: " + this.getWeight() + "\n" + "Weight after effect: " + this.getWeightAfterEffect()
                + "\n" + "Weight Requirement: " + this.getHarvestedWeightRequirement() + "\n" + "All Effects: [";
        for (int i = 0; i < this.itemEffects.size(); i++) {
            temp += this.itemEffects.get(i).getName();
            if (i + 1 != this.itemEffects.size()) {
                temp += ", ";
            }
        }

        temp = temp + "]\n";
        return temp;

    }

}
