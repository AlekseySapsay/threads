package ru.job4j.ref;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.NotThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://job4j.ru/profile/exercise/72/task-view/408
 * Thread без общих ресурсов
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 25.11.2021
 */

@NotThreadSafe
public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    public List<User> findAll() {
        List<User> arrayList = new ArrayList();
        for (User user : users.values()) {
            arrayList.add(User.of(user.getName()));
        }
        return arrayList;
    }
}