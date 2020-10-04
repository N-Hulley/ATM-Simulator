/*
    

    Date : 20190125
    Author : Nick Hulley
 
 */
package pkgATM_nick_hulley;

import account.implementations.Savings;
import account.implementations.Fixed;
import account.implementations.NetSavings;
import account.abstracts.Account;
import account.implementations.Cheque;
import account.metadata.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import user_interface.Gui;
import java.util.ArrayList;

/**
 * This system is a ATM interface that can interact with several bank accounts
 *
 * Try to pull the previously saved bank accounts from a file then
 * initialize a GUI for the user to interact with.
 * 
 *  @author Nick Hulley
 *  Date : 20190125
 */
public class Main {

    
    private static ArrayList<Account> getDefaultAccounts() {
        /*
         *  FOR SAKE OF TESTING, All pins are set to 1234. Feel free to change this
         */
        
        // Store accounts to return
        ArrayList<Account> tempAccounts = new ArrayList<>();
        
        // Two example people
        User person1 = new User("John", "Doe");
        User person2 = new User("Sam", "Smith");

        // John opens 2 accounts
        tempAccounts.add(new Savings("1234", 400, .2f, person1, 3000, "085421"));
        tempAccounts.add(new Cheque("1234", person1, 400, "082320"));

        // Sam opens 3 accounts
        tempAccounts.add(new NetSavings("1234", 1000, 3, person2, 3000, "081529"));
        tempAccounts.add(new Fixed("1234", 400, 20, person2, 20000, "081982"));
        tempAccounts.add(new Cheque("1234", person2, 300, "085201"));
        
        // Return the new array
        return tempAccounts;
    }
    public static void main(String[] args) {

        // Hold all account information
        ArrayList<Account> bankAccounts;

        try{
            // new in stream
            FileInputStream saveFile = new FileInputStream("Accounts.txt");
            
            // Object input stream
            ObjectInputStream save = new ObjectInputStream(saveFile);

            // Set bankaccounts to an array list stored in "accounts.txt"
            bankAccounts = (ArrayList<Account>) save.readObject();

            // Close the file.
            save.close();
        }
        catch(FileNotFoundException ex){
            System.err.println("Failed to find " + '"' + "Accounts.txt" + '"');
            bankAccounts = getDefaultAccounts();
        
        }
        catch(IOException ex){
            System.err.println("Failed to create object stream");
            bankAccounts = getDefaultAccounts();
        
        } 
        catch(ClassNotFoundException ex) {
            System.out.println("Failed to convert file to ArrayList<Account> ");
            bankAccounts = getDefaultAccounts();
        }
            
        // Start Gui
        new Gui(bankAccounts).setVisible(true);
    }
    
}
