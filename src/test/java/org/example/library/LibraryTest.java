package org.example.library;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void should_Return_Whole_List_Of_Books() {
        HashMap<String, List<Book>> books = createBookListImmutable();
        HashMap<String, User> users = createUser();
        Library library = new Library(books, users);

        var result = library.getAllBooks();

        assertEquals(books.size(), result.size());
        assertEquals("[[Book{isbn='123', title='King', author='Carl', copyId='1', available=true}, " +
                "Book{isbn='123', title='King', author='Carl', copyId='2', available=true}, " +
                "Book{isbn='123', title='King', author='Carl', copyId='3', available=true}], " +
                "[Book{isbn='222', title='Hard life', author='Steve', copyId='133', available=true}, " +
                "Book{isbn='222', title='Hard life', author='Steve', copyId='134', available=true}]]", result.toString());
    }


    @Test
    void should_Return_Whole_List_Of_Books_By_ISBN() {
        HashMap<String, List<Book>> books = createBookListImmutable();
        HashMap<String, User> users = createUser();
        Library library = new Library(books, users);

        var result = library.getByIsbn("222");

        assertEquals(books.size(), result.size());
        assertEquals("[Book{isbn='222', title='Hard life', author='Steve', copyId='133', available=true}, " +
                "Book{isbn='222', title='Hard life', author='Steve', copyId='134', available=true}]", result.toString());
    }

    @Test
    void should_Borrow_Book_Correctly() {
        HashMap<String, List<Book>> books = createBookListImmutable();
        HashMap<String, User> users = createUser();
        Library library = new Library(books, users);

        var result = library.borrowBook("222","123");

        assertEquals(books.get("222").getFirst(), result);
        assertNotEquals(books.get("222").getLast(), result);
        assertFalse(result.getAvailable());
    }

    @Test
    void cant_Borrow_If_Book_Is_Not_Available() {
        HashMap<String, List<Book>> books = createBookListImmutable();
        HashMap<String, User> users = createUser();
        Library library = new Library(books, users);

        library.borrowBook("222","123");
        var result = assertThrows(IllegalStateException.class, () -> library.borrowBook("222","123"));

        assertEquals("You can't borrow the same book twice", result.getMessage());
    }

    @Test
    void cant_Borrow_If_Penalty_Points_More_Than_Twenty() {
        HashMap<String, List<Book>> books = createBookListImmutable();
        HashMap<String, User> users = createUser();
        users.get("123").addPenaltyPoints(22);
        Library library = new Library(books, users);

        var result = assertThrows(IllegalStateException.class, () -> library.borrowBook("222","123"));
        assertEquals("Sorry but your penalty points is more than 20, so we can't borrow you the book", result.getMessage());
    }

    @Test
    void should_Return_Book_Successfully() {
        HashMap<String, List<Book>> books = createBookListImmutable();
        HashMap<String, User> users = createUser();
        Library library = new Library(books, users);

        var borrowResult = library.borrowBook("123","123");
        assertEquals(books.get("123").getFirst(), borrowResult);
        assertFalse(borrowResult.getAvailable());

        var returnResult = library.returnBook("1","123");
        assertEquals(books.get("123").getFirst(), returnResult);
        assertTrue(returnResult.getAvailable());
    }

    @Test
    void should_Punish_If_Book_Was_Not_Returned_In_Time() {
        HashMap<String, List<Book>> books = createBookListImmutable();
        HashMap<String, User> users = createUser();

        Book book = books.get("123").stream()
                .filter(b -> b.getCopyId().equals("1"))
                .findFirst()
                .orElseThrow();
        book.borrow();

        users.get("123").addLoan(createLoanWithOutOfDate());

        Library library = new Library(books, users);
        library.returnBook("1","123");

        assertEquals(5, users.get("123").getPenaltyPoints());
    }

    @Test
    void should_Add_Book_With_New_ISBN_Successfully() {
        HashMap<String, List<Book>> books = createBookListImmutable();
        HashMap<String, User> users = createUser();
        Library library = new Library(books, users);
        Book book = new Book("111","Title", "Author", "90");

        var result = library.addBook(book);

        assertEquals(book, result);
    }

    @Test
    void should_Add_Book_With_Present_ISBN_Successfully() {
        HashMap<String, List<Book>> books = createBookListMutable();
        HashMap<String, User> users = createUser();
        Library library = new Library(books, users);
        Book book = new Book("123", "King", "Carl", "4");

        var result = library.addBook(book);

        assertEquals(book, result);
    }


    private Loan createLoanWithOutOfDate(){
        return new Loan("123","1", LocalDateTime.now(), LocalDateTime.now().minusDays(1));
    }
    private HashMap<String, User> createUser() {
        User user = new User("123");
        HashMap<String, User> users = new HashMap<>();
        users.put("123", user);
        return users;
    }

    private HashMap<String, List<Book>> createBookListImmutable() {
        List<Book> bookList = List.of(new Book("123", "King", "Carl", "1"),
                new Book("123", "King", "Carl", "2"),
                new Book("123", "King", "Carl", "3"),
                new Book("222", "Hard life", "Steve", "133"),
                new Book("222", "Hard life", "Steve", "134"));
        HashMap<String, List<Book>> books = new HashMap<>();
        books.put("123", bookList.stream().filter(b -> b.getIsbn().equals("123")).toList());
        books.put("222", bookList.stream().filter(b -> b.getIsbn().equals("222")).toList());
        return books;
    }

    private HashMap<String, List<Book>> createBookListMutable(){
        HashMap<String,  List<Book>> books = new HashMap<>();
        books.put("123", new ArrayList<>(List.of(new Book("123", "King", "Carl", "1"))));
        books.put("222", new ArrayList<>(List.of(new Book("222", "King", "Carl", "2"))));
        return books;
    }
}