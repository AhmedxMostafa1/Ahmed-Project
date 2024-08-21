package Application;

import Database.UserDatabase;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginForm {

    private OnLoginSuccessListener onLoginSuccessListener;

    public interface OnLoginSuccessListener {
        void onLoginSuccess(String username, String serverAddress, int port);
    }

    public void setOnLoginSuccess(OnLoginSuccessListener listener) {
        this.onLoginSuccessListener = listener;
    }

    public Scene createLoginScene() {
        VBox layout = new VBox(10);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        Button signUpButton = new Button("Sign Up");

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            // Add serverAddress and port as needed
            String serverAddress = "localhost"; // Example server address
            int port = 18866; // Example port

            if (UserDatabase.authenticateUser(username, password)) {
                if (onLoginSuccessListener != null) {
                    onLoginSuccessListener.onLoginSuccess(username, serverAddress, port);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password!");
                alert.showAndWait();
            }
        });

        signUpButton.setOnAction(e -> {
            SignUpForm signUpForm = new SignUpForm();
            Stage signUpStage = new Stage();
            signUpForm.start(signUpStage);
        });

        layout.getChildren().addAll(usernameField, passwordField, loginButton, signUpButton);

        return new Scene(layout, 300, 200);
    }
}
