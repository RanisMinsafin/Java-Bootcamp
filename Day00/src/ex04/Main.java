package ex04;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();

        int[] frequencyArray = new int[128];
        countCharacterFrequency(inputString, frequencyArray);

        int[] topCounts = new int[10];
        char[] topChars = new char[10];
        findTopCharacters(frequencyArray, topCounts, topChars);

        printCharacterCounts(topCounts);
        printCharacterHistogram(topCounts, topChars);
    }

    private static void countCharacterFrequency(String input, int[] frequencyArray) {
        for (char c : input.toCharArray()) {
            if (c >= 0 && c <= 127) {
                frequencyArray[c]++;
            }
        }
    }

    private static void findTopCharacters(int[] frequencyArray, int[] topCounts, char[] topChars) {
        for (int i = 0; i < 10; i++) {
            int maxCount = -1;
            char maxChar = '\0';
            for (int j = 0; j < frequencyArray.length; j++) {
                if (maxCount < frequencyArray[j]) {
                    maxCount = frequencyArray[j];
                    maxChar = (char) j;
                }
            }
            if (maxCount > 0) {
                topCounts[i] = maxCount;
                topChars[i] = maxChar;
                frequencyArray[maxChar] = 0;
            }
        }
    }

    private static void printCharacterCounts(int[] topCounts) {
        System.out.println();
        int maxCount = topCounts[0];
        for (int i = 0; i < 10; i++) {
            if (topCounts[i] == maxCount) {
                System.out.print(topCounts[i] + "\t");
            }
        }
        System.out.println();
    }

    private static void printCharacterHistogram(int[] topCounts, char[] topChars) {
        int maxCount = topCounts[0];
        for (int i = 10; i > 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (topCounts[j] * 10 / maxCount >= i)
                    System.out.print("#\t");
                if (topCounts[j] * 10 / maxCount == i - 1) {
                    if (topCounts[j] != 0) {
                        System.out.print(topCounts[j] + "\t");
                    }
                }
            }
            System.out.println();
        }
        for (char c : topChars) {
            System.out.print(c + "\t");
        }
    }
}
