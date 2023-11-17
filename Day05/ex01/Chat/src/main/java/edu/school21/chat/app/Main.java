package edu.school21.chat.app;


import edu.school21.chat.repository.MessagesRepositoryJdbcImpl;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            MessagesRepositoryJdbcImpl repository = new MessagesRepositoryJdbcImpl(DatabaseConnector.start());
            while(true){
                System.out.println("Enter a message ID");
                String line = scanner.next();
                if(line.equals("exit")){
                    break;
                }
                int id = Integer.parseInt(line);
                repository.findById(id);
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
}
