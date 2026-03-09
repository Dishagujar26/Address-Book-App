package com.addressbookapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.addressbookapp.model.Contact;

@SpringBootApplication
public class AddressBookAppApplication {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(AddressBookAppApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);

		ConfigurableApplicationContext context = app.run(args);

		System.out.println("Welcome to Address Book");
		
		Contact contact = new Contact("Steve", "Gensi", "Hawkins", "Roane", "Indiana", "543216","1218765400", "steve@email.com");

		System.out.println(contact);
	}
}
