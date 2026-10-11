package org.example.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private final String memberId;
    private List<Loan> loanList = new ArrayList<>();
    private int penaltyPoints;


    public User(String memberId) {
        this.memberId = memberId;
    }

    public int getPenaltyPoints() {
        return penaltyPoints;
    }

    public void addPenaltyPoints(int penaltyPoints) {
        this.penaltyPoints += penaltyPoints;
    }

    public String getMemberId() {
        return memberId;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    public Loan addLoan(Loan loan) {
        if (loan == null) {
            throw new NullPointerException("Loan can't be null");
        }
        loanList.add(loan);
        return loan;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(memberId, user.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}
