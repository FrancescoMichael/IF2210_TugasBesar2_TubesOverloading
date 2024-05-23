package oop.card;

import java.util.Map;
import java.util.HashMap;

import oop.player.Player;

public class Card {
    protected String name;
    protected String pathToImg;
    protected Player owner;
    // private Effect effect; // strategy design pattern

    protected static Map<String, String> allCardImagePath = new HashMap<>();

    static {
        allCardImagePath.put("Hiu Darat", "/assets/OOP 2/OOP 2/cards/hiu_darat.png");
        allCardImagePath.put("Sapi", "/assets/OOP 2/OOP 2/cards/sapi.png");
        allCardImagePath.put("Domba", "/assets/OOP 2/OOP 2/cards/domba.png");
        allCardImagePath.put("Kuda", "/assets/OOP 2/OOP 2/cards/kuda.png");
        allCardImagePath.put("Ayam", "/assets/OOP 2/OOP 2/cards/ayam.png");
        allCardImagePath.put("Beruang", "/assets/OOP 2/OOP 2/cards/beruang.png");
        allCardImagePath.put("Biji Jagung", "/assets/OOP 2/OOP 2/cards/biji_jagung.png");
        allCardImagePath.put("Biji Labu", "/assets/OOP 2/OOP 2/cards/biji_labu.png");
        allCardImagePath.put("Biji Stroberi", "/assets/OOP 2/OOP 2/cards/biji_stroberi.png");
        allCardImagePath.put("Labu", "/assets/OOP 2/OOP 2/cards/labu.png");
        allCardImagePath.put("Jagung", "/assets/OOP 2/OOP 2/cards/jagung.png");
        allCardImagePath.put("Stroberi", "/assets/OOP 2/OOP 2/cards/stroberi.png");
        allCardImagePath.put("Susu", "/assets/OOP 2/OOP 2/cards/susu.png");
        allCardImagePath.put("Telur", "/assets/OOP 2/OOP 2/cards/telur.png");
        allCardImagePath.put("Sirip Hiu", "/assets/OOP 2/OOP 2/cards/sirip_hiu.png");
        allCardImagePath.put("Daging Kuda", "/assets/OOP 2/OOP 2/cards/daging_kuda.png");
        allCardImagePath.put("Accelerate", "/assets/OOP 2/OOP 2/cards/accelerate.png");
        allCardImagePath.put("Delay", "/assets/OOP 2/OOP 2/cards/delay.png");
        allCardImagePath.put("Instant Harvest", "/assets/OOP 2/OOP 2/cards/instant_harvest.png");
        allCardImagePath.put("Destroy", "/assets/OOP 2/OOP 2/cards/destroy.png");
        allCardImagePath.put("Protect", "/assets/OOP 2/OOP 2/cards/protect.png");
        allCardImagePath.put("Trap", "/assets/OOP 2/OOP 2/cards/trap.png");
        allCardImagePath.put("Daging Beruang", "/assets/OOP 2/OOP 2/cards/daging_beruang.png");
    }

    // contructor
    public Card() {

        this.name = "";

        this.pathToImg = null;

    }

    public Card(String name, Player owner) {
        this.name = name;
        if (allCardImagePath.containsKey(name)) {
            this.pathToImg = allCardImagePath.get(name);
        } else {
            this.pathToImg = null;
        }
        this.owner = owner;
    }

    public Card(String name) {
        this.name = name;
        // System.out.println("masuk");
        if (allCardImagePath.containsKey(name)) {
            this.pathToImg = allCardImagePath.get(name);
        } else {
            this.pathToImg = null;
        }
    }

    // getters

    public String getName() {
        return this.name;
    }

    public String getPathToImg() {

        return this.pathToImg;
    }

    public Player getOwner() {
        return this.owner;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPathToImg(String pathToImg) {
        this.pathToImg = pathToImg;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    // other methods
    public String toString() {
        String temp = "";
        temp = temp + "name: " + this.name + "\n";
        temp = temp + "Owner: " + this.owner.getName() + "\n";
        temp = temp + "pathToImg: " + this.pathToImg + "\n";

        return temp;
    }

    public boolean isEmpty() {
        return this.name.compareTo("") == 0;
    }

}
