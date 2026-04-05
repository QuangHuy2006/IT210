package atmin.btth.model;

import org.springframework.stereotype.Component;

@Component
public class Account {
    String username;
    Double balance;
    String areaType;

    public Account() {
    }

    public Account(String username,Double balance,String areaType) {
        this.username = username;
        this.balance = balance;
        this.areaType = areaType;
    }

    public String getAreaType() {
        return areaType;
    }

    public Double getBalance() {
        return balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }
}
