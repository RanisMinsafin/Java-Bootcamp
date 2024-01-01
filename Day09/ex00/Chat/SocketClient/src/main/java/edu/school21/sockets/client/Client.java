package edu.school21.sockets.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public void start(int port) {
        try {
            socket = new Socket("localhost", port);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    String serverMessage = input.readLine();
                    System.out.println(serverMessage);
                    if (serverMessage.equals("Successful!") || serverMessage.equals("Incorrect action!")) {
                        break;
                    }
                    String line = scanner.nextLine();
                    output.println(line);

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        try {
            socket.close();
            input.close();
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
