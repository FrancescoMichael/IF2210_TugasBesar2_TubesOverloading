package oop.card;
import oop.exceptionkerajaan.*;
public interface UsableCard {
    public void useCard(Card targetCard, int row, int col) throws BaseException;
    
}