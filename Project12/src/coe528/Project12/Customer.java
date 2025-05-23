/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.Project12;

/**
 *
 * @author kashish
 */



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Customer {
    private final String username;
    private final String password;
    private final BankAccount account;
    private final String customerRole = "customer";

    public Customer(String username, String password, int initialBalance) {
        this.username = username;
        this.password = password;
        this.account = new BankAccount(determineAccountLevel(initialBalance));
        this.account.setBalance(initialBalance);
        createOrUpdateCustomerFile(true);
    }

    private AccountState determineAccountLevel(int balance) {
        if (balance < 10000) {
            return new Silver();
        } else if (balance >= 10000 && balance < 20000) {
            return new Gold();
        } else {
            return new Platinum();
        }
    }

    public void depositMoney(int amount) {
        this.account.deposit(amount);
        updateAccountLevel();
        createOrUpdateCustomerFile(false);
    }

    public void withdrawMoney(int amount) {
        this.account.withdraw(amount);
        updateAccountLevel();
        createOrUpdateCustomerFile(false);
    }

    public void attemptOnlinePurchase(int amount) {
    int fee = calculatePurchaseFee(amount);
    int totalCost = amount + fee;

    if (totalCost < 50) {
        NotificationWindow.show("Purchase Error", "Online purchase must be $50 or more.");
        return;
    }

    if (account.getBalance() < totalCost) {
        NotificationWindow.show("Purchase Error", "Insufficient funds");
        return;
    }

    account.withdraw(totalCost);
    NotificationWindow.show("Purchase Successful", "Online purchase of $" + amount + " has been made. Fee: $" + fee);
    updateAccountLevel();
    createOrUpdateCustomerFile(false);
}
    private int calculatePurchaseFee(int purchaseAmount) {
        if (account.getState() instanceof Silver) {
            return 20;
        } else if (account.getState() instanceof Gold) {
            return 10;
        } else {
            return 0; // No fee for Platinum customers
        }
    }

    private void updateAccountLevel() {
        AccountState newState = determineAccountLevel(this.account.getBalance());
        if (!newState.getClass().equals(this.account.getState().getClass())) {
            this.account.setState(newState);
        }
    }

    private void createOrUpdateCustomerFile(boolean isNewCustomer) {
        try {
            File file = new File(username + ".txt");
            if (isNewCustomer) file.createNewFile();
            try (FileWriter writer = new FileWriter(file, false)) {
                writer.write(username + "\n" + password + "\n" + customerRole + "\n" + account.getBalance());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating/updating the customer file: " + e.getMessage());
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return this.account.getBalance();
    }

    public BankAccount getAccount() {
        return account;
    }
     
    public String getLevel() {
        int balance = account.getBalance();
        if (balance < 10000) {
            return "Silver";
        } else if (balance < 20000) {
            return "Gold";
        } else {
            return "Platinum";
        }
    }

    private boolean repOk() {
        return username != null && !username.isEmpty() &&
               password != null && !password.isEmpty() &&
               account != null &&
               (customerRole.equals("customer") || customerRole.equals("manager"));
    }

    @Override
    public String toString() {
        return "Customer[username=" + username + ", role=" + customerRole + ", account=" + account.toString() + "]";
    }

}