package exceptionkerajaan;

public class BlankCardException extends BaseException {
    public BlankCardException(){
        super("Can't drag card to a blank card");
    }
}
