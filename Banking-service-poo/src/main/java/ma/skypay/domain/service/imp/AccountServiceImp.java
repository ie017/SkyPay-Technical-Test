package ma.skypay.domain.service.imp;

import ma.skypay.domain.exception.InsufficientBalanceException;
import ma.skypay.domain.model.Transaction;
import ma.skypay.domain.service.AccountService;
import ma.skypay.domain.util.Printer;
import ma.skypay.domain.validator.AmountValidator;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImp implements AccountService {

    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void deposit(int amount, String date) {
        AmountValidator.validateAmount(amount);
        transactions.add(new Transaction(date, amount));
    }

    @Override
    public void withdraw(int amount, String date) {
        AmountValidator.validateAmount(amount);
        final int sum = transactions.stream()
                .mapToInt(Transaction::getAmount)
                .sum();
        if (sum < amount) throw new InsufficientBalanceException("Le solde est insuffisant pour ce retrait");
        transactions.add(new Transaction(date, -amount));
    }

    @Override
    public void printStatement() {
        Printer.print(transactions);
    }


}
