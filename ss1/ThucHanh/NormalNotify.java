package org.example.ThucHanh;

public class NormalNotify implements Notification {
    @Override
    public void sendNotify(String username, double balance){
        System.out.println("[Popup] Tài khoản: " + username + "số dư: " + balance);
    }

}
