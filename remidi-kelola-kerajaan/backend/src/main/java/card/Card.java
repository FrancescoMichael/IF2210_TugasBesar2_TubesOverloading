package card;
import player.Player;

public class Card {
    protected String name;
    protected String pathToImg;
    protected Player owner;
    // private Effect effect; // strategy design pattern

    // contructor
    public Card(){
        this.name = "";

        this.pathToImg = "";
        this.owner = new Player("");
    }

    public Card(String name,String pathToImg, Player owner){
        this.name = name;
        this.pathToImg = pathToImg;
        this.owner = owner;
    }

    public Card(String name , String pathToImg){
        this.name = name;
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


    public void setOwner(Player owner){
        this.owner = owner;
    }

    // other methods
    public String toString(){
        String temp  = "";
        temp = temp + "name: " + this.name +"\n";
        temp = temp + "Owner: " + this.owner.getName() + "\n";
        
        return temp;
    }


    public boolean isEmpty(){
        return this.name.compareTo("") == 0;
    }
}
