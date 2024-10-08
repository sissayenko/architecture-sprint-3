package kz.yandex_practicum.sprint3.device_management_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication()

public class DeviceManagementMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeviceManagementMsApplication.class, args);
	}

}
