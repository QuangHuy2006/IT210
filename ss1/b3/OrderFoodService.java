package ss1.b3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderFoodService {
    private UserAccountRepository userAccountRepository;
    private InventoryRepository inventoryRepository;

    @Autowired
    public OrderFoodService(UserAccountRepository userAccountRepository, InventoryRepository inventoryRepository) {
        this.userAccountRepository = userAccountRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public void orderFood(String username, String foodname, int quantity, double price){
        if(username == null || foodname == null || quantity <= 0 ){
            System.out.println("Invalid input");
            return;
        }
        int stock = inventoryRepository.getStock(foodname);
        if(stock < quantity){
            System.out.println("Not enough stock");
            return;
        }
        double totalCost = quantity * price;
        double balance = userAccountRepository.getBalance(username);
        if( balance < totalCost){
            System.out.println("Not enough balance");
            return;
        }
        //transactioning
        userAccountRepository.updateBalance(username, balance - totalCost);
        inventoryRepository.updateStock(foodname, stock - quantity);
        System.out.println("Order successful");
    }





}
