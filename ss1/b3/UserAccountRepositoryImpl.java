package ss1.b3;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserAccountRepositoryImpl implements  UserAccountRepository {
    private Map<String, Double> accounts = new HashMap<>();
    public UserAccountRepositoryImpl() {
        accounts.put("thanhVIP", 50000.0);
        accounts.put("hoa69", 10000.0);
        accounts.put("phuocMatChim", 1000000.0);
    }

    @Override
    public double getBalance(String username) {
        return accounts.getOrDefault(username, 0.0);
    }

    @Override
    public void updateBalance(String username, double amount) {
        accounts.put(username, amount);
    }
}
