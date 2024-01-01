package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersServiceImpl;
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
    private UsersServiceImpl service;
    private List<ClientHandlerThread> clients;

    @Autowired
    public Server(UsersServiceImpl service) {
        this.service = service;
        this.clients = new ArrayList<>();
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("User connected to the chat");
                ClientHandlerThread thread = new ClientHandlerThread(socket, service);
                clients.add(thread);
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
