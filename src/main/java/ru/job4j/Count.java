package ru.job4j;

/**
 * https://job4j.ru/profile/exercise/72/task-view/404
 * <p>
 * Изучение жизненного цикла нитей
 * Thread state
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 23.11.2021
 */

public class Count {
    private int value;

    public synchronized void increment() {
        value++;
    }

    public synchronized int get() {
        return value;
    }
}