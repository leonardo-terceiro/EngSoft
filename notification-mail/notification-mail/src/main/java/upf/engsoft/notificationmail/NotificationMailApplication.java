package upf.engsoft.notificationmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NotificationMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationMailApplication.class, args);
	}

}
