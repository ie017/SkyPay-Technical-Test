package ma.skypay.bankingservice.domain.Transaction.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalTransactionExceptionHandler {

    @ExceptionHandler(IllegalAmountException.class)
    public ResponseEntity<Map<String, String>> handleIllegalAmountException(IllegalAmountException exception) {
        return ResponseEntity.badRequest().body(
                Map.of("message", "Le montant du retrait doit être supérieur à zéro")
        );
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<Map<String, String>> handleInsufficientBalanceException(InsufficientBalanceException exception) {
        return ResponseEntity.badRequest().body(
          Map.of("message", "Le solde est insuffisant pour ce retrait")
        );
    }
}
