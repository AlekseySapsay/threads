package ru.job4j.buffer;
/**
 * https://job4j.ru/profile/exercise/73/task-view/409
 * Критическая секция с блоком synchronized
 * и монитор this
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 25.11.2021
 */

public class Buffer {
    private StringBuilder buffer = new StringBuilder();

    public synchronized void add(int value) {
        System.out.print(value);
        buffer.append(value);
    }

    @Override
    public synchronized String toString() {
        return buffer.toString();
    }
}