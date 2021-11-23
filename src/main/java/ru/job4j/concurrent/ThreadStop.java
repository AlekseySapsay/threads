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
        Thread thread = new Thread(
                () -> {
                    int count = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println(count++);
                    }
                }
        );
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}