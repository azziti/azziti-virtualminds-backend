package com.virtualminds.test.backend;

import com.virtualminds.test.backend.entities.Role;
import com.virtualminds.test.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Bean
	public BCryptPasswordEncoder passWordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		if(userService.findRoleByRoleName("USER") == null) {
			userService.saveRole(new Role(null,"USER"));
			System.out.println("Role not found");
		}
//		System.out.println("role created with success!");
//		System.out.println(new BigDecimal(-100));
	}
}
