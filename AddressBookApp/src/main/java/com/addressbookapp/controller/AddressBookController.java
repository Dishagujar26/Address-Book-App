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
		boolean running = true;

		while (running) {

			System.out.println("\nAddress Book Menu");
			System.out.println("1. Add Contact");
			System.out.println("2. Edit Contact");
			System.out.println("3. Delete Contact");
			System.out.println("4. Exit");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {

			case 1:
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
				break;

			case 2:

				System.out.println("Enter first name of contact to edit:");
				String editName = scanner.nextLine();

				Contact existing = service.findContact(editName);

				if (existing == null) {
					System.out.println("Contact not found.");
					break;
				}

				Contact updated = new Contact();

				System.out.println("New Address:");
				updated.setAddress(scanner.nextLine());

				System.out.println("New City:");
				updated.setCity(scanner.nextLine());

				System.out.println("New State:");
				updated.setState(scanner.nextLine());

				System.out.println("New Zip:");
				updated.setZip(scanner.nextLine());

				System.out.println("New Phone:");
				updated.setPhoneNumber(scanner.nextLine());

				System.out.println("New Email:");
				updated.setEmail(scanner.nextLine());

				service.editContact(editName, updated);
				break;

			case 3:

				System.out.println("Enter first name of contact to delete:");
				String deleteName = scanner.nextLine();

				service.deleteContact(deleteName);
				break;

			case 4:
				running = false;
				System.out.println("Exiting Address Book...");
				break;

			default:
				System.out.println("Invalid option");
			}
		}
	}
}