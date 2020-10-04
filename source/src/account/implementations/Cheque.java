/*
    Cheque account offers no interest
    and it also has no daily withdrawal limit.

    Date : 20181208
    Author : Nick Hulley
 */
package account.implementations;

import account.abstracts.Account;
import account.metadata.User;

public class Cheque extends Account {
    
    public Cheque(String pinCode, User holder, float balance, String bsb) 
    // Overload constructor function
    {
        super(pinCode, holder, balance, bsb);
        this.type = "Cheque";
    }
    
}
