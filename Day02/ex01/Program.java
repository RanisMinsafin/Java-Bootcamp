package ex01;

import java.io.*;
import java.util.*;

public class Program {
    private static Set<String> uniqueWords = new TreeSet<>();

    public static void main(String[] args) {
        compareWords(args[0], args[1]);
    }

    public static void compareWords(String fileName1, String filename2) {
        ArrayList<String> wordsFromFile1 = new ArrayList<>();
        ArrayList<String> wordsFromFile2 = new ArrayList<>();

        fillSet(fileName1, wordsFromFile1);
        fillSet(filename2, wordsFromFile2);

        fillDictionary();

        ArrayList<Integer> firstVector = findFrequency(wordsFromFile1);
        ArrayList<Integer> secondVector = findFrequency(wordsFromFile2);

        findSimilarity(firstVector, secondVector);
    }

    private static void fillSet(String fileName, ArrayList<String> fileWords) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
             Scanner scanner = new Scanner(fileReader)) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                uniqueWords.add(word);
                fileWords.add(word);
            }
        } catch (IOException e) {
            System.out.println("Error: input file not found");
        }
    }

    private static void fillDictionary() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ex01/dictionary.txt"))) {
            int wordCount = 0;
            for (String word : uniqueWords) {
                writer.write(word);
                wordCount++;
                if (wordCount < uniqueWords.size()) {
                    writer.write(", ");
                }
            }
        } catch (IOException e) {
            System.out.println("Error: unable to write dictionary file");
        }
    }

    private static ArrayList<Integer> findFrequency(ArrayList<String> allWords) {
        ArrayList<Integer> frequency = new ArrayList<>(uniqueWords.size());
        for (String uniqueWord : uniqueWords) {
            int count = 0;
            for (String word : allWords) {
                if (uniqueWord.equals(word)) {
                    count++;
                }
            }
            frequency.add(count);
        }
        return frequency;
    }

    private static void findSimilarity(ArrayList<Integer> firstVector, ArrayList<Integer> secondVector) {
        int numerator = 0;
        for (int i = 0; i < uniqueWords.size(); i++) {
            numerator += firstVector.get(i) * secondVector.get(i);
        }
        double denominator = calculateDenominator(firstVector) * calculateDenominator(secondVector);

        if(denominator == 0){
            System.out.println("Similarity = 0.0");
        } else {
            double similarity = numerator / denominator;
            double roundedSimilarity = Math.floor(similarity * 100) / 100.0;
            System.out.println("Similarity = " + roundedSimilarity);
        }
    }

    private static double calculateDenominator(ArrayList<Integer> vector) {
        double sumOfSquares = 0;
        for (int value : vector) {
            sumOfSquares += value * value;
        }
        return Math.sqrt(sumOfSquares);
    }
}
