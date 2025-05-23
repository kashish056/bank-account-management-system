/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.Project12;

/**
 *
 * @author kashish
 */
public class Silver implements AccountState {
    private static final int SERVICE_CHARGE = 20;

    @Override
    public void executeOnlinePurchase(BankAccount account, int purchaseAmount) {
        int totalCost = purchaseAmount + SERVICE_CHARGE;
        if (account.getBalance() >= totalCost) {
            account.withdraw(totalCost);
            NotificationWindow.show("Purchase Successful", "Silver Member Purchase: $" + purchaseAmount + " with service charge $" + SERVICE_CHARGE + ". Total: $" + totalCost);
        } else {
            NotificationWindow.show("Purchase Error", "Insufficient funds for this purchase.");
        }
    }

    @Override
    public String describeAccountType() {
        return "Silver Member Account";
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

