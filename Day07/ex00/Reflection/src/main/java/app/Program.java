package app;

import menu.Menu;
import model.Car;
import model.User;

public class Program {
    public static void main(String[] args) {
        Menu menu = new Menu(User.class, Car.class);
        menu.start();
    }
}