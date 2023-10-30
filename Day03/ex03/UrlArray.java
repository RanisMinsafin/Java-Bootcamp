package ex03;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class UrlArray {
    public static URL[] getList(String fileName) {
        ArrayList<URL> urlList = new ArrayList<>();
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                scanner.next();
                urlList.add(new URL(scanner.next()));
            }
        } catch (FileNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        }
        return urlList.toArray(new URL[urlList.size()]);
    }
}
