package ru.job4j.concurrent;

/**
 * https://job4j.ru/profile/exercise/71/task-view/399
 * <p>
 * Изучение жизненного цикла нитей
 * Thread state
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 22.11.2021
 */
public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName()));
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName()));

        first.start();
        second.start();

        System.out.println(first.getState());
        System.out.println(second.getState());

        if (first.getState() == Thread.State.TERMINATED
                && second.getState() == Thread.State.TERMINATED) {

            System.out.println(first.getState());
            System.out.println(second.getState());

            System.out.println("Work done!");
        }
    }
}
