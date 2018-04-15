package pik;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import pik.infrastructure.Profiles;


@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        //System.setProperty("spring.profiles.default", Profiles.TEST);
        ApplicationContext ctx = SpringApplication.run(Application.class, args);


        System.out.println("Let's inspect the beans provided by Spring Boot:");
        
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

        // Only testing
        // H2 In Memory DB is in development
    }

}
