package card.creature;
import card.product.Product;
import player.Player;

public class Plant extends Creature {

    // constructors
    public Plant() {
        super();
    }

    // with owner
    public Plant(String name, String pathToImg, Player owner) {
        super(name, pathToImg, owner);
    }

    // without owner
    public Plant(String name, String pathToImg) {
        super(name, pathToImg);
    }

    
    @Override
    public void increaseWeight(int weight){
        super.increaseWeight(weight);
        if ( this.weightAfterEffect >  this.harvestedWeightRequirement){
            Product tmp  = this.getHarvestedProduct();
            // Update picture
            this.setPathToImg(tmp.getPathToImg());
        }


    }

    @Override
    public void increaseWeightAfterEffect(int additionalWeight){
        super.increaseWeightAfterEffect(additionalWeight);
        if ( this.weightAfterEffect >  this.harvestedWeightRequirement){
            Product tmp  = this.getHarvestedProduct();
            // Update picture
            this.setPathToImg(tmp.getPathToImg());
        }

    }
}
