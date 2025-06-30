package ma.skypay.bankingservice.domain.Account.exception;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailAlreadyExistingException extends RuntimeException {
    public EmailAlreadyExistingException(String message) {
        super(message);
    }
}
