package java.example.naming;

import java.util.Set;

// TODO: class cleanup

class LibraryResources {
    private final Catalogue catalogue;
    private final BookWarehouse bookWarehouse;

    LibraryResources(Catalogue catalogue, BookWarehouse bookWarehouse) {
        this.catalogue = catalogue;
        this.bookWarehouse = bookWarehouse;
    }

    void addBook(Book book) {
        catalogue.add(book);
        bookWarehouse.addBook(book.getIsbn());
    }

    void addBooks(Book book, int amount) {
        catalogue.add(book);
        bookWarehouse.addBook(book.getIsbn(), amount);
    }

    int availableCopies(Book book) {
        return bookWarehouse.availableCopies(book.getIsbn());
    }

    Set<Book> bookCatalogue() {
        return catalogue.getAll();
    }

    void take(ISBN isbn) {
        bookWarehouse.borrowBook(isbn);
    }

    boolean contains(Book book) {
        return catalogue.contains(book);
    }

    void addToResources(ISBN isbn) {
        bookWarehouse.addBook(isbn);
    }
}