package atmin.b5.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemConfig {
    //    SystemConfig config = new SystemConfig();
//    config.branchName = "Chi nhánh Hà Nội";
    @Value("Chi nhánh Hà Nội")
    private String branchName;

    @Value("8:00 - 22:00")
    private String openingHour;

    public void printInfo() {
        System.out.println("Branch: " + branchName);
        System.out.println("Opening hour: " + openingHour);
    }
}
