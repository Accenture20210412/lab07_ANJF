package java.example.naming;

// TODO: clean up methods


class BorrowManager {
    private final LibraryResources books;
    private final ReadersManager readersRegistry;
    private final BorrowedBooksRegistry borrowedBooksRegistry;

    BorrowManager(LibraryResources books, ReadersManager readersManager, BorrowedBooksRegistry borrowedBookRegistry) {
        this.books = books;
        this.readersRegistry = readersManager;
        this.borrowedBooksRegistry = borrowedBookRegistry;
    }

    BorrowOutcome borrow(Book book, Reader reader) {

        if (readersRegistry.checkReaderExists(reader) &&
                books.checkBookExists(book) &&
                !borrowedBooksRegistry.readerHasBookCopy(book, reader) &&
                books.availableCopies(book) > 0
        ) {
            books.deleteBookFromLibrary(book.getIsbn());
            borrowedBooksRegistry.rent(book, reader);
            return success;
        } else
        if (!readersRegistry.checkReaderExists(reader)) {
            return readerNotEnrolled;
        } else if (!books.checkBookExists(book)) {
            return notInCatalogue;
        } else if (borrowedBooksRegistry.readerHasBookCopy(book, reader)) {
            return bookAlreadyBorrowedByReader;
        } else if (books.availableCopies(book) == 0) {
            return noAvailableCopies;
        }
        return null;
    }

    ReturnOutcome giveBack(Book book, Reader reader) {
        if (!readersRegistry.checkReaderExists(reader))
            return ReturnOutcome.readerNotExists;

        if (!books.checkBookExists(book))
            return ReturnOutcome.notInCatalogue;

        if (borrowedBooksRegistry.readerHasNoBookCopy(book, reader))
            return ReturnOutcome.bookNotBorrowedByReader;

        books.addBookToLibrary(book.getIsbn());
        borrowedBooksRegistry.returnBook(book, reader);
        return ReturnOutcome.success;
    }

}