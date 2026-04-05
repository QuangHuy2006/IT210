package ss1.b3;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InventoryRepositoryImpl implements InventoryRepository{
    private Map<String, Integer> stock = new HashMap<>();
    private Map<String, Double> price = new HashMap<>();

    public InventoryRepositoryImpl() {
        stock.put("Pizza", 10);
        stock.put("Burger", 20);
        stock.put("Sushi", 15);

        price.put("Pizza", 8.99);
        price.put("Burger", 5.99);
        price.put("Sushi", 12.99);
    }

    @Override
    public int getStock(String Foodname) {
        return stock.getOrDefault(Foodname, 0);
    }

    @Override
    public void updateStock(String Foodname, int quantity) {
         stock.put(Foodname, quantity);
    }
}
