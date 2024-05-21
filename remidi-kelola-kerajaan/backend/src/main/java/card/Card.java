package card;
import player.Player;

public class Card {
    private String name;
    private int price;
    private String pathToImg;
    private Player owner;
    // private Effect effect; // strategy design pattern

    // contructor
    public Card(){
        this.name = "";
        this.price = 0;
        this.pathToImg = "";
        this.owner = new Player("");
    }

    public Card(String name,int price, String pathToImg, Player owner){
        this.name = name;
        this.price = price; 
        this.pathToImg = pathToImg;
        this.owner = owner;
    }

    public Card(String name, int price, String pathToImg){
        this.name = name;
        this.price = price;
        this.pathToImg = pathToImg;
        this.owner = new Player("");
    }

    // getters

    public String getName(){
        return this.name;
    }

    public String getPathToImg(){
        return this.pathToImg;
    }

    public int getPrice(){
        return this.price;
    }

    public Player getOwner(){
        return this.owner;
    }


    // setters
    public void setName(String name){
        this.name = name;
    }

    public void setPathToImg(String pathToImg){
        this.pathToImg = pathToImg;
    }

    public void setPrice(int price){
        this.price = price;
    }


    public void setOwner(Player owner){
        this.owner = owner;
    }

    // other methods
    public String toString(){
        String temp  = "";
        temp = temp + "name: " + this.name +"\n";
        temp = temp + "price: " +this.price +"\n";
        return temp;
    }





}
