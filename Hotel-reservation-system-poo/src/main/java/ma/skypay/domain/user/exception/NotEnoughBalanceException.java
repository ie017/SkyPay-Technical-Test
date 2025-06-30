package ma.skypay.domain.user.exception;

public class NotEnoughBalanceException extends RuntimeException {
    public NotEnoughBalanceException(String s) {
        super(s);
    }
}
