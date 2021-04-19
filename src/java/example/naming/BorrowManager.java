package java.example.naming;

// TODO: clean up methods

import static java.example.naming.BorrowOutcome.*;

class BorrowManager {
    private final LibraryResources libraryResources;
    private final ReadersManager readersManager;
    private final BorrowedBooksRegistry borrowedBooksRegistry;

    BorrowManager(LibraryResources libraryResources, ReadersManager readersManager, BorrowedBooksRegistry borrowedBookRegistry) {
        this.libraryResources = libraryResources;
        this.readersManager = readersManager;
        this.borrowedBooksRegistry = borrowedBookRegistry;
    }

    BorrowOutcome borrowBook(Book book, Reader reader) {
        if (isRegister(reader)) {
            return readerNotEnrolled;
        } if (isAvailableInCatalogue(book)) {
            return notInCatalogue;
        } if (isAlreadyBorrowed(book, reader)) {
            return bookAlreadyBorrowedByReader;
        } if (copyIsAvailable(book)) {
            return noAvailableCopies;
        } else {
            return borrow(book, reader);
        }
    }


    ReturnOutcome returnBook(Book book, Reader reader) {
        if (isRegister(reader)) {
            return ReturnOutcome.READER_NOT_ENROLLED;
        } if (!isAvailableInCatalogue(book)) {
            return ReturnOutcome.NOT_IN_CATALOGUE;
        } if (isAlreadyBorrowed(book, reader)) {
            return ReturnOutcome.BOOK_NOT_BORROWED_BY_READER;
        } else {
            return giveBack(book, reader);
        }
    }

    private BorrowOutcome borrow(Book book, Reader reader) {
        libraryResources.deleteBookFromLibrary(book.getIsbn());
        borrowedBooksRegistry.borrow(book, reader);
        return success;
    }

    private ReturnOutcome giveBack(Book book, Reader reader) {
        libraryResources.addBookToLibrary(book.getIsbn());
        borrowedBooksRegistry.returnBook(book, reader);
        return ReturnOutcome.SUCCESS;
    }

    private boolean isAlreadyBorrowed(Book book, Reader reader) {
        return borrowedBooksRegistry.readerHasNoBookCopy(book, reader);
    }


    private boolean copyIsAvailable(Book book) {
        return libraryResources.availableCopies(book) == 0;
    }

    private boolean isAvailableInCatalogue(Book book) {
        return libraryResources.checkBookExists(book);
    }

    private boolean isRegister(Reader reader) {
        return !readersManager.checkReaderExists(reader);
    }

}