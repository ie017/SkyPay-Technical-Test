package ma.skypay;

import ma.skypay.domain.service.AccountService;
import ma.skypay.domain.service.imp.AccountServiceImp;

public class Main {
    public static void main(String[] args) {
        AccountService account = new AccountServiceImp();
        account.deposit(1000, "10-01-2012");
        account.deposit(2000, "13-01-2012");
        account.withdraw(500, "14-01-2012");
        account.printStatement();
    }
}