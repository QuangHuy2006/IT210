package atmin.btth.service;


import atmin.btth.model.Account;
import atmin.btth.noti.Impl.NormalNotification;
import atmin.btth.noti.Impl.VipNotificatiion;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountManagement {

    private final VipNotificatiion vipNotificatiion;
    private final NormalNotification normalNotification;

    public AccountManagement(VipNotificatiion vipNotificatiion,
                             NormalNotification normalNotification) {
        this.vipNotificatiion = vipNotificatiion;
        this.normalNotification = normalNotification;
    }

    private final ArrayList<Account> accounts = new ArrayList<>(List.of(
            new Account("Atmin", 4000.0, "Normal"),
            new Account("Mynato", 3500.0, "Vip")
    ));

    public void checkBalance(double balance, String username) {

        if (username == null || username.isEmpty()) {
            System.out.println("Username is not exist");
            return;
        }

        boolean isExist = accounts.stream()
                .anyMatch(account -> account.getUsername().equals(username));

        if (!isExist) {
            System.out.println("[ERROR] Username is not exist");
            return;
        }

        Account acc = accounts.stream()
                .filter(account -> account.getUsername().equals(username))
                .findFirst()
                .orElse(null);

        if (acc != null) {

            if (acc.getBalance() < 0) {
                System.out.println("[ERROR] Balance is negative");
                return;
            }

            if (acc.getBalance() < 5000) {

                if ("Vip".equalsIgnoreCase(acc.getAreaType())) {
                    vipNotificatiion.noti(acc.getBalance(), acc.getUsername());
                } else {
                    normalNotification.noti(acc.getBalance(), acc.getUsername());
                }

            }
        }
    }
}