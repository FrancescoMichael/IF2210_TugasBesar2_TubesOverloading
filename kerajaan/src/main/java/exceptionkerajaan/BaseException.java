package exceptionkerajaan;

public class BaseException extends Exception{
    public BaseException(){
        super("something went wrong");
    }

    public BaseException(String message){
        super(message);
    }
}