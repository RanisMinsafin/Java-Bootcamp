package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagesToChar {
    public static void readBmpFile(char whiteChar, char blackChar, String imagePath) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            int width = image.getWidth();
            int height = image.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    boolean isBlack = (rgb & 0x00FFFFFF) == 0;
                    char pixelChar = isBlack ? blackChar : whiteChar;
                    System.out.print(pixelChar);
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error: incorrect name of file");
        }
    }
}
