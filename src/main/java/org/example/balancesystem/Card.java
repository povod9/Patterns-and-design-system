package org.example.balancesystem;

import java.math.BigDecimal;
import java.util.Objects;

public class Card {
    private long cardId;
    private BigDecimal balance;

    public Card(long cardId, BigDecimal balance) {
        this.cardId = cardId;
        this.balance = balance;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    private void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal topUpByATM(BigDecimal amount){
        setBalance(this.balance.add(amount));
        return balance;
    }

    public BigDecimal withdrawByATM(BigDecimal amount){
        if(this.balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0) throw new RuntimeException("Negative balance");
        setBalance(this.balance.subtract(amount));
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardId == card.cardId && Objects.equals(balance, card.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, balance);
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", balance=" + balance +
                '}';
    }
}
