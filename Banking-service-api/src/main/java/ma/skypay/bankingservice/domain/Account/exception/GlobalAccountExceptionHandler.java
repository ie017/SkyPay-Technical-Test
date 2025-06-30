package ma.skypay.bankingservice.domain.Account.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalAccountExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(
                objectError -> errors.put(objectError.getField(), objectError.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(
                errors
        );
    }

    @ExceptionHandler(EmailAlreadyExistingException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistingException(EmailAlreadyExistingException exception) {
        return ResponseEntity.badRequest().body(
                Map.of("message", " Cet e-mail est déjà utilisé")
        );
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleAccountNotFoundException(AccountNotFoundException axception) {
        return ResponseEntity.badRequest().body(
                Map.of("message", "Ce compte n'existe pas")
        );
    }

    @ExceptionHandler(AccountIdNullException.class)
    public ResponseEntity<Map<String, String>> handleAccountIdNullException(AccountIdNullException exception) {
        return ResponseEntity.badRequest().body(
          Map.of("message", " L’identifiant est manquant")
        );
    }
}
