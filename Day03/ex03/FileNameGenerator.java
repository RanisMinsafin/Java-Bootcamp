package ex03;

import java.net.URL;

public class FileNameGenerator {
    public static String generateFileName(URL url) {
        String urlString = url.toString();
        int lastSlashIndex = urlString.lastIndexOf('/');
        if (lastSlashIndex != -1 && lastSlashIndex < urlString.length() - 1) {
            return urlString.substring(lastSlashIndex + 1);
        }
        return "unknown";
    }
}