package java.example.naming;

// TODO: clean up methods


import static java.example.naming.BorrowOutcome.*;

class BorrowManager {
    private final LibraryResources libraryResources;
    private final ReadersManager readersManager;
    private final BorrowedBooksRegistry borrowedBooksRegistry;

    BorrowManager(LibraryResources books, ReadersManager readersManager, BorrowedBooksRegistry borrowedBookRegistry) {
        this.libraryResources = books;
        this.readersManager = readersManager;
        this.borrowedBooksRegistry = borrowedBookRegistry;
    }

    BorrowOutcome borrowBook(Book book, Reader reader) {

        if (readersManager.contains(reader) &&
                libraryResources.contains(book) &&
                !borrowedBooksRegistry.readerHasBookCopy(book, reader) &&
                libraryResources.availableCopies(book) > 0
        ) {
            libraryResources.take(book.getIsbn());
            borrowedBooksRegistry.borrow(book, reader);
            return success;
        } else
        if (!readersManager.contains(reader)) {
            return readerNotEnrolled;
        } else if (!libraryResources.contains(book)) {
            return notInCatalogue;
        } else if (borrowedBooksRegistry.readerHasBookCopy(book, reader)) {
            return bookAlreadyBorrowedByReader;
        } else if (libraryResources.availableCopies(book) == 0) {
            return noAvailableCopies;
        }
        return null;
    }

    ReturnOutcome returnBook(Book book, Reader reader) {
        if (!readersManager.contains(reader))
            return ReturnOutcome.readerNotEnrolled;

        if (!libraryResources.contains(book))
            return ReturnOutcome.notInCatalogue;

        if (borrowedBooksRegistry.readerHasNoBookCopy(book, reader))
            return ReturnOutcome.bookNotBorrowedByReader;

        libraryResources.addToResources(book.getIsbn());
        borrowedBooksRegistry.returnBook(book, reader);
        return ReturnOutcome.success;
    }

}