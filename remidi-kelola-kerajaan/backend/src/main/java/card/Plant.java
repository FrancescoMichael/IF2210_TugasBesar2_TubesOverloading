package card;

public class Plant extends Card{
    private int incrementAge;

    // constructors
    public Plant(){
        this.incrementAge = 0;
    }

    public Plant(String name, int price, String pathToImg, int incrementAge){
        super(name,price,pathToImg);
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
