package card.creature;
import card.product.Product;
import player.Player;

public class Plant extends Creature {
    private String pathImgOriginal;
    // constructors
    public Plant() {
        super();
    }

    // with owner
    public Plant(String name, String pathToImg, Player owner) {
        super(name, pathToImg, owner);
        this.pathImgOriginal = pathToImg;
    }

    // without owner
    public Plant(String name, String pathToImg) {
        super(name, pathToImg);
        this.pathImgOriginal = pathToImg;
    }

    
    @Override
    public void increaseWeight(int weight){
        super.increaseWeight(weight);
        if ( this.weightAfterEffect >  this.harvestedWeightRequirement){
            Product tmp  = this.getHarvestedProduct();
            // Update picture
            this.setPathToImg(tmp.getPathToImg());
        } else {
            this.setPathToImg(this.pathImgOriginal);
        }


    }

    @Override
    public void increaseWeightAfterEffect(int additionalWeight){
        super.increaseWeightAfterEffect(additionalWeight);
        if ( this.weightAfterEffect >  this.harvestedWeightRequirement){
            Product tmp  = this.getHarvestedProduct();
            // Update picture
            this.setPathToImg(tmp.getPathToImg());
        } else {
            setPathToImg(this.pathImgOriginal);
        }

    }
}
