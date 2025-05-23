/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.Project12;

/**
 *
 * @author Kashish
 */

public interface AccountState {
    
    /**
    
     * Effects: Executes an online purchase for the specified bank account with the given purchase amount.
     * Modifies: The balance of the bank account may be modified.
     * Requires: The purchaseAmount must be a non-negative integer.
     * @param account
     * @param purchaseAmount
     */
    void executeOnlinePurchase(BankAccount account, int purchaseAmount);
    
    /**
     * Describes the type of the account.
     * 
     * @return A string describing the type of the account.
     * 
     * Effects: Provides a description of the type of the account.
     * Modifies: Nothing.
     * Requires: Nothing.
     */
    String describeAccountType();
    
    /**
     * Checks the balance of the given bank account.
     * 
     * @param account The bank account whose balance is to be checked.
     * @return The balance of the bank account.
     * 
     * Effects: Checks the balance of the specified bank account.
     * Modifies: Nothing.
     * Requires: Nothing.
     */
    int checkBalance(BankAccount account);
    
    /**
     * Deposits funds into the given customer's account.
     * 
     * @param customer The customer into whose account the funds are to be deposited.
     * @param amount The amount to deposit.
     * 
     * Effects: Deposits the specified amount into the customer's account.
     * Modifies: The balance of the customer's account may be modified.
     * Requires: The amount must be a non-negative integer.
     */
    void deposit(Customer customer, int amount);
    
    /**
     * Withdraws funds from the given customer's account.
     * 
     * @param customer The customer from whose account the funds are to be withdrawn.
     * @param amount The amount to withdraw.
     * 
     * Effects: Withdraws the specified amount from the customer's account.
     * Modifies: The balance of the customer's account may be modified.
     * Requires: The amount must be a non-negative integer.
     */
    void withdraw(Customer customer, int amount);
    
    /**
     * Updates the balance of the given bank account with the new balance.
     * 
     * @param account The bank account whose balance is to be updated.
     * @param newBalance The new balance to set.
     * 
     * Effects: Updates the balance of the specified bank account with the new balance.
     * Modifies: The balance of the bank account may be modified.
     * Requires: The newBalance must be a non-negative integer.
     */
    void updateBalance(BankAccount account, int newBalance);
}