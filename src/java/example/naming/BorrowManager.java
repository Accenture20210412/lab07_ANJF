package java.example.naming;

// TODO: clean up methods


import static java.example.naming.BorrowOutcome.*;

class BorrowManager {
    private final Resources libraryResources;
    private final ReadersManagerInterface readersManager;
    private final BorrowedBooksRegistryInterface borrowedBooksRegistry;

    BorrowManager(Resources books, ReadersManagerInterface readersManager, BorrowedBooksRegistryInterface borrowedBookRegistry) {
        this.libraryResources = books;
        this.readersManager = readersManager;
        this.borrowedBooksRegistry = borrowedBookRegistry;
    }

    BorrowOutcome borrowBook(Book book, Reader reader) {
        if (isRegister(reader)) {
            return READER_NOT_ENROLLED;
        } else if (isAvailableInCatalogue(book)) {
            return NOT_IN_CATALOGUE;
        } else if (isAlreadyBorrowed(book, reader)) {
            return BOOK_ALREADY_BORROWED_BY_READER;
        } else if (copyIsAvailable(book)) {
            return NO_AVAILABLE_COPIES;
        } else {
            return borrow(book, reader);
        }
    }


    ReturnOutcome returnBook(Book book, Reader reader) {
        if (isRegister(reader)) {
            return ReturnOutcome.readerNotEnrolled;
        }else
        if (!isAvailableInCatalogue(book)) {
            return ReturnOutcome.notInCatalogue;
        }else
        if (isAlreadyBorrowed(book, reader)) {
            return ReturnOutcome.bookNotBorrowedByReader;
        }else {
            return giveBack(book, reader);
        }
    }

    private BorrowOutcome borrow(Book book, Reader reader) {
        libraryResources.take(book.getIsbn());
        borrowedBooksRegistry.borrow(book, reader);
        return SUCCESS;
    }

    private ReturnOutcome giveBack(Book book, Reader reader) {
        libraryResources.addToResources(book.getIsbn());
        borrowedBooksRegistry.returnBook(book, reader);
        return ReturnOutcome.success;
    }

    private boolean isAlreadyBorrowed(Book book, Reader reader) {
        return borrowedBooksRegistry.readerHasNoBookCopy(book, reader);
    }


    private boolean copyIsAvailable(Book book) {
        return libraryResources.availableCopies(book) == 0;
    }

    private boolean isAvailableInCatalogue(Book book) {
        return libraryResources.contains(book);
    }

    private boolean isRegister(Reader reader) {
        return !readersManager.contains(reader);
    }
}