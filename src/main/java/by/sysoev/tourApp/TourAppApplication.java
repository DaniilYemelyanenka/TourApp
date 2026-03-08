package by.sysoev.tourApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TourAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourAppApplication.class, args);
	}

}
