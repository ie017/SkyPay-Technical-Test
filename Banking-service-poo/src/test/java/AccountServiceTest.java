import ma.skypay.domain.exception.IllegalAmountException;
import ma.skypay.domain.exception.InsufficientBalanceException;
import ma.skypay.domain.service.AccountService;
import ma.skypay.domain.service.imp.AccountServiceImp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {
    @Test
    public void testDepositAndWithdrawOperations() {
        AccountService account = new AccountServiceImp();

        assertDoesNotThrow(() -> {
            account.deposit(700, "10-01-2012");
            account.withdraw(300, "11-01-2012");
        });
    }

    @Test
    public void testInvalidAmountToDeposit() {
        AccountService account = new AccountServiceImp();
        assertThrows(IllegalAmountException.class, () -> account.deposit(0, "10-01-2012"));
    }

    @Test
    public void testInsufficientBalanceWhenWithdrawOperation() {
        AccountService account = new AccountServiceImp();
        account.deposit(700, "10-01-2012");
        assertThrows(InsufficientBalanceException.class, () -> account.withdraw(1000, "11-01-2012"));
    }
}
