package kz.yandex_practicum.sprint3.profile_management_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication()
//@ComponentScan({"kz.yandex_practicum.sprint3.profile_management_ms"})
public class ProfileManagementMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfileManagementMsApplication.class, args);
	}

}
