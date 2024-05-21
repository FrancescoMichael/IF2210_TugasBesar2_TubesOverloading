package card.creature;
import card.product.Product;
import player.Player;

public class Plant extends Creature {

    // constructors
    public Plant() {
        super();
    }
    public Plant(String name, String pathToImg, Player owner) {
        super(name, pathToImg, owner);
    }

    public Plant(String name, String pathToImg) {
        super(name, pathToImg);
    }

    
    @Override
    public void increaseWeight(int weight){
        super.increaseWeight(weight);
        System.out.println("UPDATING PICTURE");
        if ( this.weightAfterEffect >  this.harvestedWeightRequirement){
            Product tmp  = this.getHarvestedProduct();
            // Update picture
            this.setPathToImg(tmp.getPathToImg());
        }


    }
}
