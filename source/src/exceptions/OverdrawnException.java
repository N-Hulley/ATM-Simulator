package exceptions;
/**
 * Thrown when an account tries to go over the daily limit
 * 
 * @author  Nick Hulley
 */
public class OverdrawnException extends TransactionFailedException{
    
    public OverdrawnException(String errorMessage, String errorCode) {
        super(errorMessage, errorCode);
    }
    
}
