package exceptions;

/**
 * Exception that is thrown when an incorrect pin is found
 * 
 * Date : 20190125
 * @author Nick Hulley
 */
public class IncorrectPinException extends Exception {
    private String errorCode;
    
    public IncorrectPinException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
    
    public String getErrorcode() {
        return errorCode;
    }
}
