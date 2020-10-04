/*
    Represents a user that would have a bank account

    Date : 20181208
    Author : Nick Hulley
 */
package account.metadata;

public class User implements java.io.Serializable{
    
    private String firstname;
    private String surname;

    public User(String firstname, String surname) 
    // Overload constructor function
    {
        this.firstname = firstname;
        this.surname = surname;
    }
    
    public void setName(String firstname, String surname)
    // Set users name
    {
        this.firstname = firstname;
        this.surname = surname;
    }
    
    public String getName() 
    // Get full name of the user
    {
        return firstname + " " + surname;
    }
    
}
