package Application;

import ClientSide.ChatClient;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatClientApp extends Application {

    private ChatClient client;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize the login form
        LoginForm loginForm = new LoginForm();
        Scene loginScene = loginForm.createLoginScene();

        // Setup main chat window after login
        loginForm.setOnLoginSuccess((username, serverAddress, port) -> {
            try {
                client = new ChatClient(serverAddress, port);
                ChatRoom chatRoom = new ChatRoom(client, username);
                Scene chatScene = chatRoom.createChatScene();
                primaryStage.setScene(chatScene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Chat Application");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
