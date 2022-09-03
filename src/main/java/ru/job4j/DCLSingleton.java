package ru.job4j;


public final class DCLSingleton {

    private static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        DCLSingleton localInstance = inst;
        if (localInstance == null) {
            synchronized (DCLSingleton.class) {
                if (localInstance == null) {
                    inst = localInstance;
                    localInstance = new DCLSingleton();
                }
            }
        }
        return localInstance;
    }
}