package ma.skypay.bankingservice.domain.Account.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ma.skypay.bankingservice.domain.Transaction.entity.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID")
    private UUID id;

    @Column(unique = true, name = "EMAIL")
    @NotNull
    @Email
    private String email;

    @Column(name = "FIRST_NAME")
    @NotNull
    private String firstName;

    @Column(name = "LAST_NAME")
    @NotNull
    private String lastName;

    @Column(name = "ADDRESS")
    @NotNull
    private String address;

    @Column(name = "DATE_OF_BIRTH")
    @NotNull
    private LocalDate dateOfBirth;

    @Column(name = "REGISTERED_DATE")
    @NotNull
    private LocalDate registeredDate;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

}
