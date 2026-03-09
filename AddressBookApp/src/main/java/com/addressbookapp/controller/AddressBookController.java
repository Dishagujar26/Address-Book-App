package com.addressbookapp.controller;

import com.addressbookapp.manager.AddressBookManager;
import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBookService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class AddressBookController {

	private final AddressBookService service;
	private final AddressBookManager manager;

	public AddressBookController(AddressBookService service, AddressBookManager manager) {
		this.service = service;
		this.manager = manager;
	}

	public void start() {

		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {

			System.out.println("\n1 Add Address Book");
			System.out.println("2 Add Contact");
			System.out.println("3 Edit Contact");
			System.out.println("4 Delete Contact");
			System.out.println("5 View Contacts");
			System.out.println("6 Exit");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {

			case 1:

				System.out.println("Enter Address Book Name:");
				String bookName = scanner.nextLine();
				manager.addAddressBook(bookName);

				break;

			case 2:

				System.out.println("Enter Address Book Name:");
				String addBook = scanner.nextLine();

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

				service.addContact(addBook, contact);

				break;

			case 3:

				System.out.println("Enter Address Book Name:");
				String editBook = scanner.nextLine();

				System.out.println("Enter First Name of contact:");
				String name = scanner.nextLine();

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

				service.editContact(editBook, name, updated);

				break;

			case 4:

				System.out.println("Enter Address Book Name:");
				String deleteBook = scanner.nextLine();

				System.out.println("Enter First Name of contact:");
				String deleteName = scanner.nextLine();

				service.deleteContact(deleteBook, deleteName);

				break;

			case 5:

				System.out.println("Enter Address Book Name:");
				String viewBook = scanner.nextLine();

				List<Contact> contacts = service.getAllContacts(viewBook);

				if (contacts == null || contacts.isEmpty()) {
					System.out.println("No contacts found.");
				} else {
					contacts.forEach(System.out::println);
				}

				break;

			case 6:

				running = false;
				System.out.println("Exiting Address Book...");
				break;
			}
		}
	}
}