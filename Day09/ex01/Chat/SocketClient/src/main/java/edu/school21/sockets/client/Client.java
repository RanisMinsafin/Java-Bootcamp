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

            ReadMessageThread read = new ReadMessageThread();
            WriteMessageThread write = new WriteMessageThread();

            read.start();
            write.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private class ReadMessageThread extends Thread {
        @Override
        public void run() {
            try {
                readMessage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void readMessage() throws IOException {
            String serverMessage;
            while ((serverMessage = input.readLine()) != null) {
                System.out.println(serverMessage);
                if (serverMessage.equals("You have left the chat!") ||
                        serverMessage.equals("Incorrect password!") ||
                        serverMessage.equals("Incorrect action!")) {
                    closeConnection();
                    break;
                }
            }
        }
    }

    private class WriteMessageThread extends Thread {
        @Override
        public void run() {
            try {
                writeMessage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeMessage() throws IOException {
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    String clientMessage = scanner.nextLine();
                    output.println(clientMessage);
                }
            }
        }
    }

    private void closeConnection() {
        try {
            input.close();
            output.close();
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
