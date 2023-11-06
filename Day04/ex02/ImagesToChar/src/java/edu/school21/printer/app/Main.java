package edu.school21.printer.app;


import com.beust.jcommander.JCommander;
import edu.school21.printer.logic.ImagesToChar;
import edu.school21.printer.logic.Args;

public class Main {
    public static void main(String[] args) {
        Args arguments = new Args();
        JCommander jCommander = new JCommander(arguments);
        jCommander.parse(args);

        String fileName = "target/classes/it.bmp";
        ImagesToChar.readBmpFile(arguments, fileName);
    }
}
