package org.example.ThucHanh;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AccountManagement {
    List<Account> list = new ArrayList<>(Arrays.asList(
            new Account("Nguyễn Đăng Quang", 100000),
            new Account("Tống Trần Huy Hoàng", 120000),
            new Account("Phê thế", 1200000)
    ));

    public void checkYourBalance(String username, String area){
        if(username == null || username.isEmpty()){
            System.out.println("[ERROR] Ten khong hop le");
            return;
        }

        boolean isExist = list.stream().anyMatch(account -> account.getUsername().equalsIgnoreCase(username));
        if(!isExist){
            System.out.println("[ERROR] User not found");
            return;
        }

        Account acc = list.stream().filter(account -> account.getUsername().equalsIgnoreCase(username)).findFirst().orElse(null);

        if(acc != null){
            if(acc.getBalance() < 0){
                System.err.println("[ERROR] balance not enough");
                return;
            }

            ApplicationContext context = new AnnotationConfigApplicationContext();
            if(acc.getBalance() < 5000){
                if(area.equalsIgnoreCase("VIP")){
                    VIPNotify vipnotify = context.getBean("VIPNotify", VIPNotify.class);
                    vipnotify.sendNotify(username, account.getBalance());
                }else{
                    NormalNotify normalNotify = context.getBean("NormalNotify", NormalNotify.class);
                    normalNotify.sendNotify(username, account.getBalance());
                }
            }
        }
    }
}
