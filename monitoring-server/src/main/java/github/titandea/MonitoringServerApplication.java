package github.titandea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Стартер всего приложения.
 */
@SpringBootApplication
@EnableScheduling
public class MonitoringServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitoringServerApplication.class, args);
    }
}