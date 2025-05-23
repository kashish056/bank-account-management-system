/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.Project12;

/**
 *
 * @author kashish
 */
import javafx.scene.control.Alert;

public class NotificationWindow {

    /**
     * Displays an informational alert with the specified title and message.
     * 
     * Preconditions: 'title' and 'message' must be non-null strings.
     * Postconditions: A modal alert dialog is displayed with the provided title and message.
     * @param title
     * @param message
     */
    public static void show(String title, String message) {
        Alert notificationAlert = new Alert(Alert.AlertType.INFORMATION);
        notificationAlert.setTitle(title);
        notificationAlert.setHeaderText(null);
        notificationAlert.setContentText(message);
        notificationAlert.showAndWait();
    }
}
    
    
    
