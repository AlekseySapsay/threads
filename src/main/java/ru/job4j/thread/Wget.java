package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            long startTime = 0;
            float totalTime = 0;
            float size = 0;
            float sleepInMiliseconds = 0;

            byte[] dataBuffer = new byte[speed];
            System.out.println("Start loading ... ");
            System.out.println("Loaded.");
            int bytesRead;
            startTime = System.nanoTime();
            System.out.println("startTime :" + startTime);

            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                totalTime = System.nanoTime() - startTime;
                System.out.println("totalTime :" + totalTime);
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                sleepInMiliseconds = (speed * 1_000_000_000 - totalTime);
                System.out.println("sleepInMiliseconds  : " + sleepInMiliseconds);

                if (totalTime < speed * 1_000_000_000) {
                    Thread.sleep((long) sleepInMiliseconds / 1_000_000);
                }
                startTime = System.nanoTime();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void validationArguments(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Not enough arguments, add additional arguments and try again");
        }
        if (!args[0].endsWith(".xml")) {
            throw new IllegalArgumentException("Invalid link for downloading");
        }
        if (!args[1].matches("^(1024)")) {
            throw new IllegalArgumentException("Not enable data format");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validationArguments(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}