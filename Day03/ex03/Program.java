package ex03;

import java.net.URL;

public class Program {

    private static final String FILES_URLS_PATH = "ex03/files_urls.txt";
    public static void main(String[] args) {
        int threadsCount = Integer.parseInt(args[0].substring(15));
        URL[] urlList = UrlArray.getList(FILES_URLS_PATH);

        for (int i = 0; i < threadsCount; i++) {
            Thread thread = new Thread(new DownloadTask(urlList));
            thread.start();
        }
    }
}