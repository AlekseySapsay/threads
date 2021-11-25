package ru.job4j.linked;

/**
 * https://job4j.ru/profile/exercise/72/task-view/407
 * <p>
 * <p>
 * Создание immutable class
 * Условия для иммутабельности:
 * 1. Все поля отмеченны final
 * 2. Состояние объекта не изменяется после созадания объекта
 * Т.е нет setter ов, меняющих состояние объекта
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 24.11.2021
 */

public final class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}