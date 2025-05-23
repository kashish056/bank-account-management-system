/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.Project12;

/**
 *
 * @author Kashish
/**
 * Represents a bank account with a balance and state.
 * This class is mutable.
 */
public class BankAccount {
    private int balance;
    private AccountState state;

    
     // Abstraction Function:
     // Represents a bank account with a balance and state.
     // 
     // Representation Invariant:
     // Balance must not be negative.
     // State must not be null.
    //

    //
     // Constructs a new bank account with the given initial state.
     //@param initialState The initial state of the account.
     //
    public BankAccount(AccountState initialState) {
        this.balance = 0;
        this.state = initialState;
    }

    /**
     * Returns the balance of the bank account.
     * @return The balance.
     */
    public int getBalance() {
        // Effects: Returns the current balance of the account.
        // Modifies: Nothing
        return balance;
    }

    /**
     * Sets the balance of the bank account to the given value.
     * @param newBalance The new balance to set.
     */
    public void setBalance(int newBalance) {
        // Effects: Sets the balance of the account to the specified value.
        // Modifies: balance
        // Requires: newBalance >= 0
        if (newBalance >= 0) {
            this.balance = newBalance;
        }
    }

    /**
     * Deposits funds into the bank account.
     * @param amount The amount to deposit.
     */
    public void deposit(int amount) {
        // Effects: Increases the balance by the specified amount.
        // Modifies: balance
        // Requires: amount > 0
        if (amount > 0) {
            this.balance += amount;
            NotificationWindow.show("Deposit", "$" + amount + " has been deposited into your account.");
        } else {
            NotificationWindow.show("Error","Invalid deposit amount");
        }
    }

    /**
     * Withdraws funds from the bank account.
     * @param amount The amount to withdraw.
     */
    public void withdraw(int amount) {
        // Effects: Decreases the balance by the specified amount.
        // Modifies: balance
        // Requires: amount > 0, amount <= balance
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            NotificationWindow.show("Withdrawal", "$" + amount + " has been withdrawn from your account.");
        } else if (amount <= 0) {
            NotificationWindow.show("Error","Invalid withdrawal amount");
        } else {
            NotificationWindow.show("Error","Insufficient funds for withdrawal");
        }
    }

    /**
     * Executes an online purchase.
     * @param purchaseAmount The amount of the purchase.
     */
    public void executeOnlinePurchase(int purchaseAmount) {
        // Effects: Executes an online purchase if conditions are met, else shows error messages.
        // Modifies: balance
        if (purchaseAmount < 50) {
            NotificationWindow.show("Purchase Error", "Online purchase must be $50 or more.");
            return;
        } else if (purchaseAmount > state.checkBalance(this)) {
            NotificationWindow.show("Purchase Error", "Insufficient funds");
            return;
        }
        
        state.executeOnlinePurchase(this, purchaseAmount);
    }

    /**
     * Sets the state of the bank account.
     * @param newState The new state of the account.
     */
    public void setState(AccountState newState) {
        // Effects: Sets the state of the account to the specified state.
        // Modifies: state
        this.state = newState;
    }

    /**
     * Returns the type of the bank account.
     * @return The type of the account.
     */
    public String getType() {
        // Effects: Returns the type of the account.
        // Modifies: Nothing
        return this.state.describeAccountType();
    }

    
     //Returns the current state of the bank account.
     //@return The state of the account.
   
    public AccountState getState() {
        // Effects: Returns the current state of the account.
        // Modifies: Nothing
        return this.state;
    }

    /**
     * Checks if the balance of the bank account is valid.
     * @return True if the balance is valid, false otherwise.
     */
    public boolean isValidBalance() {
        // Effects: Checks if the balance of the account is valid.
        // Modifies: Nothing
        return this.balance >= 0;
    }

    /**
     * Returns a string representation of the bank account.
     * @return A string containing the balance and type of the account.
     */
    @Override
    public String toString() {
        // Abstraction Function Implementation
        return "Current Balance: $" + balance + ", Account Type: " + getType();
    }

    /**
     * Checks if the representation invariant of the bank account is maintained.
     * @return True if the representation invariant holds, false otherwise.
     */
    private boolean repOk() {
        // Representation Invariant Implementation
        return balance >= 0 && state != null;
    }
}
