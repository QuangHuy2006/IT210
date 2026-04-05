package atmin.btth.noti.Impl;

import atmin.btth.noti.Notification;
import org.springframework.stereotype.Component;

@Component
public class NormalNotification implements Notification {
    @Override
    public void noti(double balance, String username) {
        System.out.printf("[ Sound ] Acc %s so du con lai %.1f", username, balance);
    }
}
