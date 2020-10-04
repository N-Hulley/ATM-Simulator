/*
    Savings account offer interest
    which is calculated daily. Savings account have daily withdrawal
    limits. Users can set their own withdrawal limit.

    Date : 20181208
    Author : Nick Hulley
 */
package account.implementations;

import account.interfaces.Closable;
import account.abstracts.Account;
import account.abstracts.LimitedInterest;
import account.metadata.User;

public class Savings extends LimitedInterest implements Closable {

    public Savings(String pinCode, float dailyLimit, float interestRate, User holder, float balance, String bsb) 
    // Overloaded constructor function
    {
        // 1 Represents daily interest period
        super(pinCode, dailyLimit, 1, interestRate, holder, balance, bsb);
        
        this.interestPeriod = 1;
        this.type = "Savings";
    }
    
    public void setDailtLimit(float limit) 
    // Unique to savings account, allow user to set daily limit
    {
        this.dailyLimit = limit;
    }
    
    @Override
    public void close() 
    // Close the account, set all meaningful variables to empty
    {
        this.balance = 0;
        this.holder = new User("Closed", "Account");
        this.accountNumber = "--------";
        Account.amount--;
    }
    
}
