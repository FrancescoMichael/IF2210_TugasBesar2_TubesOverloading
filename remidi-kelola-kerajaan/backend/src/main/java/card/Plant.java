package card;

import player.Player;

public class Plant extends Card{
    private int incrementAge;

    // constructors
    public Plant(){
        this.incrementAge = 0;
    }

    public Plant(String name, int price, String pathToImg, Player owner,int incrementAge){
        super(name,price,pathToImg,owner);
        this.incrementAge = incrementAge;
    }

    // getters
    public int getIncrementAge(){
        return this.incrementAge;
    }

    // setter
    public void setIncrementAge(int incrementAge){
        this.incrementAge = incrementAge;
    }
}
