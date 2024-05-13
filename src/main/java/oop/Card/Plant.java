package oop.Card;

public class Plant extends Creature {

    // pas dibikin agenya masi 0
    public Plant(String pathToImg, String name, int harvestGoal) {
        super(pathToImg, name, harvestGoal, 0);
    }

    public int getAge() {
        return this.getHarvestProgress();
    }

    public void harvest() {
        System.out.println("Panenn");
    }
}
