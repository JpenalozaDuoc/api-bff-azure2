package bffapi.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BffApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffApplication.class, args);
	}

	// Configuración de RestTemplate como un Bean
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
