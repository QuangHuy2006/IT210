package ss1.b4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final MessageSender emailSender;
    private final MessageSender smsSender;

    @Autowired
    public NotificationService(@Qualifier("emailSender")MessageSender emailSender,@Qualifier("smsSender")MessageSender smsSender) {
        this.emailSender = emailSender;
        this.smsSender = smsSender;
    }

    public void notifyPayment(double amount){
        String msg = "Payment notification: " + amount;
        emailSender.send(msg);
        try {
            smsSender.send(msg);
        } catch (RuntimeException e) {
            System.out.println("ERROR");
        }
    }
}
