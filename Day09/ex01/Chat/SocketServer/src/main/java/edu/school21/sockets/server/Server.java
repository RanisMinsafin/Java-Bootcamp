package edu.school21.sockets.server;

import edu.school21.sockets.services.MessageService;
import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Component("server")
public class Server {
    private ServerSocket serverSocket;
    private UsersService usersService;
    private MessageService messageService;
    private static List<ClientHandlerThread> clients = new ArrayList<>();

    @Autowired
    public Server(UsersService usersService, MessageService messageService) {
        this.usersService = usersService;
        this.messageService = messageService;
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            int i = 1;
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("User" + i++ + " connected to the chat");
                ClientHandlerThread thread = new ClientHandlerThread(socket, usersService, messageService);
                clients.add(thread);
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendMessageToChats(String username, String message) {
        for (ClientHandlerThread client : clients) {
            client.sendMessage(username, message);
        }
    }
}
