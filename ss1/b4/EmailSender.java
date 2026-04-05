package ss1.b4;
import org.springframework.stereotype.Component;

@Component("emailSender")
public class EmailSender implements MessageSender {
    @Override
    public void send(String msg) {
        System.out.println("Sending email: " + msg);
    }
}
