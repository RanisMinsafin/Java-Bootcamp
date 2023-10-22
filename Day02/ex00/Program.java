package ex00;

import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;

public class Program {
    private static Map<String, String> signatures = new HashMap<>();

    public static void main(String[] args) {
        detectFileType();
    }

    public static void detectFileType() {
        loadSignatures();
        String fileType = determineTypeOfFile();
        if (!fileType.isEmpty()) {
            writeResult(fileType);
        }
    }

    private static void loadSignatures() {
        try (FileInputStream inputStream = new FileInputStream("ex00/signatures.txt");
             Scanner scanner = new Scanner(inputStream);) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(", ");
                signatures.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            System.out.println("Error: signature file not found");
        }
    }

    private static String determineTypeOfFile() {
        System.out.println("Input the full path of the file:");
        try (Scanner scanner = new Scanner(System.in);
             FileInputStream fileInputStream = new FileInputStream(scanner.nextLine());) {
            byte[] byteSignature = new byte[8];
            fileInputStream.read(byteSignature);
            String fileSignatureString = bytesToHexString(byteSignature);

            for (Map.Entry<String, String> entry : signatures.entrySet()) {
                String fileType = entry.getKey();
                String fileSignature = entry.getValue();

                if (fileSignatureString.startsWith(fileSignature)) {
                    System.out.println("PROCESSED");
                    return fileType;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: input file not found");
        }
        System.out.println("UNDEFINED");
        return "";
    }

    private static String bytesToHexString(byte[] fileSignature) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < fileSignature.length; i++) {
            hexString.append(String.format("%02X", fileSignature[i]));
            if (i < fileSignature.length - 1) {
                hexString.append(" ");
            }
        }
        return hexString.toString();
    }

    private static void writeResult(String fileType) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("ex00/result.txt", true)) {
            byte[] fileTypeBytes = (fileType + "\n").getBytes(StandardCharsets.UTF_8);
            fileOutputStream.write(fileTypeBytes);
        } catch (IOException e) {
            System.out.println("Error: result file not found");
        }
    }
}