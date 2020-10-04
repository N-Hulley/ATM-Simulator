
package account.abstracts;

/**
 * Represents an interest account that has maximum limits per day, meaning no
 * more than the daily limit can be charged in a day
 *
 * Date : 20181208
 * Author : Nick Hulley
 */
import exceptions.TransactionFailedException;
import account.metadata.User;
import exceptions.OverdrawnException;

public abstract class LimitedInterest extends InterestAccount {
    
    protected float dailyLimit;
    protected float withDrawnAmount;

    public float getDailyLimit() {
        return dailyLimit;
    }

    public float getWithDrawnAmount() {
        return withDrawnAmount;
    }

    public LimitedInterest(String pinCode, float dailyLimit, int interestPeriod, float interestRate, User holder, float balance, String bsb) 
    // Overloaded constructor function
    {
        super(pinCode, interestRate, interestPeriod, holder, balance, bsb);
        this.dailyLimit = dailyLimit;
    }
    
    public Boolean charge(float amount) throws OverdrawnException, TransactionFailedException
    // Check to see if user will go above daily limit, deny the transaction
    {    
        
        if (amount + withDrawnAmount > this.dailyLimit) throw new OverdrawnException("Cannot Overdraw Account", "04");
        withDrawnAmount += amount;
        return super.charge(amount);
    }
    
    public String toString() 
    // Return relevant information
    {
        return super.toString() + 
                "\n\t- Daily Limit: " + this.dailyLimit;
    }
    
}
