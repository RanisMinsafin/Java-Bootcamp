package ex02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int primeCounter = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int number = scanner.nextInt();

            if (number == 42) {
                break;
            }

            int sum = sumOfDigits(number);

            if (isPrime(sum)) {
                ++primeCounter;
            }
        }
        System.out.println("Count of coffee-request – " + primeCounter);
    }
    private static int sumOfDigits(int number){
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
    private static boolean isPrime(int number){
        int i = 2;
        for (; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
