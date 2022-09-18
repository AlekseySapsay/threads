package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import static java.lang.System.currentTimeMillis;

public class Wget implements Runnable {
    private final String url;

    private final int speed;

    private final String fileName;

    private final String fileExtension;


    public Wget(String url, int speed, String fileName, String fileExtension) {
        this.url = url;
        this.speed = speed;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/ru/job4j/thread/"
                     + fileName + "." + fileExtension)) {

            int downloadData = 0;
            byte[] dataBuffer = new byte[speed];
            int bytesReadied;
            int totalSizeBytes = 0;
            long startTimeMs = 0;
            int totalTimeSeconds = 0;
            float deltaTimeSec = 0;

            System.out.println("Start loading ... ");
            startTimeMs = System.currentTimeMillis();

            while ((bytesReadied = in.read(dataBuffer, 0, speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesReadied);
                downloadData = bytesReadied;
                deltaTimeSec = (float) ((currentTimeMillis() - startTimeMs) / 1_000_000.0);
                if ((downloadData >= speed) && (deltaTimeSec < 1)) {
                    long threadPauseMs = (long) ((1 - deltaTimeSec) * 1000);
                    Thread.sleep(threadPauseMs);
                }
                totalSizeBytes += downloadData;
                totalTimeSeconds += 1;
            }
            totalTimeSeconds = totalTimeSeconds - 1;
            System.out.println("total size bytes : " + totalSizeBytes);
            System.out.println("total time seconds : " + totalTimeSeconds);
            System.out.println("average speed : " + totalSizeBytes / totalTimeSeconds);
            System.out.println("Loading completed ...");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void validationArguments(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Not enough arguments, add additional arguments and try again");
        }
        if (!args[1].matches("^\\d+$")) {
            throw new IllegalArgumentException("Not enable data format");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validationArguments(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String fileName = args[2];
        String fileExtension = args[3];

        Thread wget = new Thread(new Wget(url, speed, fileName, fileExtension));
        wget.start();
        wget.join();
    }
}