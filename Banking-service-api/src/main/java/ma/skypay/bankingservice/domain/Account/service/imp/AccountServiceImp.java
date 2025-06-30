package ma.skypay.bankingservice.domain.Account.service.imp;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.skypay.bankingservice.domain.Account.dto.AccountRequestDto;
import ma.skypay.bankingservice.domain.Account.dto.AccountResponseDto;
import ma.skypay.bankingservice.domain.Account.entity.Account;
import ma.skypay.bankingservice.domain.Account.exception.AccountIdNullException;
import ma.skypay.bankingservice.domain.Account.exception.AccountNotFoundException;
import ma.skypay.bankingservice.domain.Account.exception.EmailAlreadyExistingException;
import ma.skypay.bankingservice.domain.Account.mapper.AccountMapper;
import ma.skypay.bankingservice.domain.Account.repository.AccountRepository;
import ma.skypay.bankingservice.domain.Account.service.AccountService;
import ma.skypay.bankingservice.domain.Transaction.entity.Transaction;
import ma.skypay.bankingservice.domain.Transaction.exception.IllegalAmountException;
import ma.skypay.bankingservice.domain.Transaction.exception.InsufficientBalanceException;
import ma.skypay.bankingservice.domain.Transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@AllArgsConstructor
public class AccountServiceImp implements AccountService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    @Override
    public void deposit(UUID id, int amount, String date) {
        if (amount <= 0) throw new IllegalAmountException("Le montant doit être supérieur à zéro");
        final Account account = getAccount(id);
        this.transactionRepository.save(Transaction.builder()
                .account(account)
                .amount(amount)
                .date(LocalDate.parse(date))
                .build());
    }

    @Override
    public void withdraw(UUID id, int amount, String date) {
        if (amount <= 0) throw new IllegalAmountException("Le montant doit être supérieur à zéro");
        final Account account = getAccount(id);
        final int sum = account.getTransactions().stream()
                .mapToInt(Transaction::getAmount)
                .sum();
        if (sum < amount) throw new InsufficientBalanceException("Le solde est insuffisant pour ce retrait");
        this.transactionRepository.save(Transaction.builder()
                        .account(account)
                        .date(LocalDate.parse(date))
                        .amount(-amount)
                .build());
    }

    @Override
    public void printStatement(UUID id) {
        final Account account = getAccount(id);
        AtomicInteger balance = new AtomicInteger(0);
        log.info(String.format("%-12s || %-10s || %-10s", "DATE", "AMOUNT", "BALANCE"));
        Collections.reverse(account.getTransactions());
        account.getTransactions().forEach(
                transaction -> {
                    int nvBalance = balance.addAndGet(transaction.getAmount());
                    log.info(String.format(
                            "%-12s || %-10d || %-10d",
                            transaction.getDate(),
                            transaction.getAmount(),
                            nvBalance
                    ));
                }
        );
    }

    @Override
    public List<AccountResponseDto> getAllAccounts() {
        final List<Account> accounts = this.accountRepository.findAll();
        return accounts.stream()
                .map(AccountMapper::toDto)
                .toList();
    }

    @Override
    public Account getAccount(UUID id) {
        if (Objects.isNull(id)) throw new AccountIdNullException(" L’identifiant est manquant");
        return this.accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException("Ce compte n'existe pas " + id)
        );
    }

    @Override
    public AccountResponseDto createAccount(AccountRequestDto accountRequestDto) {
        if (this.accountRepository.existsByEmail(accountRequestDto.getEmail()))
            throw new EmailAlreadyExistingException("Cet e-mail est déjà utilisé par un autre utilisateur " + accountRequestDto.getEmail());
        return AccountMapper.toDto(
                this.accountRepository.save(AccountMapper.toEntity(accountRequestDto))
        );
    }

    @Override
    public AccountResponseDto updateAccount(UUID id, AccountRequestDto accountRequestDto) {
        final Account account = getAccount(id);
        if (!account.getEmail().equals(accountRequestDto.getEmail()) && this.accountRepository.existsByEmail(accountRequestDto.getEmail()))
            throw new EmailAlreadyExistingException("Cet e-mail est déjà utilisé par un autre utilisateur "
                    + accountRequestDto.getEmail());
        account.setFirstName(accountRequestDto.getFirstName());
        account.setEmail(accountRequestDto.getEmail());
        account.setRegisteredDate(LocalDate.parse(accountRequestDto.getRegisteredDate()));
        account.setDateOfBirth(LocalDate.parse(accountRequestDto.getDateOfBirth()));
        account.setAddress(accountRequestDto.getAddress());
        account.setLastName(accountRequestDto.getLastName());
        return AccountMapper.toDto(this.accountRepository.save(account));
    }

    @Override
    public void deleteAccount(UUID id) {
        final Account account = getAccount(id);
        this.accountRepository.deleteById(account.getId());
    }


}
