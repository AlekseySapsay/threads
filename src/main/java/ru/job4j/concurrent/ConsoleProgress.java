package ru.job4j.concurrent;

/**
 * https://job4j.ru/profile/exercise/71/task-view/401
 * <p>
 * Изучение жизненного цикла нитей
 * Thread.interrupted()
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 23.11.2021
 */
public class ConsoleProgress implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                for (int i = 0; i < 4; i++) {
                    Thread.sleep(400);
                    char[] chars = new char[]{'-', '\\', '|', '/'};
                    System.out.print("\r load: " + "process" + chars[i]);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
