package java.example.naming;

import java.util.List;

public interface ReaderFacade {
    void addNewReader(Reader reader);

    List<Reader> getReaders();
}
