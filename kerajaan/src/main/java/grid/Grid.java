package grid;

import java.util.ArrayList;

class Card{

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

public class Grid {
    private int row;
    private int col;
    public void set() {
    }
    public void remove() {
    }
    public void getElement() {
    }
    public String toString(){
        return "BISA MAS";
    }
}

class Field extends Grid {
    //private list kartu
    private ArrayList<Creatures> listOfCreatures;
    public void set(int row, int col, Creatures creature) {
        listOfCreatures.set(row * col, creature);
    }
    public void remove(int row, int col) {
        listOfCreatures.remove(row*col);
    }
    public Creatures getElement(int row, int col) {
        return listOfCreatures.get(row * col);
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
    public String toString(){
        return "BISA MAS";
    }

}

class ActiveDeck extends Grid {
    //private list kartu
    public void useCard() {

    }

}