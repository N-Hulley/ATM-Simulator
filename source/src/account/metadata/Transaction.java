/*
    Represents a transaction betweeen two accounts,
    As well as an amount

    Date : 20181208
    Author : Nick Hulley
 */
package account.metadata;

import account.abstracts.Account;
import account.metadata.User;

public class Transaction implements java.io.Serializable{
    
    private Account sender;
    private Account reciever;
    private float amount;
    String type;
    
    public Transaction(Account sender, Account reciever, float amount) 
    // Represent a transaction with a sender and reciever
    {
        this.sender = sender;
        this.reciever = reciever;
        this.amount = amount;
        this.type = "payment";
    }
    public Transaction(Account sender, float amount, String type) 
    // Represents a transaction between one account and atm
    {
        this.sender = sender;
        this.amount = amount;
        this.type = type;
    }
    
    public Account getSender() {
        return sender;
    }
    
    public Account getReciever() {
        return reciever;
    }
    
    public float getAmount() {
        return amount;
    }
    
    @Override
    public String toString() 
    // Return relevant information
    {
        // If the transaction was through withdrawal
        if (this.type.equals("ATM"))
            return sender.getHolder().getName() + " (Account Number:" + sender.getAccountNumber() + 
                ") | Withdrew: " + this.amount;
        else if (this.type.equals("ATMDeposit"))
            return sender.getHolder().getName() + " (Account Number:" + sender.getAccountNumber() + 
                ") | Deposited: " + this.amount;
        else
        // If transaction was through payment
            return sender.getHolder().getName() + " (Account Number:" + sender.getAccountNumber() + 
                ") | Amount: $" + this.amount;
    }
}
