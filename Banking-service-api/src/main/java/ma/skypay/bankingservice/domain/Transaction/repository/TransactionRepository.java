package ma.skypay.bankingservice.domain.Transaction.repository;

import ma.skypay.bankingservice.domain.Transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

}
