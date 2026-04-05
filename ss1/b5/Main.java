package atmin.b5;

import atmin.b5.config.AppConfig;
import atmin.b5.model.SystemConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    static void main() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        SystemConfig config = context.getBean(SystemConfig.class);
        config.printInfo();
    }
}