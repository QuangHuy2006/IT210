package ss1.b3;

public interface UserAccountRepository {
    double getBalance(String username);
    void updateBalance(String username, double amount);
}
