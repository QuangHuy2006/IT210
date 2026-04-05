package ss1.b4;

import org.springframework.stereotype.Component;

@Component
public class SmsSender implements MessageSender{
    @Override
    public void send(String msg) {
        // Gia lap mat mang
        if (Math.random() > 0.7) {
            throw new RuntimeException("Connection Error");
        }
        System.out.println("Sending SMS: " + msg);
    }
}
