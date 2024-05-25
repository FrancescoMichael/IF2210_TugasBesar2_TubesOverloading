package oop.exceptionkerajaan;

public class InvalidSellException extends BaseException {
    public InvalidSellException() {
        super("The item is not a product");
    }
}
