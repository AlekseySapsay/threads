package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private long startTime = 0;
    private float totalTime = 0;
    private float size = 0;
    private float sleepInMiliseconds = 0;


    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            System.out.println("Start loading ... ");
            System.out.println("Loaded.");
            int bytesRead;
            startTime = System.nanoTime();
            System.out.println("startTime :" + startTime);
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
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

    public static void main(String[] args) throws InterruptedException {
        String url;
        url = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        int speed;
        speed = 10000;
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}