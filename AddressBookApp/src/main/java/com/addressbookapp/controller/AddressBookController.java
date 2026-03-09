package com.addressbookapp.controller;
import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBookService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddressBookController {

	private final AddressBookService service;

	public AddressBookController(AddressBookService service) {
		this.service = service;
	}

	public void start() {

		Scanner scanner = new Scanner(System.in);

		System.out.println("1. Add Contact");
		System.out.println("2. Edit Contact");

		int choice = scanner.nextInt();
		scanner.nextLine();

		if (choice == 1) {

			Contact contact = new Contact();

			System.out.println("First Name:");
			contact.setFirstName(scanner.nextLine());

			System.out.println("Last Name:");
			contact.setLastName(scanner.nextLine());

			System.out.println("Address:");
			contact.setAddress(scanner.nextLine());

			System.out.println("City:");
			contact.setCity(scanner.nextLine());

			System.out.println("State:");
			contact.setState(scanner.nextLine());

			System.out.println("Zip:");
			contact.setZip(scanner.nextLine());

			System.out.println("Phone:");
			contact.setPhoneNumber(scanner.nextLine());

			System.out.println("Email:");
			contact.setEmail(scanner.nextLine());

			service.addContact(contact);
		}

		if (choice == 2) {

			System.out.println("Enter first name of contact to edit:");
			String name = scanner.nextLine();

			Contact existing = service.findContact(name);

			if (existing == null) {
				System.out.println("Contact not found.");
				return;
			}

			System.out.println("Enter new Address:");
			String address = scanner.nextLine();

			System.out.println("Enter new City:");
			String city = scanner.nextLine();

			System.out.println("Enter new State:");
			String state = scanner.nextLine();

			System.out.println("Enter new Zip:");
			String zip = scanner.nextLine();

			System.out.println("Enter new Phone:");
			String phone = scanner.nextLine();

			System.out.println("Enter new Email:");
			String email = scanner.nextLine();

			Contact updated = new Contact();
			updated.setAddress(address);
			updated.setCity(city);
			updated.setState(state);
			updated.setZip(zip);
			updated.setPhoneNumber(phone);
			updated.setEmail(email);

			service.editContact(name, updated);
		}
	}
}