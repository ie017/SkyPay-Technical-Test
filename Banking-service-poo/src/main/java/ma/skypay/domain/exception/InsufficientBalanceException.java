package ma.skypay.domain.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String s) {
        super(s);
    }
}
