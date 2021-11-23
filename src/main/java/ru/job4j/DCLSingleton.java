package ru.job4j;

/**
 * https://job4j.ru/profile/exercise/72/task-view/405
 * <p>
 * Изучение жизненного цикла нитей
 * Thread state
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 23.11.2021
 */

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