package ex02;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileManager {
    private static Path currentDirectory;

    public static void main(String[] args) {
        currentDirectory = Path.of(args[0]);
        System.out.println(currentDirectory);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("-> ");
                String command = scanner.next();

                switch (command) {
                    case "exit":
                        return;
                    case "cd":
                        changeDirectory(scanner.nextLine().trim());
                        break;
                    case "ls":
                        displayFolder();
                        break;
                    case "mv":
                        String sourceFileName = scanner.next();
                        String destinationFileName = scanner.next();
                        moveFile(sourceFileName, destinationFileName);
                        break;
                    default:
                        System.out.println("Error: Unknown command");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: Incorrect path");
        }
    }


    private static void changeDirectory(String newDirectory) {
        if (newDirectory.equals("..")) {
            currentDirectory = currentDirectory.getParent();
        } else if (newDirectory.startsWith("../")) {
            newDirectory = newDirectory.replace("../", "");
            currentDirectory = currentDirectory.getParent().resolve(newDirectory);
        } else {
            currentDirectory = currentDirectory.resolve(newDirectory);
        }

        if (Files.isDirectory(currentDirectory)) {
            System.out.println(currentDirectory);
        } else {
            System.out.println("cd: not a directory: " + currentDirectory);
        }
    }

    private static void displayFolder() {
        String[] files = currentDirectory.toFile().list();
        for (String fileName : files) {
            File file = new File(fileName);
            System.out.println(file + " " + file.length() + " KB");
        }
    }

    private static void moveFile(String sourceFileName, String destinationFileName) {
        Path sourcePath = currentDirectory.resolve(sourceFileName);
        Path destinationPath = currentDirectory.resolve(destinationFileName);
        try {
            Files.move(sourcePath, destinationPath);
        } catch (Exception e) {
            System.out.println("mv: " + sourceFileName + " to " + destinationFileName + ": No such file or directory");
        }
    }
}
