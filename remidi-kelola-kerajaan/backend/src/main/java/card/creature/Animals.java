package card.creature;
import card.product.CarnivoreFood;
import card.product.HerbivoreFood;
import exceptionkerajaan.BaseException;

public interface Animals {
    void eat(CarnivoreFood carnivoreFood) throws BaseException;

    void eat(HerbivoreFood herbivoreFood) throws BaseException;
}
