package oop.card.creature;
import oop.card.product.CarnivoreFood;
import oop.card.product.HerbivoreFood;
import oop.exceptionkerajaan.BaseException;

public interface Animals {
    void eat(CarnivoreFood carnivoreFood) throws BaseException;

    void eat(HerbivoreFood herbivoreFood) throws BaseException;
}
