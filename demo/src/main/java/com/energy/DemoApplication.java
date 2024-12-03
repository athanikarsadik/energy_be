package com.energy;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.energy.model.EnergyConsumption;
import com.energy.model.User;
import com.energy.repository.EnergyConsumptionRepository;
import com.energy.repository.UserRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository,
			EnergyConsumptionRepository energyConsumptionRepository) {
		return args -> {
			LocalDateTime now = LocalDateTime.now();

			User user1 = userRepository.save(new User(null, "user1", "user1@example.com"));
			User user2 = userRepository.save(new User(null, "user2", "user2@example.com"));

			energyConsumptionRepository
					.save(new EnergyConsumption(null, user1, 120.0, 60.0, 50.0, 130.0, now.minusDays(5)));
			energyConsumptionRepository
					.save(new EnergyConsumption(null, user1, 150.0, 75.0, 60.0, 160.0, now.minusDays(2)));

			energyConsumptionRepository
					.save(new EnergyConsumption(null, user2, 120.0, 60.0, 50.0, 130.0, now.minusDays(1)));
			energyConsumptionRepository
					.save(new EnergyConsumption(null, user2, 150.0, 75.0, 60.0, 160.0, now));

		};
	}

}
