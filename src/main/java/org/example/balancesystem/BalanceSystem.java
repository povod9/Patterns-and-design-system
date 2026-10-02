package org.example.balancesystem;

import java.math.BigDecimal;

public class BalanceSystem {
    static void main() {
        Card nazarCard = new Card(0,new BigDecimal("0.00"));
        Card radikCard = new Card(0,new BigDecimal("0.00"));
        User nazar = new User(0, nazarCard);
        User radik = new User(0,  radikCard);

        System.out.println(nazarCard.topUpByATM(BigDecimal.valueOf(100.00)));
        System.out.println(nazarCard.topUpByATM(BigDecimal.valueOf(100.00)));
        System.out.println(nazarCard.withdrawByATM(BigDecimal.valueOf(100.00)));

        sendMoney(nazarCard, radikCard, BigDecimal.valueOf(150.00));

        System.out.println(nazarCard);
        System.out.println(radikCard);

    }

    public static void sendMoney(Card sendCard, Card recipientCard, BigDecimal amount){
        sendCard.withdrawByATM(amount);
        recipientCard.topUpByATM(amount);
    }
}
