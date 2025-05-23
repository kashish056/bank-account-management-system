/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package coe528.Project12;

import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 *
 * @author kashish
 */



public class BankingApplication extends Application {
    private final ArrayList<Customer> customers = new ArrayList<>();
    private final BankManager bankManager = new BankManager("admin", "admin");
    private Stage primaryStage;
    private VBox vBox;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("TMU Banking ");

        showLoginScene();
    }

    private void showLoginScene() {
    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.TOP_LEFT); // Align content to the top left
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setPadding(new Insets(20));

    Label usernameLabel = new Label("Username:");
    TextField usernameField = new TextField();
    Label passwordLabel = new Label("Password:");
    PasswordField passwordField = new PasswordField();
    Button loginButton = new Button("Login");

    // Add constraints to align elements to the left
    GridPane.setHalignment(usernameLabel, HPos.LEFT);
    GridPane.setHalignment(usernameField, HPos.LEFT);
    GridPane.setHalignment(passwordLabel, HPos.LEFT);
    GridPane.setHalignment(passwordField, HPos.LEFT);
    GridPane.setHalignment(loginButton, HPos.LEFT);

    gridPane.add(usernameLabel, 0, 0);
    gridPane.add(usernameField, 1, 0);
    gridPane.add(passwordLabel, 0, 1);
    gridPane.add(passwordField, 1, 1);
    gridPane.add(loginButton, 1, 2);

    loginButton.setOnAction((var event) -> {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (bankManager.login(username, password)) {
            showManagerScene();
        } else {
            Customer customer = findCustomer(username, password);
            if (customer != null) {
                // Determine customer's level
                String level = determineCustomerLevel(customer);

                // Display customer's level
                Label levelLabel = new Label("Level: " + level);
                gridPane.add(levelLabel, 1, 3); // Add levelLabel to gridPane

                showCustomerScene(customer);
            } else {
                showAlert("Login Failed", "Invalid username or password.");
            }
        }
    });

    Scene scene = new Scene(gridPane, 300, 200);
    primaryStage.setScene(scene);
    primaryStage.show();
}

// Method to determine customer's level based on account balance
private String determineCustomerLevel(Customer customer) {
    int balance = customer.getBalance();
    if (balance < 10000) {
        return "Silver";
    } else if (balance < 20000) {
        return "Gold";
    } else {
        return "Platinum";
    }
}

    private void showManagerScene() {
    Button addCustomerButton = new Button("Add Customer");
    Button deleteCustomerButton = new Button("Delete Customer");
    Button logoutButton = new Button("Logout");

    VBox vBox = new VBox(10);
    vBox.setAlignment(Pos.TOP_LEFT); // Align content to the top left
    vBox.setPadding(new Insets(20));
    vBox.getChildren().addAll(addCustomerButton, deleteCustomerButton, logoutButton);

    addCustomerButton.setOnAction(event -> showAddCustomerDialog());
    deleteCustomerButton.setOnAction(event -> showDeleteCustomerDialog());
    logoutButton.setOnAction(event -> showLoginScene());

    Scene scene = new Scene(vBox, 300, 200);
    primaryStage.setScene(scene);
}
     

    private void showCustomerScene(Customer customer) {
    GridPane layout = new GridPane();
    layout.setAlignment(Pos.CENTER);
    layout.setVgap(10);
    layout.setHgap(10);

    Label balanceLabel = new Label("Balance: $" + customer.getBalance());
    GridPane.setConstraints(balanceLabel, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER);

    Label accountLevelLabel = new Label("Account Level: " + customer.getLevel());
    GridPane.setConstraints(accountLevelLabel, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER);

    TextField depositAmountField = new TextField();
    depositAmountField.setPromptText("Amount to deposit");
    Button depositButton = new Button("Deposit");
    GridPane.setConstraints(depositAmountField, 0, 2);
    GridPane.setConstraints(depositButton, 1, 2);

    depositButton.setOnAction(e -> {
        try {
            int amount = Integer.parseInt(depositAmountField.getText());
            customer.depositMoney(amount);
            balanceLabel.setText("Balance: $" + customer.getBalance());
            updateAccountLevel(customer, accountLevelLabel, balanceLabel); // Update account level and balance
            depositAmountField.clear();
        } catch (NumberFormatException ex) {
            AlertBox.display("Error", "Invalid amount. Please enter a valid number.");
        }
    });
    

    TextField withdrawAmountField = new TextField();
    withdrawAmountField.setPromptText("Amount to withdraw");
    Button withdrawButton = new Button("Withdraw");
    GridPane.setConstraints(withdrawAmountField, 0, 3);
    GridPane.setConstraints(withdrawButton, 1, 3);

    withdrawButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
            try {
                int amount = Integer.parseInt(withdrawAmountField.getText());
                customer.withdrawMoney(amount);
                balanceLabel.setText("Balance: $" + customer.getBalance());
                updateAccountLevel(customer, accountLevelLabel, balanceLabel); // Update account level and balance
                withdrawAmountField.clear();
            } catch (NumberFormatException ex) {
                AlertBox.display("Error", "Invalid amount. Please enter a valid number.");
            }
        }

        private void updateAccountLevel(Customer customer, Label accountLevelLabel, Label balanceLabel) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    });

    // Online Purchase Button
    // Online Purchase Button
    Button onlinePurchaseButton = new Button("Online Purchase");
    GridPane.setConstraints(onlinePurchaseButton, 0, 4, 2, 1, HPos.CENTER, VPos.CENTER);
    onlinePurchaseButton.setOnAction(e -> showOnlinePurchaseScene(customer, balanceLabel, accountLevelLabel)); // Pass balanceLabel and accountLevelLabel to update
    
    Button checkBalanceButton = new Button("Check Balance");
    checkBalanceButton.setOnAction(e -> {
        NotificationWindow.show("Balance", "Your current balance is: $" + customer.getBalance());
    });
    GridPane.setConstraints(checkBalanceButton, 0, 5, 2, 1, HPos.CENTER, VPos.CENTER);

    Button logoutbutton = new Button("Log Out");
    logoutbutton.setOnAction(e -> showLoginScene()); // Log out action
    GridPane.setConstraints(logoutbutton, 0, 6, 2, 1, HPos.CENTER, VPos.CENTER);

    layout.getChildren().addAll(balanceLabel, accountLevelLabel, depositAmountField, depositButton, withdrawAmountField, withdrawButton, onlinePurchaseButton, checkBalanceButton, logoutbutton);

    Scene customerScene = new Scene(layout, 400, 300);
    primaryStage.setScene(customerScene); // Update primaryStage instead of mainStage
}
 private void showOnlinePurchaseScene(Customer customer, Label balanceLabel, Label accountLevelLabel) {
    TextInputDialog dialog = createTextInputDialog("Online Purchase", "Enter purchase amount", "Amount:");
    Optional<String> result = dialog.showAndWait();

    result.ifPresent(amount -> {
        handleTransaction(amount, customer, "purchase");
        balanceLabel.setText("Balance: $" + customer.getBalance());
        updateAccountLevel(customer, accountLevelLabel, balanceLabel);
    });
}

private void updateAccountLevel(Customer customer, Label accountLevelLabel, Label balanceLabel) {
    int balance = customer.getBalance();
    String level;

    if (balance < 10000) {
        level = "Silver";
    } else if (balance < 20000) {
        level = "Gold";
    } else {
        level = "Platinum";
    }

    accountLevelLabel.setText("Account Level: " + level);
}
private void showAddCustomerDialog() {
        GridPane gridPane = createGridPane();
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Label balanceLabel = new Label("Initial Balance:");
        TextField balanceField = new TextField();
        Button addButton = new Button("Add");

        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(balanceLabel, 0, 2);
        gridPane.add(balanceField, 1, 2);
        gridPane.add(addButton, 1, 3);
        GridPane.setHalignment(addButton, HPos.RIGHT);

        addButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String balanceText = balanceField.getText();

            if (!username.isEmpty() && !password.isEmpty() && !balanceText.isEmpty()) {
                try {
                    int initialBalance = Integer.parseInt(balanceText);
                    if (initialBalance < 0) {
                        showAlert("Error", "Initial balance cannot be negative!");
                    } else {
                        Customer newCustomer = new Customer(username, password, initialBalance);
                        customers.add(newCustomer);
                        showAlert("Success", "Customer added successfully!");
                        clearFields(usernameField, passwordField, balanceField);
                        showLoginScene(); // Navigate back to login scene
                    }
                } catch (NumberFormatException e) {
                    showAlert("Error", "Invalid balance! Please enter a valid amount.");
                }
            } else {
                showAlert("Error", "Username, password, and balance cannot be empty!");
            }
        });

        primaryStage.setScene(new Scene(gridPane, 400, 300));
    }

    private void showDeleteCustomerDialog() {
        GridPane gridPane = createGridPane();
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Button deleteButton = new Button("Delete");

        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(deleteButton, 1, 1);
        GridPane.setHalignment(deleteButton, HPos.RIGHT);

        deleteButton.setOnAction(event -> {
            String username = usernameField.getText();
            if (!username.isEmpty()) {
                Customer customerToDelete = findCustomerByUsername(username);
                if (customerToDelete != null) {
                    customers.remove(customerToDelete);
                    showAlert("Success", "Customer deleted successfully!");
                    clearFields(usernameField);
                    showLoginScene(); // Navigate back to login scene
                } else {
                    showAlert("Error", "Customer not found!");
                }
            } else {
                showAlert("Error", "Username cannot be empty!");
            }
        });
        primaryStage.setScene(new Scene(gridPane, 400, 300));
    }

   

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        return gridPane;
    }

    private TextInputDialog createTextInputDialog(String title, String headerText, String contentText) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        dialog.setContentText(contentText);
        return dialog;
    }

    private void handleTransaction(String amount, Customer customer, String transactionType) {
        try {
            int transactionAmount = Integer.parseInt(amount);
            switch (transactionType) {
                case "deposit":
                    customer.depositMoney(transactionAmount);
                    break;
                case "withdraw":
                    if (customer.getBalance() >= transactionAmount) {
                        customer.withdrawMoney(transactionAmount);
                    } else {
                        showAlert("Error", "Insufficient balance!");
                        return;
                    }
                    break;
                case "purchase":
                    if (customer.getBalance() >= transactionAmount) {
                        customer.attemptOnlinePurchase(transactionAmount);
                    } else {
                        showAlert("Error", "Insufficient balance!");
                        return;
                    }
                    break;
            }
            showCustomerScene(customer); // Refresh the scene after the transaction
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input! Please enter a valid amount.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    private Customer findCustomer(String username, String password) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }

    private Customer findCustomerByUsername(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }

}

