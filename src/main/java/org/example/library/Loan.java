package org.example.library;

import java.time.LocalDateTime;

public class Loan {
    private final String isbn;
    private final String copyId;
    private final LocalDateTime borrowedAt;
    private final LocalDateTime dueDate;
    private LocalDateTime returnedAt;

    public Loan(String isbn, String copyId, LocalDateTime borrowedAt, LocalDateTime dueDate) {
        this.isbn = isbn;
        this.copyId = copyId;
        this.borrowedAt = borrowedAt;
        this.dueDate = dueDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCopyId() {
        return copyId;
    }

    public LocalDateTime getBorrowedAt() {
        return borrowedAt;
    }

    public LocalDateTime getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(LocalDateTime returnedAt) {
        this.returnedAt = returnedAt;
    }
    public LocalDateTime getDueDate() {
        return dueDate;
    }
}
