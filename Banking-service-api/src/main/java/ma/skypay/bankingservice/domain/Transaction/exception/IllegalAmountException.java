package ma.skypay.bankingservice.domain.Transaction.exception;

public class IllegalAmountException extends RuntimeException {
    public IllegalAmountException(String s) {
        super(s);
    }
}
