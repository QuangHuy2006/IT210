package org.example.ss1.b5.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemConfig {
    @Value("Chi nhánh Phe The")
    private String branchName;

    @Value("18:00 - 36:00")
    private String openingHour;

    public void printInfo() {
        System.out.println("Branch: " + branchName);
        System.out.println("Opening hour: " + openingHour);
    }
}
