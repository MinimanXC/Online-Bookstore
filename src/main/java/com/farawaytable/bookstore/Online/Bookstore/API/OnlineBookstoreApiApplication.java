package com.farawaytable.bookstore.Online.Bookstore.API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// TODO:
// - Database Connection (Somehow it's not working)
// - Error Handling
// - Search Functionality
// - Review Functionality
// - Add & Retrieve Reviews Functionality
// - Testing
// - Documentation(?)

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class OnlineBookstoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBookstoreApiApplication.class, args);
	}

}
