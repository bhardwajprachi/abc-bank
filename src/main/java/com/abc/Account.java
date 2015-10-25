package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

public void transferTo(double amount, Account a) {
    try
    {
        switch(this.getAccountType())
        {
            case Account.CHECKING:
                this.withdraw(amount);
                System.out.println("The amount " +amount+ " has been withdrawn from Checking");
                a.deposit(amount);
                break;
            case Account.SAVINGS:
                this.withdraw(amount);
                System.out.println("The amount " +amount+ " has been withdrawn from Savings");
                a.deposit(amount);
                break;
            case Account.MAXI_SAVINGS:
                this.withdraw(amount);
                System.out.println("The amount " +amount+ " has been withdrawn from Maxi-Savings");
                a.deposit(amount);
                break;
        }
    } catch (IllegalArgumentException e)
    {
        e.printStackTrace();
    }
    
}

public void withdraw(double amount) throws IllegalArgumentException {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } else {
        transactions.add(new Transaction(-amount));
    }
}

    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return (amount * 0.001)/365;
                else
                    return (1 + (amount-1000) * 0.002)/365;
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;
            case MAXI_SAVINGS:
                Date today = new Calendar.getInstance().getTime();
                if (this.checkIfWithdrawalInLast10DaysExists)
                {
                    return (amount * 0.01)/365;
                } else
                {
                    return (amount * 0.05)/365;
                }
                
/*                
                if (amount <= 1000)
                    return amount * 0.02;
                if (amount <= 2000)
                    return 20 + (amount-1000) * 0.05;
                return 70 + (amount-2000) * 0.1;
            default:
                return amount * 0.001;
                
*/                
        }
    }

    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }
    
    private boolean checkIfWithdrawalInLast10DaysExists(Date today)
    {
        double amount=0.0;
        Date tranDate;
        for(Transaction t:transactions)
        {
            if (t.amount<0)
            {
                int diffdays=(int)((today.getTime() - t.transactionDate)/20*60*60*1000);
                if (diffdays<10)
*                return true;
            }
            return false;
        }
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

}
