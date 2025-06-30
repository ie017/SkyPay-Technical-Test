package ma.skypay.domain.exception;

public class IllegalAmountException extends RuntimeException {
    public IllegalAmountException(String s) {
        super(s);
    }
}
