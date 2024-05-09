package card;

public class Card {
    private String name;
    private int price;
    private String pathToImg;




    // contructor
    public Card(){
        this.name = "";
        this.price = 0;
        this.pathToImg = "";
    }

    public Card(String name,int price, String pathToImg){
        this.name = name;
        this.price = price; 
        this.pathToImg = pathToImg;
    
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


    // other methods
    public String toString(){
        String temp  = "";
        temp = temp + "name: " + this.name +"\n";
        temp = temp + "price: " +this.price +"\n";
        return temp;
    }
}
