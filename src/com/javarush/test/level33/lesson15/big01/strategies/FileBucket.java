package com.javarush.test.level33.lesson15.big01.strategies;

import com.javarush.test.level33.lesson15.big01.ExceptionHandler;
import com.javarush.test.level33.lesson15.big01.Helper;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(Helper.generateRandomString(), null);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize() {
        return path.toFile().length();
    }

    public void putEntry(Entry entry) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(entry);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry() {
        if (getFileSize() == 0) return null;
        Entry entry = null;
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            entry = (Entry) ois.readObject();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return entry;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }
}
