package kz.yandex_practicum.sprint3.telemetry_harvester_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication()
@EnableScheduling

//@EnableJpaRepositories
//@EnableTransactionManagement
public class TelemetryHarvesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelemetryHarvesterApplication.class, args);
	}

}
