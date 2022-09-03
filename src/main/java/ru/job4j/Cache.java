package ru.job4j;

public final class Cache {
    private static Cache2 cache;

    public synchronized static Cache2 instOf() {
        if (cache == null) {
            cache = new Cache2();
        }
        return cache;
    }
}