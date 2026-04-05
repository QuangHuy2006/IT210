package atmin.btth;

import atmin.btth.config.AppConfig;
import atmin.btth.service.AccountManagement;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        // tạo Spring container
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // lấy bean đã được Spring tạo
        AccountManagement accountManagement = context.getBean(AccountManagement.class);

        // chạy thử
        accountManagement.checkBalance(4000, "Atmin");
        accountManagement.checkBalance(3500, "Mynato");
        accountManagement.checkBalance(10000, "Atmin");
        accountManagement.checkBalance(2000, "Unknown");
        accountManagement.checkBalance(-100, "Mynato");
    }
}