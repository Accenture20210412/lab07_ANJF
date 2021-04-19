package java.example.naming;

import java.util.Set;

public interface BookFacade {
    void addBook(Book book);

    void addBooks(Book book, int amount);

    int getBookAmounts(Book book);

    Set<Book> getBooks();

    BorrowOutcome borrowBook(Book book, Reader reader);

    ReturnOutcome returnBook(Book book, Reader reader);
}
