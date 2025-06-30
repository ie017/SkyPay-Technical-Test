package ma.skypay.domain.user.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private int balance;

    public void deductBalance(int amount) { this.balance -= amount; }
    @Override
    public String toString() {
        return "[User] " + id + " [balance=" + balance + "]";
    }
}
