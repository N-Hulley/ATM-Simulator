/*
    Represents a bank account

    Date : 20181208
    Author : Nick Hulley
 */
package account.abstracts;
import account.metadata.Transaction;
import account.metadata.User;
import exceptions.TransactionFailedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Account implements java.io.Serializable{
    
    // Keep track of number of accounts
    public static Integer amount = 0;
    
    protected User holder;
    protected String accountNumber;
    protected String pinCode;
    protected String type = "Not Specified";
    protected float balance;
    protected String bsb = "";
    protected List<Transaction> history;
    
    public Account(String pinCode, User holder, float balance, String accountNumber) 
    // Overloaded constructor class
    {
        this.pinCode = pinCode;
        this.holder = holder;
        this.accountNumber = accountNumber;
        this.balance = balance;
        
        // Generate random bsb
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            this.bsb = this.bsb + String.valueOf(Math.abs(r.nextInt()%10));
        }
        
        // Make sure account history is initialised
        this.history = new ArrayList<>();
        
        // Increment the amount of accounts that exist
        Account.amount++;
    
    }
    
    public void deposit(float amount) 
    // Add amount to balance
    {
        this.balance += amount;
        this.history.add(new Transaction(this, amount ,"ATMDeposit"));
    }
    
    public Boolean correctPin(String pinCode) {
        return this.pinCode.equals(pinCode);
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public List<Float> withdraw(float amount) throws TransactionFailedException
    // Simulate taking money out of an ATM
    // Return a list of notes that the ATM will spit out
    // Could be implemented into a new ATM class
            
    // Just a coincidence that ATM lines up 3 times
    {
        float origAmount = amount;
        
        // Store the possible notes an atm can give
        List<Float> possibleNotes = new ArrayList<Float>();
        possibleNotes.add(100f);
        possibleNotes.add(50f);
        possibleNotes.add(20f);
        
        // A list of notes that will be outputted
        List<Float> output = new ArrayList<Float>();
        
        // Store how much will be charged to the account
        float chargeAmount = 0;
        
        // Keep going until the amount cant go lower
        while (amount - possibleNotes.get(possibleNotes.size()-1) >= 0) 
        {
            // For each note
            for (int i = 0; i < possibleNotes.size(); i++ ) {
                
                // Check if thee is enough to give note i
                if (amount - possibleNotes.get(i) >= 0 &&
                    possibleNotes.get(i) >= 0) {

                    // Take away the notes value
                    amount -= possibleNotes.get(i); 
                    
                    // add note to list of notes to spit out
                    output.add(possibleNotes.get(i));
                    
                    // Charge value of note to account
                    chargeAmount += possibleNotes.get(i);
                    
                    break;
                } 
            }
        }
        // Change the balance, or cancel if the user does not have enough ballence
        this.charge(chargeAmount);
        
        // Print what happened
        System.out.println(this.holder.getName() + " withdrew " + chargeAmount);
        
        // Print the notes that were withdrawn
        output.forEach((note) -> {System.out.println("\t- $" + note);});
        
        // Print conversion 
        System.out.println("\t$" + origAmount + " was rounded to $" + chargeAmount + " during ATM transaction\n");
        
        // Store the transaction 
        this.history.add(new Transaction(this, chargeAmount,"ATM"));
        
        // Return a list of notes
        return output;
    }
    
    public Boolean charge(float amount) throws TransactionFailedException
    // Charge an amount to the account if it will not go negative
    // Returns true if transaction is successful
    {    
        // Check if user has enough money
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        throw new TransactionFailedException("Insufficient Funds for withdrawal", "01");
    }
    
    public float getBalance() 
    // Return accounts current balance
    {
        return balance;
    }
    
    public Boolean pay(Account other, float amount) throws TransactionFailedException
    // Charge an amount to the account and give amount to another account
    // Returns true if transaction is successful
    {
        // Check if charging is successful
        if (!this.charge(amount)) {
            
            // Print what happened
            System.out.println(this.holder.getName() + 
                    " did not have $" + amount + " to pay " + 
                    other.holder.getName());
            
            return false;
        }
        
        // Print what happened
        System.out.println(this.holder.getName() + 
                    " gave $" + amount + " to " + 
                    other.holder.getName());
        
        // Store the transaction
        Transaction transaction = new Transaction(this, other, amount);
        
        // Add transaction to historys
        this.history.add(transaction);
        other.addTransaction(transaction);
        
        // Deposit money into other account
        other.deposit(amount);
        
        // Indicate transaction was successful
        return true;
    }
    
    public void addTransaction(Transaction transaction) 
    // Add a transaction to the history of the account
    {
        this.history.add(transaction);
    }
    
    public String getHistory() 
    // Convert transaction history to a string
    {
        String output = "";
        
        // Add each transaction to output as a string
        output = history.stream().map((transaction) -> 
                " - " + transaction.toString() + "\n"
        ).reduce(output, String::concat);
        
        return output;
    }

    public static Integer getAmount() {
        return amount;
    }

    public User getHolder() {
        return holder;
    }

    public String getType() {
        return type;
    }

    public String getBsb() {
        return bsb;
    }
    
    @Override
    public String toString() 
    // Return relevant information
    {   
        return (
                "\n\t- " + "Holder: " + this.holder.getName() +
                "\n\t- " + "Account Type: " + this.type + 
                "\n\t- " + "Account number: " + this.accountNumber +  
                "\n\t- " + "Balance: " + this.balance + 
                "\n\t- " + "Transaction History: " + this.getHistory()
                );
    }
}
