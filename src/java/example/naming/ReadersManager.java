package java.example.naming;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;


/**
 * klasa odpowiada za zarządzanie czytelnikami w obrębie naszej biblioteki
 */
class ReadersManager {
    private final List<Reader> readers;

    ReadersManager() {
        readers = new ArrayList<>();
    }

    void addReader(Reader reader) {
        readers.add(reader);
    }

    List<Reader> getReaders() {
        return unmodifiableList(readers);
    }

    boolean checkReaderExists(Reader reader) {
        return readers.contains(reader);
    }
}