
package exceptions;

/**
 *  This error occurs when a transaction fails
 *  this could be due to withdraw limit or not enough balance
 *
 *  @date : 20190125
 *  @author : Nick Hulley
 */
public class TransactionFailedException  extends Exception {
    private String errorCode;
    
    public TransactionFailedException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
    
    public String getErrorcode() {
        return errorCode;
    }
}
