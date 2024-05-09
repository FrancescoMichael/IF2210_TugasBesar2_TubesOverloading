package card;

public class Item extends Card{
    private String effect;


    // constructors
    public Item(String name,int price, String pathToImg, String effect){
        super(name,price,pathToImg);
        this.effect = effect;
    }


    // getters
    public String getEffect(){
        return this.effect;
    }


    // setters



}