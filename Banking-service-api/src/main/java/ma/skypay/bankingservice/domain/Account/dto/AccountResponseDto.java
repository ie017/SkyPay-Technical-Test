package ma.skypay.bankingservice.domain.Account.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountResponseDto {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String dateOfBirth;
    private String registeredDate;
}
