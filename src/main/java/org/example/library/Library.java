package org.example.library;

import java.time.LocalDateTime;
import java.util.*;

public class Library {
    public HashMap<String, List<Book>> bookHashMap;
    public HashMap<String, User> userHashMap;

    public Library(HashMap<String, List<Book>> libraryMap, HashMap<String, User> userHashMap) {
        this.bookHashMap = libraryMap;
        this.userHashMap = userHashMap;
    }

    public List<List<Book>> getAllBooks() {
        return bookHashMap.values().stream().toList();
    }

    public List<Book> getByIsbn(String isbn) {
        return bookHashMap.get(isbn);
    }

    public Book borrowBook(String isbn, String memberId) {
        User user = findUser(memberId);
        if(user.getPenaltyPoints() > 20){
            throw new IllegalStateException("Sorry but your penalty points is more than 20, so we can't borrow you the book");
        }
        Book book = findAvailableBook(isbn);
        if (isAlreadyBorrowedBook(user, book)) {
            throw new IllegalStateException("You can't borrow the same book twice");
        }
        user.addLoan(createLoan(book));
        book.borrow();
        return book;
    }

    public Book returnBook(String copyId, String memberId) {
        Book book = findExactBook(copyId);
        User user = findUser(memberId);
        Loan loan = findLoanWithActiveStatus(user, book.getCopyId());
        if (isAlreadyBorrowedBook(user, book)) {
            loan.setReturnedAt(LocalDateTime.now());
            book.returnBook();
        }

        if (loan.getReturnedAt().isAfter(loan.getDueDate())){
            user.addPenaltyPoints(5);
        }

        return book;
    }

    public Book addBook(Book book) {
        if (book == null) {
            throw new NullPointerException("Book can't be null");
        }
        if (bookHashMap.containsKey(book.getIsbn())) {
            bookHashMap.get(book.getIsbn()).add(book);
            return book;
        }
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookHashMap.put(book.getIsbn(), bookList);
        return book;
    }

    private Loan findLoanWithActiveStatus(User user, String copyId) {
        return user.getLoanList().stream()
                .filter(l -> l.getCopyId().equals(copyId))
                .filter(l -> l.getReturnedAt() == null)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find any match"));
    }

    private boolean isAlreadyBorrowedBook(User user, Book book) {
        return user.getLoanList().stream()
                .filter(l -> l.getCopyId().equals(book.getCopyId()))
                .anyMatch(l -> l.getReturnedAt() == null);
    }

    private Book findExactBook(String copyId){
        return bookHashMap.values().stream()
                .flatMap(Collection::stream)
                .filter(b -> b.getCopyId().equals(copyId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Cannot find book by copy id: " + copyId));
    }

    private Book findAvailableBook(String isbn) {
        return bookHashMap.get(isbn).stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .filter(Book::getAvailable)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Cannot find book by isbn: " + isbn));
    }

    private User findUser(String memberId) {
        if(!userHashMap.containsKey(memberId)){
            throw new NoSuchElementException("Can't find the user with member id: " + memberId);
        }
        return userHashMap.get(memberId);
    }

    private Loan createLoan(Book book) {
        return new Loan(book.getIsbn(), book.getCopyId(), LocalDateTime.now(), LocalDateTime.now().plusWeeks(1));
    }

}
