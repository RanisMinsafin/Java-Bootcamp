package ex01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        if (number < 0) {
            System.err.println("Illegal Argument");
        } else {
            isPrimeNumber(number);
        }
    }

    private static void isPrimeNumber(int number) {
        boolean isPrime = true;
        int i = 2;
        for (; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                isPrime = false;
                System.out.println("false " + --i);
                break;
            }
        }
        if (isPrime) {
            System.out.println("true " + --i);
        }
    }
}
