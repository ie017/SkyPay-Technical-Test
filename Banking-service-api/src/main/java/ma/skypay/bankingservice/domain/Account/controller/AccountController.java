package ma.skypay.bankingservice.domain.Account.controller;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import ma.skypay.bankingservice.domain.Account.dto.AccountRequestDto;
import ma.skypay.bankingservice.domain.Account.dto.AccountResponseDto;
import ma.skypay.bankingservice.domain.Account.dto.Validators.CreateAccountValidationGroup;
import ma.skypay.bankingservice.domain.Account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountResponseDto>> getAccounts() {
        final List<AccountResponseDto> allAccounts = this.accountService.getAllAccounts();
        return ResponseEntity.ok().body(allAccounts);
    }

    @PostMapping
    public ResponseEntity<AccountResponseDto> createAccount(@Validated({Default.class, CreateAccountValidationGroup.class}) @RequestBody AccountRequestDto accountRequestDto) {
        final AccountResponseDto accountResponseDto = this.accountService.createAccount(accountRequestDto);
        return ResponseEntity.ok().body(accountResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDto> updateAccount(@PathVariable UUID id,
                                                            @Validated(Default.class) @RequestBody AccountRequestDto accountRequestDto) {
        final AccountResponseDto accountResponseDto = this.accountService.updateAccount(id, accountRequestDto);
        return ResponseEntity.ok().body(accountResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateAccount(@PathVariable UUID id) {
        this.accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/transactions/deposit/{id}")
    public ResponseEntity<Void> deposit(@PathVariable UUID id, @RequestParam int amount,  @RequestParam String date) {
        this.accountService.deposit(id, amount, date);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/transactions/withdraw/{id}")
    public ResponseEntity<Void> withdraw(@PathVariable UUID id, @RequestParam int amount,  @RequestParam String date) {
        this.accountService.withdraw(id, amount, date);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/transactions/printStatement/{id}")
    public ResponseEntity<Void> printStatement(@PathVariable UUID id) {
        this.accountService.printStatement(id);
        return ResponseEntity.noContent().build();
    }
}
