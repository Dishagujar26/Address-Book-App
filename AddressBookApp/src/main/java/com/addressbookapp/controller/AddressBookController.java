package com.addressbookapp.controller;

import com.addressbookapp.manager.AddressBookManager;
import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBookService;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class AddressBookController {

	private final AddressBookService service;
	private final AddressBookManager manager;

	private final Scanner scanner = new Scanner(System.in);

	public AddressBookController(AddressBookService service, AddressBookManager manager) {
		this.service = service;
		this.manager = manager;
	}

	public void start() {

		boolean running = true;

		while (running) {

			printMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {

			case 1 -> addAddressBook();
			case 2 -> addContact();
			case 3 -> editContact();
			case 4 -> deleteContact();
			case 5 -> viewContacts();
			case 6 -> searchByCity();
			case 7 -> searchByState();
			case 8 -> viewPersonsByCity();
			case 9 -> viewPersonsByState();
			case 10 -> countByCity();
			case 11 -> countByState();
			case 12 -> sortContacts();
			case 13 -> sortByCity();
			case 14 -> sortByState();
			case 15 -> sortByZip();
			case 16 -> saveToFile();
			case 17 -> loadFromFile();
			case 18 -> running = false;

			}
		}
	}

	private void printMenu() {

		System.out.println("\n1 Add Address Book");
		System.out.println("2 Add Contact");
		System.out.println("3 Edit Contact");
		System.out.println("4 Delete Contact");
		System.out.println("5 View Contacts");
		System.out.println("6 Search by City");
		System.out.println("7 Search by State");
		System.out.println("8 View Persons by City");
		System.out.println("9 View Persons by State");
		System.out.println("10 Count Contacts by City");
		System.out.println("11 Count Contacts by State");
		System.out.println("12 Sort Contacts by Name");
		System.out.println("13 Sort by City");
		System.out.println("14 Sort by State");
		System.out.println("15 Sort by Zip");
		System.out.println("16 Save Address Book to File");
		System.out.println("17 Load Address Book from File");
		System.out.println("18 Exit");
	}

	private void addAddressBook() {

		System.out.println("Enter Address Book Name:");
		String name = scanner.nextLine();

		manager.addAddressBook(name);
	}

	private void addContact() {

		System.out.println("Enter Address Book Name:");
		String book = scanner.nextLine();

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

		service.addContact(book, contact);
	}

	private void editContact() {

		System.out.println("Enter Address Book Name:");
		String book = scanner.nextLine();

		System.out.println("Enter First Name:");
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

		service.editContact(book, name, updated);
	}

	private void deleteContact() {

		System.out.println("Enter Address Book Name:");
		String book = scanner.nextLine();

		System.out.println("Enter First Name:");
		String name = scanner.nextLine();

		service.deleteContact(book, name);
	}

	private void viewContacts() {

		System.out.println("Enter Address Book Name:");
		String book = scanner.nextLine();

		List<Contact> contacts = service.getAllContacts(book);

		if (contacts == null || contacts.isEmpty()) {
			System.out.println("No contacts found.");
			return;
		}

		contacts.forEach(System.out::println);
	}

	private void searchByCity() {

		System.out.println("Enter City:");
		String city = scanner.nextLine();

		List<Contact> results = service.searchByCity(city);

		if (results.isEmpty()) {
			System.out.println("No contacts found.");
			return;
		}

		results.forEach(System.out::println);
	}

	private void searchByState() {

		System.out.println("Enter State:");
		String state = scanner.nextLine();

		List<Contact> results = service.searchByState(state);

		if (results.isEmpty()) {
			System.out.println("No contacts found.");
			return;
		}

		results.forEach(System.out::println);
	}

	private void viewPersonsByCity() {

		Map<String, List<Contact>> cityMap = service.getPersonsByCity();

		cityMap.forEach((city, persons) -> {
			System.out.println("\nCity: " + city);
			persons.forEach(System.out::println);
		});
	}

	private void viewPersonsByState() {

		Map<String, List<Contact>> stateMap = service.getPersonsByState();

		stateMap.forEach((state, persons) -> {
			System.out.println("\nState: " + state);
			persons.forEach(System.out::println);
		});
	}

	private void countByCity() {

		Map<String, Long> result = service.getContactCount(Contact::getCity);

		result.forEach((city, count) -> System.out.println(city + " : " + count));
	}

	private void countByState() {

		Map<String, Long> result = service.getContactCount(Contact::getState);

		result.forEach((state, count) -> System.out.println(state + " : " + count));
	}

	private void sortContacts() {

		System.out.println("Enter Address Book Name:");
		String book = scanner.nextLine();

		List<Contact> contacts = service.sortContactsByName(book);

		if (contacts.isEmpty()) {
			System.out.println("No contacts found.");
			return;
		}

		contacts.forEach(System.out::println);
	}

	private void sortByCity() {

		System.out.println("Enter Address Book Name:");
		String book = scanner.nextLine();

		List<Contact> contacts = service.sortContacts(book, Comparator.comparing(Contact::getCity));

		contacts.forEach(System.out::println);
	}

	private void sortByState() {

		System.out.println("Enter Address Book Name:");
		String book = scanner.nextLine();

		List<Contact> contacts = service.sortContacts(book, Comparator.comparing(Contact::getState));

		contacts.forEach(System.out::println);
	}

	private void sortByZip() {

		System.out.println("Enter Address Book Name:");
		String book = scanner.nextLine();

		List<Contact> contacts = service.sortContacts(book, Comparator.comparing(Contact::getZip));

		contacts.forEach(System.out::println);
	}

	private void saveToFile() {

		System.out.println("Enter Address Book Name:");
		String book = scanner.nextLine();

		service.saveAddressBookToFile(book);
	}

	private void loadFromFile() {

		System.out.println("Enter Address Book Name:");
		String book = scanner.nextLine();

		service.loadAddressBookFromFile(book);
	}

}