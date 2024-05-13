package oop.Card;

abstract class Creature extends Card {

    private int harvestGoal;

    public Creature(String pathToImg, String name, int harvestGoal) {
        super(pathToImg, name);
        this.harvestGoal = harvestGoal;
    }

    public int getHarvestGoal() {
        return harvestGoal;
    }

    abstract void harvest();

}
