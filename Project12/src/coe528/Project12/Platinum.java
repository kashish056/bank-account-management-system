/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.Project12;

/**
 *
 * @author kashish
 */
public class Platinum implements AccountState {
    @Override
    public void executeOnlinePurchase(BankAccount account, int purchaseAmount) {
        if (account.getBalance() >= purchaseAmount) {
            account.withdraw(purchaseAmount);
            NotificationWindow.show("Purchase Successful", "Platinum Member Purchase: $" + purchaseAmount);
        } else {
            NotificationWindow.show("Purchase Error", "Insufficient funds for this purchase.");
        }
    }

    @Override
    public String describeAccountType() {
        return "Platinum Member Account";
    }

    @Override
    public int checkBalance(BankAccount account) {
        return account.getBalance();
    }

    @Override
    public void deposit(Customer customer, int amount) {
        if (amount > 0) {
            customer.getAccount().deposit(amount);
        }
    }

    @Override
    public void withdraw(Customer customer, int amount) {
        if (amount > 0 && customer.getAccount().getBalance() >= amount) {
            customer.getAccount().withdraw(amount);
        }
    }

    @Override
    public void updateBalance(BankAccount account, int newBalance) {
        if (newBalance >= 0) {
            account.setBalance(newBalance);
        }
    }
}