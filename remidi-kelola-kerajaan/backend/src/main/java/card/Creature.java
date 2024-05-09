package card;
import java.util.*;
public class Creature extends Card{
    private static Map<String, Product> allHarvestedProduct ;
    private static Map<String, Integer> allHarvestedWeightRequirement;
    private Product harvestedProduct;
    private int harvestedWeightRequirement;
    
    // constructor

    public Creature(){
        super();
    }
    public Creature(String name, int price, String pathToImg){
        super(name,price,pathToImg);
        this.harvestedWeightRequirement = Creature.allHarvestedWeightRequirement.get(name);
        
        // Possibility of null value, be careful
        this.harvestedProduct = new Product(Creature.allHarvestedProduct.get(name));
    }

    // getter
    public Product getHarvestedProduct(){
        return this.harvestedProduct;
    }

    // setter
    public void setHarvestedProduct(Product copyProduct){
        this.harvestedProduct = new Product(copyProduct);
    }

    public int getHarvestedWeightRequirement(){
        return this.harvestedWeightRequirement;
    }

    // other functions
    public static void initializeAllCreatureStaticVariables() {
        allHarvestedProduct = Map.of(
            "Hiu Darat", new Product("Sirip Hiu", 500, "dummy.img", "Carnivore", 12),
            "Sapi", new Product("Susu", 100, "dummy.img", "Carnivore", 4),
            "Domba", new Product("Daging Domba", 120, "dummy.img", "Carnivore", 6),
            "Kuda", new Product("Daging Kuda", 150, "dummy.img", "Carnivore", 8),
            "Ayam", new Product("Telur", 50, "dummy.img", "Carnivore", 2),
            "Beruang", new Product("Daging Beruang", 500, "dummy.img", "Carnivore", 12),
            "Biji Jagung", new Product("Jagung", 150, "dummy.img", "Herbivore", 3),
            "Biji Labu", new Product("Labu", 500, "dummy.img", "Herbivore", 10),
            "Biji Stroberi", new Product("Stroberi", 350, "dummy.img", "Herbivore", 5)
        );
        
        allHarvestedWeightRequirement = Map.of(
            "Hiu Darat",20,
            "Sapi",10,
            "Domba",12,
            "Kuda",14,
            "Ayam",5,
            "Beruang",25,
            "Biji Jagung",3,
            "Biji Labu",5,
            "Biji Stroberi",4
        );
    }

    public void applyItem(Item item){
        
    }


    
}
