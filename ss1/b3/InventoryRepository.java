package ss1.b3;

public interface InventoryRepository {
    int getStock(String Foodname);
    void updateStock(String Foodname, int quantity);
}




