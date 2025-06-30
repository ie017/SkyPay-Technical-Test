package ma.skypay.bankingservice.domain.Transaction.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.skypay.bankingservice.domain.Account.entity.Account;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "CREATION_DATE")
    private LocalDate date;

    @NotNull
    @Column(name = "AMOUNT")
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;


}
