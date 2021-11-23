package ru.job4j.concurrent;

/**
 * https://job4j.ru/profile/exercise/71/task-view/401
 * <p>
 * Изучение жизненного цикла нитей
 * Thread state
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 23.11.2021
 */
public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println("start ...");
                            Thread.sleep(4000);
                            System.out.println("????");
                        } catch (InterruptedException e) {
                            System.out.println("do interrupt " + Thread.currentThread().isInterrupted());
                            System.out.println(Thread.currentThread().getState());
                            Thread.currentThread().interrupt();
                            System.out.println("after interrupt " + Thread.currentThread().isInterrupted());
                            System.out.println(Thread.currentThread().getState());
                        }
                    }
                }
        );
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
        progress.join();
    }
}