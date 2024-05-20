package card.creature;

import card.food.CarnivoreFood;
import card.food.HerbivoreFood;

public interface Animals {
    void eat(CarnivoreFood carnivoreFood);

    void eat(HerbivoreFood herbivoreFood);
}
