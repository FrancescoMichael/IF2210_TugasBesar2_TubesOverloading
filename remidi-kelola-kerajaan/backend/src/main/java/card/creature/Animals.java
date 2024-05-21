package card.creature;
import card.food.CarnivoreFood;
import card.food.HerbivoreFood;
import exceptionkerajaan.BaseException;

public interface Animals {
    void eat(CarnivoreFood carnivoreFood) throws BaseException;

    void eat(HerbivoreFood herbivoreFood) throws BaseException;
}
