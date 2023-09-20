package com.qsspy.bandmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
		scanBasePackages = {
				"com.qsspy.authservice",
				"com.qsspy.bands",
				"com.qsspy.finances"
		}
)
@EnableJpaRepositories(
		basePackages = {
				"com.qsspy.authservice",
				"com.qsspy.bands",
				"com.qsspy.finances"
		}
)
@EntityScan(
		basePackages = {"com.qsspy.jpadatamodel"}
)
public class BandManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BandManagerApplication.class, args);
	}

}
