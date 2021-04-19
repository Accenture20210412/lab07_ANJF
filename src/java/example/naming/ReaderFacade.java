package java.example.naming;

import java.util.List;

public interface ReaderFacade {
    void newReader(Reader reader);

    List<Reader> loadReaders();
}
