package Client;

import java.io.*;
import java.net.*;

public class client {
    private static final int SERVER_PORT = 1234;

    public Object sendRequest(String request) {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String SERVER_IP = inetAddress.getHostAddress();
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            // Use ObjectOutputStream and ObjectInputStream
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // Send the request
            out.writeObject(request);

            // Receive the response
            Object response = in.readObject();

            // Close resources
            out.close();
            in.close();
            socket.close();

            return response;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        client client = new client();
        Object response = client.sendRequest("Hello, Server!");
        System.out.println("Response: " + response);
    }
}