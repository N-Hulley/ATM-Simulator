
package exceptions;


/**
 *   Exception that is thrown when an account is not found during a search,
 *
 *    Date : 20190125
 *    @author Nick Hulley
 */
public class AccountNotFoundException extends Exception {
    private String errorCode;
    private String accountNumber;

/**
 * @
 * @param errorMessage This is a message describing the error
 * @param errorCode This is a error code to trace back to the instance of the error
 * @param accountNumber This is the account number that could not be found
 */
    public AccountNotFoundException(String errorMessage, String errorCode, String accountNumber) {
        super();
        this.errorCode = errorCode;
        this.accountNumber = accountNumber;
    }
    
    public String getErrorcode() {
        return errorCode;
    }
    
    public String getAccountNumber() {
        return this.accountNumber;
    }
}
