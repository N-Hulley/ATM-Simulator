/*
    Can be added to an account that needs to be able to be closed
    When an account is closed, all meaningful information is lost

    This is not shown in the main function as it is not required of the project

    Date : 20181208
    Author : Nick Hulley
 */
package account.interfaces;

/**
 *
 * @author Nick
 */
public interface Closable {
    public void close();
}
