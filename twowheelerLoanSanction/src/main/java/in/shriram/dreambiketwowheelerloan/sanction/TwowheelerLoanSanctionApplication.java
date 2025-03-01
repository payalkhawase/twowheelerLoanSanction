package in.shriram.dreambiketwowheelerloan.sanction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
=======
>>>>>>> branch 'main' of https://github.com/payalkhawase/twowheelerLoanSanction.git
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class TwowheelerLoanSanctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwowheelerLoanSanctionApplication.class, args);
	}
	
	@Bean
	public RestTemplate rt()
	{
		return new RestTemplate();
	}

	@Bean
	public RestTemplate rt()
	{
		return new RestTemplate();
	}
}
