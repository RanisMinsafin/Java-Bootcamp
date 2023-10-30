package ex03;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static java.nio.file.Files.*;

public class DownloadTask implements Runnable {
    private final String DOWNLOAD_START_MESSAGE = "%s start download file number %d\n";
    private final String DOWNLOAD_FINISH_MESSAGE = "%s finish download file number %d\n";

    private final URL[] urlArray;
    private static int numberOfFile = 1;

    public DownloadTask(URL[] urlArray) {
        this.urlArray = urlArray;
    }

    @Override
    public void run() {
        downloadFile();
    }

    private void downloadFile() {
        int nextFileToDownload;
        synchronized (this) {
            nextFileToDownload = numberOfFile;
            numberOfFile++;
        }

        while (nextFileToDownload <= urlArray.length) {
            System.out.printf(DOWNLOAD_START_MESSAGE, Thread.currentThread().getName(), nextFileToDownload);
            String fileName = FileNameGenerator.generateFileName(urlArray[nextFileToDownload - 1]);
            try (InputStream in = new BufferedInputStream(urlArray[nextFileToDownload - 1].openStream())) {
                copy(in, Path.of(fileName), StandardCopyOption.REPLACE_EXISTING);
                System.out.printf(DOWNLOAD_FINISH_MESSAGE, Thread.currentThread().getName(), nextFileToDownload);
                synchronized (this) {
                    nextFileToDownload = numberOfFile;
                    numberOfFile++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
