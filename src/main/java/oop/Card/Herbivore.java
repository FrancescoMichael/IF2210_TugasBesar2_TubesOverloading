package oop.Card;

public class Herbivore extends Creature implements Animal {

    // pas dibikin weight masi 0
    public Herbivore(String pathToImg, String name, int harvestGoal) {
        super(pathToImg, name, harvestGoal, 0);
    }

    public int getWeight() {
        return this.getHarvestProgress();
    }

    public void harvest() {
        System.out.println("Panenn");
    }

    public void eat() {
        System.out.println("Makann");
    }
}