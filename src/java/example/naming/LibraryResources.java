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
        return catalogue.getAll();
    }

    void deleteBookFromLibrary(ISBN isbn) {
        bookWarehouse.borrowBook(isbn);
    }

    boolean checkBookExists(Book book) {
        return catalogue.contains(book);
    }

    void addBookToLibrary(ISBN isbn) {
        bookWarehouse.addBook(isbn);
    }
}