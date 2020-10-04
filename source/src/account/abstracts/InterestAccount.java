/*
    Representation of any account that has interest

    Date : 20181208
    Author : Nick Hulley
 */
package account.abstracts;

import account.metadata.User;

public abstract class InterestAccount extends Account {
    
    protected float interestRate; //%
    protected int interestPeriod; // Days between each time interest is added
 
    public InterestAccount(String pinCode, float interestRate, int interestPeriod, User holder, float balance, String bsb) 
    // Overloaded constructor function
    {
        super(pinCode, holder, balance, bsb);
        this.interestPeriod = interestPeriod;
        this.interestRate = interestRate;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public int getInterestPeriod() {
        return interestPeriod;
    }
    
    public void addInterest(float calculatedInterest) 
    // This function would be called every interest period
    {
        System.out.println("$" + calculatedInterest + " interest added to " + holder.getName() + "s " + type + " account");
        this.balance += calculatedInterest;
    }
    
    @Override 
    public String toString() 
    // Return relevant information
    {
        return  super.toString() + 
                "\n\t- InterestRate: " + this.interestRate +
                "\n\t- Interest Period (per year): " + this.interestPeriod;
    }
}
