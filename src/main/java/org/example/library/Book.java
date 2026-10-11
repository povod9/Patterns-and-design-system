package org.example.library;

import java.util.Objects;

public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private final String copyId;
    private boolean available;

    public Book(String isbn, String title, String author, String copyId) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.copyId = copyId;
        this.available = true;
    }

    public void borrow() {
        if (!available) {
            throw new IllegalStateException("This book is not available now");
        }
        setAvailable(false);
    }

    public void returnBook() {
        if (available) {
            throw new IllegalStateException("This book is not taken");
        }
        setAvailable(true);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean getAvailable() {
        return available;
    }

    public String getCopyId() {
        return copyId;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", copyId='" + copyId + '\'' +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(copyId, book.copyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, author, copyId);
    }
}
