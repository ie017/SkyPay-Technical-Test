package ma.skypay.bankingservice.domain.Account.service;

import ma.skypay.bankingservice.domain.Account.dto.AccountRequestDto;
import ma.skypay.bankingservice.domain.Account.dto.AccountResponseDto;
import ma.skypay.bankingservice.domain.Account.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    void deposit(UUID id, int amount, String date);
    void withdraw(UUID id, int amount, String date);
    void printStatement(UUID id);
    List<AccountResponseDto> getAllAccounts();
    Account getAccount(UUID id);
    AccountResponseDto createAccount(AccountRequestDto accountRequestDto);
    AccountResponseDto updateAccount(UUID id, AccountRequestDto accountRequestDto);
    void deleteAccount(UUID id);
}
