package ru.job4j.concurrent;

/**
 * https://job4j.ru/profile/exercise/71/task-view/400
 * <p>
 * Изучение жизненного цикла нитей
 * Thread state
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 23.11.2021
 */

public class ThreadSleep {
    public static void main(String[] args) {
        Thread thread =
                new Thread(() -> {
                    try {
                        System.out.println("Start loading...");
                        Thread.sleep(3000);
                        System.out.println("Loaded");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        thread.start();
        System.out.println("Main");
    }
}
