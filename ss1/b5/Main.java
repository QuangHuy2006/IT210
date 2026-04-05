package ss1.b5;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ss1.b5.config.AppConfig;
import ss1.b5.model.SystemConfig;




public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SystemConfig config = context.getBean(SystemConfig.class);
        config.displayinfo();
        context.registerShutdownHook();
    }
}
