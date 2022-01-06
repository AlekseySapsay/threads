package ru.job4j.io;

import java.io.*;

/**
 * https://job4j.ru/profile/exercise/73/task-view/410
 * Критическая секция с блоком synchronized
 * и монитор this
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 25.11.2021
 */

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }


    public String getContent() throws IOException {
        try (InputStream i = new FileInputStream(file)) {
            String output = "";
            int data;
            while ((data = i.read()) > 0) {
                output += (char) data;
            }
            return output;
        }
    }

    public String getContentWithoutUnicode() throws IOException {
        try (InputStream i = new FileInputStream(file)) {
            String output = "";
            int data;
            while ((data = i.read()) > 0) {
                if (data < 0x80) {
                    output += (char) data;
                }
            }
            return output;
        }
    }

    public void saveContent(String content) throws IOException {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        }
    }
}