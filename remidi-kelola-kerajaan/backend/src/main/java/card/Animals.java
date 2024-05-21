package card;
import exceptionkerajaan.BaseException;

public interface Animals {
    void eat(CarnivoreFood carnivoreFood) throws BaseException;

    void eat(HerbivoreFood herbivoreFood) throws BaseException;
}
