package exceptionkerajaan;

import card.*;

public class FoodException extends BaseException{
    public FoodException(Animals creature){
        super("Food type is not compatible");
    }
    
}
