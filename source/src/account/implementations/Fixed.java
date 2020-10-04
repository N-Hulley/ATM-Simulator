/*

    Fixed offers the biggest interest
    rate, however it is only calculated after a fixed contract period. If
    user witdraws before this contract period, they receive no interest
    (assume any withdrawal is too early, so keep track of early
    withdrawal in a boolean attribute for the class). Fixed account has
    no daily withdraw limit.

    Date : 20181208
    Author : Nick Hulley
 */
package account.implementations;

import account.interfaces.Closable;
import account.abstracts.Account;
import account.abstracts.InterestAccount;
import account.metadata.User;
import exceptions.TransactionFailedException;
import java.util.ArrayList;
import java.util.List;

public class Fixed extends InterestAccount implements Closable {

    private Boolean earlyWithdrawal;

    public Boolean getEarlyWithdrawal() {
        return this.earlyWithdrawal;
    }
    public Fixed(String pinCode, int interestPeriod, float interestRate, User holder, float balance, String bsb) 
    // Overload constructor function
    {
        super(pinCode, interestRate, interestPeriod, holder, balance, bsb);
        
        this.earlyWithdrawal = false;
        this.type = "Fixed";
    }
    @Override 
    public void addInterest(float calculatedInterest) 
    // Add interest only if there has been no withdrawals
    {
        if (this.earlyWithdrawal) {
            // Print what happened
            System.out.println("Interest was not added to " + holder.getName() + " as they withdrew money early");
            return;
        }
        // Add interest as usual
        super.addInterest(calculatedInterest);
    }
    
    @Override 
    public List<Float> withdraw(float amount) throws TransactionFailedException
    // Same as withdraw but it sets early withdrawal to true if successful
    {
        // Get the notes that would be found
        List<Float> results = super.withdraw(amount);
        
        // Check if the transaction was successfult, if so, set Earlywithdrawal to true
        if (results.size() > 0) this.earlyWithdrawal = true;
       
        // Return results
        return results;
    }
    
    
    
    @Override
    public void close() 
    // Remove all useful information from account
    {
        this.balance = 0;
        this.holder = new User("Closed", "Account");
        this.accountNumber = "--------";
        Account.amount--;
    }
    
    @Override
    public String toString() 
    // Return relevant information
    {
        return (
                super.toString()) + 
                "\n\t- Early Withdrawal? " + this.earlyWithdrawal + 
                "\n\t- Interest Rate: " + this.interestRate +
                "\n\t- Period: " + this.interestRate;
    }
}
