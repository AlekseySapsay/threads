package ru.job4j.ref;

import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * https://job4j.ru/profile/exercise/72/task-view/408
 * Thread без общих ресурсов
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 25.11.2021
 */

public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("name");
        cache.add(user);
        System.out.println(cache.findById(1).getName());
        Thread first = new Thread(() -> {
            user.setName("rename");
        }
        );
        System.out.println("User from ShareNotSafe before first.start: "
                + user.getName());
        first.start();
        first.join();
        System.out.println("User from ShareNotSafe after first.start: "
                + user.getName());
        System.out.println(cache.findById(1).getName());

        user.setName("Ivan in ShareNotSafe");
        System.out.println(user.getName());

        cache.findById(1).setName("rename 100500");
        System.out.println("user name from cache : "
                + cache.findById(1).getName());

        cache.findById(1).setName("rename 100500");

        User user1 = new User();
        user1.setName("rename 100500");
        cache.add(user1);
        System.out.println("user name from cache : "
                + cache.findById(2).getName());

        System.out.println("cache.findAll() " + cache.findAll().toString());

        System.out.println("cache.findById(1).getName(): " + cache.findById(1).getName());
        System.out.println("cache.findById(2).getName(): " + cache.findById(2).getName());

        cache.findById(1).setName("New Name");
        System.out.println("cache.findById(1).getName() after rename: " + cache.findById(1).getName());

        List<User> userList = new ArrayList<User>();
        userList.addAll(cache.findAll());

        System.out.println("userList.toString() " + userList.toString());
    }
}