package ma.skypay.bankingservice.domain.Account.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String s) {
        super(s);
    }
}
