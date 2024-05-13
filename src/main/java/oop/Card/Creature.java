package oop.Card;

abstract class Creature extends Card {

    private int harvestGoal;
    private int harvestProgress; // age or weightnya

    public Creature(String pathToImg, String name, int harvestGoal, int harvestProgress) {
        super(pathToImg, name);
        this.harvestGoal = harvestGoal;
        this.harvestProgress = harvestProgress;
    }

    public int getHarvestGoal() {
        return harvestGoal;
    }

    public int getHarvestProgress() {
        return harvestProgress;
    }

    abstract void harvest();

}
