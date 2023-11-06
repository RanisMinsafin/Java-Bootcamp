package edu.school21.printer.app;


import edu.school21.printer.logic.ImagesToChar;

public class Main {
    public static void main(String[] args) {
        char whiteChar = args[0].charAt(0);
        char blackChar  = args[1].charAt(0);
        String fileName = args[2];
        ImagesToChar.readBmpFile(whiteChar, blackChar, fileName);
    }
}
