package org.example.balancesystem;

import java.util.Objects;

public class User {
    private long userId;
    private Card card;

    public User(long userId, Card card) {
        this.userId = userId;
        this.card = card;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", card=" + card +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(card, user.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, card);
    }
}
