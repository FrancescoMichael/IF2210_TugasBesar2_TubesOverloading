package card;
import exceptionkerajaan.*;
public interface UsableCard {
    public void useCard(Card targetCard, int row, int col) throws BaseException;
    
}