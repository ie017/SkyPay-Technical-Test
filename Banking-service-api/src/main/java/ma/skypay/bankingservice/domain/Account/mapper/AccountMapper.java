package ma.skypay.bankingservice.domain.Account.mapper;

import ma.skypay.bankingservice.domain.Account.dto.AccountRequestDto;
import ma.skypay.bankingservice.domain.Account.dto.AccountResponseDto;
import ma.skypay.bankingservice.domain.Account.entity.Account;

import java.time.LocalDate;

public class AccountMapper {

    public static AccountResponseDto toDto(Account account) {
        return new AccountResponseDto(account.getId().toString(),
                account.getEmail(),
                account.getFirstName(),
                account.getLastName(),
                account.getAddress(),
                account.getDateOfBirth().toString(),
                account.getRegisteredDate().toString());
    }

    public static Account toEntity(AccountRequestDto accountRequestDto) {
        Account account = new Account();
        account.setAddress(accountRequestDto.getAddress());
        account.setEmail(accountRequestDto.getEmail());
        account.setLastName(accountRequestDto.getLastName());
        account.setFirstName(accountRequestDto.getFirstName());
        account.setDateOfBirth(LocalDate.parse(accountRequestDto.getDateOfBirth()));
        account.setRegisteredDate(LocalDate.parse(accountRequestDto.getRegisteredDate()));
        return account;
    }
}
