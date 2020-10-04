/*
    Net Savings offer interest higher
    then savings but its calculated monthly. They have a daily withdrawal
    limit but the user cannot set/change this limit.

    Date : 20181208
    Author : Nick Hulley
 */
package account.implementations;

import account.abstracts.LimitedInterest;
import account.metadata.User;

public class NetSavings extends LimitedInterest {

    public NetSavings(String pinCode, float dailyLimit, float interestRate, User holder, float balance, String bsb) 
    // Overload constructor function
    {
        // 28 Represents a monthly interest peroid
        super(pinCode, dailyLimit, 28, interestRate, holder, balance, bsb);
        this.type = "Net Savings";
    }
}
