package card;

public class Product extends Card{
    private String type;
    private int additionalWeight;

    // constructors
    public Product(){
        super();
    }
    public Product(String name, int price, String pathToImg, String type, int additionalWeight){
        super(name,price,pathToImg);
        this.type = type;
        this.additionalWeight = additionalWeight;
    }

    public Product(Product copyProduct){
        super(copyProduct.getName(),copyProduct.getPrice(),copyProduct.getType());
        this.type = copyProduct.getType();
    }

    // getters
    public String getType(){
        return this.type;
    }

    public int getAdditionalWeight(){
        return this.additionalWeight;
    }


    // setters
    public void setType(String type){
        this.type = type;
    }

    public void setAdditionalWeight(int additionalWeight){
        this.additionalWeight = additionalWeight;
    }
}