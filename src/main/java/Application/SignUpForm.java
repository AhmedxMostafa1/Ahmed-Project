package Application;

import Database.UserDatabase;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignUpForm {

    public void start(Stage stage) {
        VBox layout = new VBox(10);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");

        Button signUpButton = new Button("Sign Up");
        Button cancelButton = new Button("Cancel");

        signUpButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (password.equals(confirmPassword)) {
                if (UserDatabase.registerUser(username, password)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Sign-up successful!");
                    alert.showAndWait();
                    stage.close();  // Close the sign-up window
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Sign-up failed!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Passwords do not match!");
                alert.showAndWait();
            }
        });

        cancelButton.setOnAction(e -> stage.close());

        layout.getChildren().addAll(usernameField, passwordField, confirmPasswordField, signUpButton, cancelButton);

        Scene scene = new Scene(layout, 300, 200);
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();
    }
}
