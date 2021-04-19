package java.example.naming;

import java.util.Set;

// TODO: class cleanup

class LibraryResources {
    private final BookCatalogue catalogue;
    private final BookWarehouse bookWarehouse;

    LibraryResources(BookCatalogue catalogue, BookWarehouse bookWarehouse) {
        this.catalogue = catalogue;
        this.bookWarehouse = bookWarehouse;
    }

    void addBook(Book book) {
        catalogue.addBook(book);
        bookWarehouse.addBook(book.getIsbn());
    }

    void addBooks(Book book, int amount) {
        catalogue.addBook(book);
        bookWarehouse.addBook(book.getIsbn(), amount);
    }

    int availableCopies(Book book) {
        return bookWarehouse.availableCopies(book.getIsbn());
    }

    Set<Book> bookCatalogue() {
        return catalogue.getUniqueBooks();
    }

    void take(ISBN isbn) {
        bookWarehouse.borrowBook(isbn);
    }

    boolean contains(Book book) {
        return catalogue.isContains(book);
    }

    void addToResources(ISBN isbn) {
        bookWarehouse.addBook(isbn);
    }
}