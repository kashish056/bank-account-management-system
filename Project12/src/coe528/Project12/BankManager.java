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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BankManager {

    public BankManager(String admin, String admin1) {
    }
    private final String managerUsername = "admin";
    private final String managerPassword = "admin";

    public boolean login(String username, String password) {
        return username.equals(managerUsername) && password.equals(managerPassword);
    }

    public boolean addCustomer(String username, String password) {
        // Check if the username and password are not null
        if (username == null || password == null) {
            System.out.println("Username or password cannot be null.");
            return false;
        }

        // Check if the customer already exists
        if (customerExists(username)) {
            System.out.println("Customer already exists.");
            return false;
        }

        // Create a new customer file with default balance of $100
        try {
            File customerFile = new File(username + ".txt");
            if (customerFile.createNewFile()) {
                Files.write(Paths.get(username + ".txt"), ("Username: " + username + "\nPassword: " + password + "\nRole: customer\nBalance: 100").getBytes());
                System.out.println("Customer added successfully.");
                return true;
            }
        } catch (IOException e) {
        }

        System.out.println("Failed to add customer.");
        return false;
    }

    public boolean deleteCustomer(String username) {
        // Check if the customer exists
        if (!customerExists(username)) {
            System.out.println("Customer does not exist.");
            return false;
        }

        // Delete the customer file
        try {
            Files.deleteIfExists(Paths.get(username + ".txt"));
            System.out.println("Customer deleted successfully.");
            return true;
        } catch (IOException e) {
        }

        System.out.println("Failed to delete customer.");
        return false;
    }

    private boolean customerExists(String username) {
        File customerFile = new File(username + ".txt");
        return customerFile.exists();
    }

}
  