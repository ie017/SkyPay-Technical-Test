package ma.skypay.bankingservice.domain.Account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.skypay.bankingservice.domain.Account.dto.Validators.CreateAccountValidationGroup;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountRequestDto {

    @NotBlank(message = "L'adresse e-mail est obligatoire")
    @Email(message = "L'adresse e-mail n'est pas valide")
    private String email;

    @NotBlank(message = "Le prenom est obligatoire")
    private String firstName;

    @NotBlank(message = "Le nom est obligatoire")
    private String lastName;

    @NotBlank(message = "L'adresse est obligatoire")
    private String address;

    @NotBlank(message = "La date de naissance est obligatoire")
    private String dateOfBirth;

    @NotBlank(groups = CreateAccountValidationGroup.class, message = "La date de creation est obligatoire")
    private String registeredDate;

}
