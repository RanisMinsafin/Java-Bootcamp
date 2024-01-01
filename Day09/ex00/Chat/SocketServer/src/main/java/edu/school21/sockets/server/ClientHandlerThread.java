package edu.school21.sockets.server;

import edu.school21.sockets.models.User;
import edu.school21.sockets.services.UsersServiceImpl;

import java.io.*;
import java.net.Socket;

public class ClientHandlerThread extends Thread {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private UsersServiceImpl service;

    public ClientHandlerThread(Socket socket, UsersServiceImpl service) throws IOException {
        this.socket = socket;
        this.service = service;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    @Override
    public void run() {
        try {
            getClientAction();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getClientAction() throws IOException {
        output.println("Hello from Server!");
        String action = input.readLine();
        if (action.equals("signUp")) {
            User user = new User();
            output.println("Enter username:");
            user.setUsername(input.readLine());
            output.println("Enter password:");
            user.setPassword(input.readLine());
            service.signUp(user);
            output.println("Successful!");
        } else if (action.equals("exit")) {
            closeConnection();
        } else {
            output.println("Incorrect action!");
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
        System.exit(0);
    }
}
