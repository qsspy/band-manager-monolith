package com.qsspy.bandmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
		scanBasePackages = {
				"com.qsspy.authservice.*",
				"com.qsspy.bands.*"
		}
)
@EnableJpaRepositories(
		basePackages = {
				"com.qsspy.authservice.*",
				"com.qsspy.bands.*"
		}
)
@EntityScan(
		basePackages = {
				"com.qsspy.authservice.*",
				"com.qsspy.bands.*"
		}
)
public class BandManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BandManagerApplication.class, args);
	}

}
