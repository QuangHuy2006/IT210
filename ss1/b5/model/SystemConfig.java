package ss1.b5.model;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemConfig {
    @Value("Tuan tu 24h")
    private String branchName;
    @Value("69 Ha Dong")
    private String address;

    public void displayinfo(){
        System.out.println("Branch name: " + branchName);
        System.out.println("Address: " + address);
    }
}
