package oop.Card;

public class Plant extends Creature {
    
    private int age;

    // pas dibikin agenya masi 0
    public Plant(String pathToImg, String name, int harvestGoal) {
        super(pathToImg, name, harvestGoal);
        this.age = 0;
    }

    public int getAge() {
        return age;
    }

    public void harvest() {
        System.out.println("Panenn");
    }
}
