package Application;

import ClientSide.ChatClient;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ChatRoom {
    private ChatClient client;
    private String username;
    private TextArea chatArea;
    private ComboBox<String> statusComboBox;

    public ChatRoom(ChatClient client, String username) {
        this.client = client;
        this.username = username;
    }

    public Scene createChatScene() {
        VBox vbox = new VBox();
        vbox.setSpacing(10);

        chatArea = new TextArea();
        chatArea.setEditable(false);

        statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll("Available", "Busy");
        statusComboBox.setValue("Available");

        TextField messageField = new TextField();
        messageField.setPromptText("Enter your message");

        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> {
            String message = messageField.getText();
            if (!message.isEmpty()) {
                String status = statusComboBox.getValue();
                client.sendMessage(username + " (" + status + "): " + message);
                chatArea.appendText(username + " (" + status + "): " + message + "\n");
                messageField.clear();
            }
        });

        Button saveLogButton = new Button("Save Chat Log");
        saveLogButton.setOnAction(e -> saveChatLog());

        vbox.getChildren().addAll(statusComboBox, chatArea, messageField, sendButton, saveLogButton);
        Scene scene = new Scene(vbox, 500, 400);

        // Start listening for incoming messages in a separate thread
        new Thread(() -> {
            try {
                while (true) {
                    String receivedMessage = client.receiveMessage();
                    Platform.runLater(() -> chatArea.appendText(receivedMessage + "\n"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        return scene;
    }

    private void saveChatLog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Chat Log");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(chatArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
