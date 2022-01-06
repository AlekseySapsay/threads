package ru.job4j.net;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * https://job4j.ru/profile/exercise/71/task-view/403
 * Критическая секция с блоком synchronized
 * и монитор this
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 06.01.2022
 */
public class FileDownload {
    public static void main(String[] args) throws Exception {
        String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
