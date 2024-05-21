package oop.Grid;

import java.util.ArrayList;

class Card {

}

class Creatures extends Card {
    public void harvest() {

    }
    public void eat() {

    }
    public void grow() {

    }
}

interface activeCreature {
    public void eat();
}
interface passiveCreature {
    public void grow();
}

class Animal extends Creatures implements activeCreature{
    public void eat() {
        System.out.println("Makann");
    }
}

class Plant extends Creatures implements passiveCreature{
    public void grow() {
        System.out.println("Tumbuhh");
    }
}

class Field {
    private int row;
    private int col;
    //private list kartu
    private ArrayList<ArrayList<Creatures>> listOfCreatures;
    public void set(int row, int col, Creatures creature) {
        listOfCreatures.get(row).set(col, creature);
    }
    public void remove(int row, int col) {
        listOfCreatures.get(row).remove(col);
    }
    public Creatures getElement(int row, int col) {
        return listOfCreatures.get(row).get(col);
    }
    public void harvestCreature(int row, int col) {
        getElement(row, col).harvest();
        remove(row, col);
    }
    public void growPlant(int row, int col) {
        getElement(row, col).grow();
        // ((passiveCreature) getElement(0, 0)).grow();
        
    }
    public void eat(int row, int col) {
        getElement(row, col).eat();
        
    }
    
}

class ActiveDeck {
    //private list kartu
    private int row;
    private int col;
    public void useCard() {
        
    }

}