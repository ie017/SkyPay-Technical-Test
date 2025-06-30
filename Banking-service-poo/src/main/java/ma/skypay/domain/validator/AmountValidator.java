package ma.skypay.domain.validator;

import ma.skypay.domain.exception.IllegalAmountException;

public class AmountValidator {

    public static void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalAmountException("Le montant doit être supérieur à zéro");
        }
    }
}
