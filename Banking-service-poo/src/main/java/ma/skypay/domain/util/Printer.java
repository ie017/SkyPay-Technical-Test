package ma.skypay.domain.util;

import ma.skypay.domain.model.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Printer {

    private static final Logger logger = Logger.getLogger(Printer.class.getName());

    public static void print(List<Transaction> transactions) {
        System.out.println(String.format("%-12s || %-10s || %-10s", "DATE", "AMOUNT", "BALANCE"));
        List<String> records = new ArrayList<>();
        AtomicInteger balance = new AtomicInteger(0);
        transactions.forEach(
                transaction -> {
                    int nvBalance = balance.addAndGet(transaction.getAmount());
                    records.add((String.format(
                            "%-12s || %-10d || %-10d",
                            transaction.getDate(),
                            transaction.getAmount(),
                            nvBalance
                    )));
                });
        Collections.reverse(records);
        records.forEach(System.out::println);
    }

}
