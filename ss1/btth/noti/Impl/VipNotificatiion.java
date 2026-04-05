package atmin.btth.noti.Impl;

import atmin.btth.noti.Notification;
import org.springframework.stereotype.Component;

@Component
public class VipNotificatiion implements Notification {
    @Override
    public void noti(double balance, String username) {
        System.out.printf("[ Pop up ] Acc %s so du con lai %.1f", username, balance);
    }
}
