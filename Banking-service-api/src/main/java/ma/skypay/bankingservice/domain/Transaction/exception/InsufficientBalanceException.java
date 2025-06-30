package ma.skypay.bankingservice.domain.Transaction.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String s) {
        super(s);
    }
}
