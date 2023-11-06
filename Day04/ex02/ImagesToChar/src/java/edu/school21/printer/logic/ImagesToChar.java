package edu.school21.printer.logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ImagesToChar {
    public static void readBmpFile(Args arguments, String imagePath) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            ColoredPrinter coloredPrinter = new ColoredPrinter();
            int width = image.getWidth();
            int height = image.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    boolean isBlack = (rgb & 0x00FFFFFF) == 0;

                    String colorString = isBlack ? arguments.getBlack() : arguments.getWhite();
                    Ansi.BColor bColor = Ansi.BColor.valueOf(colorString);
                    Ansi.FColor fColor = Ansi.FColor.valueOf(colorString);

                    coloredPrinter.setBackgroundColor(bColor);
                    coloredPrinter.setForegroundColor(fColor);
                    coloredPrinter.print(" ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error: incorrect name of file");
        }
    }
}
