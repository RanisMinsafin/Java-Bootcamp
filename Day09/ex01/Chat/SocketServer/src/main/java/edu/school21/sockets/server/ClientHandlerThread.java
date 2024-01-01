package edu.school21.sockets.server;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import edu.school21.sockets.services.MessageService;
import edu.school21.sockets.services.UsersService;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class ClientHandlerThread extends Thread {
    private String username;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private UsersService usersService;
    private MessageService messageService;

    public ClientHandlerThread(Socket socket, UsersService usersService, MessageService messageService) throws IOException {
        this.socket = socket;
        this.usersService = usersService;
        this.messageService = messageService;
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
            signUp();
        } else if (action.equals("signIn")) {
            signIn();
        } else {
            output.println("Incorrect action!");
            closeConnection();
        }
    }


    void signUp() throws IOException {
        User user = new User();
        output.println("Enter username:");
        username = input.readLine();
        user.setUsername(username);
        output.println("Enter password:");
        user.setPassword(input.readLine());
        usersService.signUp(user);
        output.println("Successful!");
        startMessaging();
    }

    void signIn() throws IOException {
        User user = new User();
        output.println("Enter username:");
        username = input.readLine();
        user.setUsername(username);
        output.println("Enter password:");
        user.setPassword(input.readLine());

        if (usersService.signIn(user)) {
            startMessaging();
        } else {
            output.println("Incorrect password!");
            closeConnection();
        }
    }

    private void startMessaging() {
        output.println("Start messaging!");

        Thread readerThread = new Thread(() -> {
            try {
                String message;
                while (!(message = input.readLine()).equalsIgnoreCase("exit")) {
                    Server.sendMessageToChats(username, message);
                    saveMessageInDataBase(username, message);
                }
                output.println("You have left the chat!");
                closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        readerThread.start();
    }

    public void sendMessage(String name, String text) {
        output.println(name + ": " + text);
    }

    private void saveMessageInDataBase(String name, String text) {
        Message message = new Message();
        Long id = usersService.getUserId(name);
        message.setSenderId(id);
        message.setText(text);
        message.setDateTime(LocalDateTime.now());
        messageService.save(message);
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
