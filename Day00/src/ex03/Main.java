package ex03;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int num = takeResult();
        printResult(num);
    }

    public static int takeResult() {
        int result = 0;
        boolean error = false;
        Scanner scanner = new Scanner(System.in);

        for (int week = 1; week < 19; week++) {
            String input = scanner.nextLine();
            if (input.equals("42")) {
                break;
            }

            if (input.equals("Week " + week)) {
                int minGrade = Integer.MAX_VALUE;
                for (int i = 0; i < 4; i++) {
                    int nextGrade = scanner.nextInt();
                    if (nextGrade > 0 && nextGrade < 10) {
                        minGrade = Math.min(minGrade, nextGrade);
                    } else {
                        printError();
                    }
                }
                result += minGrade;
                result *= 10;
                scanner.nextLine();
            } else {
                printError();
            }
        }
        return result;
    }

    public static void printResult(int minGrades) {
        minGrades = reverseNumber(minGrades);
        for (int i = 0; minGrades != 0; i++) {
            System.out.print("Week ");
            System.out.print(i + 1);
            System.out.print(" ");
            int tmp = minGrades % 10;
            minGrades /= 10;

            for (int j = 0; j < tmp; j++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
    }

    public static void printError() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }

    public static int reverseNumber(int number) {
        int result = 0;
        while (number != 0) {
            result *= 10;
            result += number % 10;
            number /= 10;
        }
        return result;
    }
}