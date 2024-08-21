package ClientSide;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ChatClient(String serverAddress, int port) throws IOException {
        socket = new Socket(serverAddress, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("Connected to server at " + serverAddress + ":" + port);
    }

    public void sendMessage(String message) {
        out.println(message);
        System.out.println("Sent message: " + message);  // Log sent messages
    }

    public String receiveMessage() throws IOException {
        String message = in.readLine();
        if (message != null) {
            System.out.println("Received message: " + message);  // Log received messages
        }
        return message;
    }

    public void closeConnection() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
