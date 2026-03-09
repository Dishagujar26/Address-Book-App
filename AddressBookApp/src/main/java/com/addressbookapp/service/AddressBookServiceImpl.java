package com.addressbookapp.service;

import com.addressbookapp.manager.AddressBookManager;
import com.addressbookapp.model.AddressBook;
import com.addressbookapp.model.Contact;
import com.addressbookapp.repository.AddressBookRepository;
import org.springframework.stereotype.Service;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AddressBookServiceImpl implements AddressBookService {

	private final AddressBookRepository repository;
	private final AddressBookManager manager;

	public AddressBookServiceImpl(AddressBookRepository repository, AddressBookManager manager) {
		this.repository = repository;
		this.manager = manager;
	}

	@Override
	public void addContact(String bookName, Contact contact) {

		AddressBook book = manager.getAddressBook(bookName);

		if (book == null) {
			System.out.println("Address Book not found.");
			return;
		}

		boolean duplicate = book.getContacts().stream()
				.anyMatch(c -> c.getFirstName().equalsIgnoreCase(contact.getFirstName())
						&& c.getLastName().equalsIgnoreCase(contact.getLastName()));

		if (duplicate) {
			System.out.println("Duplicate contact found. Contact not added.");
			return;
		}

		repository.save(contact, book.getContacts());
		System.out.println("Contact added successfully.");
	}

	@Override
	public void editContact(String bookName, String name, Contact updatedContact) {

		AddressBook book = manager.getAddressBook(bookName);

		if (book == null) {
			System.out.println("Address Book not found.");
			return;
		}

		Contact existing = repository.findByFirstName(name, book.getContacts());

		if (existing != null) {

			existing.setAddress(updatedContact.getAddress());
			existing.setCity(updatedContact.getCity());
			existing.setState(updatedContact.getState());
			existing.setZip(updatedContact.getZip());
			existing.setPhoneNumber(updatedContact.getPhoneNumber());
			existing.setEmail(updatedContact.getEmail());

			System.out.println("Contact updated successfully.");
		} else {
			System.out.println("Contact not found.");
		}
	}

	@Override
	public void deleteContact(String bookName, String name) {

		AddressBook book = manager.getAddressBook(bookName);

		if (book == null) {
			System.out.println("Address Book not found.");
			return;
		}

		Contact contact = repository.findByFirstName(name, book.getContacts());

		if (contact != null) {
			repository.delete(contact, book.getContacts());
			System.out.println("Contact deleted successfully.");
		} else {
			System.out.println("Contact not found.");
		}
	}

	@Override
	public List<Contact> getAllContacts(String bookName) {

		AddressBook book = manager.getAddressBook(bookName);

		if (book == null) {
			System.out.println("Address Book not found.");
			return null;
		}

		return book.getContacts();
	}

	@Override
	public Contact findContact(String bookName, String name) {

		AddressBook book = manager.getAddressBook(bookName);

		if (book == null) {
			return null;
		}

		return repository.findByFirstName(name, book.getContacts());
	}

	@Override
	public List<Contact> searchByCity(String city) {

		return manager.getAllAddressBooks().values().stream().flatMap(book -> book.getContacts().stream())
				.filter(contact -> contact.getCity().equalsIgnoreCase(city)).toList();
	}

	@Override
	public List<Contact> searchByState(String state) {

		return manager.getAllAddressBooks().values().stream().flatMap(book -> book.getContacts().stream())
				.filter(contact -> contact.getState().equalsIgnoreCase(state)).toList();
	}

	@Override
	public Map<String, List<Contact>> getPersonsByCity() {

		return manager.getAllAddressBooks().values().stream().flatMap(book -> book.getContacts().stream())
				.collect(Collectors.groupingBy(Contact::getCity));
	}

	@Override
	public Map<String, List<Contact>> getPersonsByState() {

		return manager.getAllAddressBooks().values().stream().flatMap(book -> book.getContacts().stream())
				.collect(Collectors.groupingBy(Contact::getState));
	}

	@Override
	public Map<String, Long> getContactCount(Function<Contact, String> classifier) {

		return manager.getAllAddressBooks().values().stream().flatMap(book -> book.getContacts().stream())
				.collect(Collectors.groupingBy(classifier, Collectors.counting()));
	}

	@Override
	public List<Contact> sortContactsByName(String bookName) {

		AddressBook book = manager.getAddressBook(bookName);

		if (book == null) {
			System.out.println("Address Book not found.");
			return List.of();
		}

		return book.getContacts().stream()
				.sorted(Comparator.comparing(Contact::getFirstName).thenComparing(Contact::getLastName)).toList();
	}

	@Override
	public List<Contact> sortContacts(String bookName, Comparator<Contact> comparator) {

		AddressBook book = manager.getAddressBook(bookName);

		if (book == null) {
			System.out.println("Address Book not found.");
			return List.of();
		}

		return book.getContacts().stream().sorted(comparator).toList();
	}

	@Override
	public void saveAddressBookToFile(String bookName) {

		AddressBook book = manager.getAddressBook(bookName);

		if (book == null) {
			System.out.println("Address Book not found.");
			return;
		}

		try {

			List<String> lines = book.getContacts().stream()
					.map(c -> String.join(",", c.getFirstName(), c.getLastName(), c.getAddress(), c.getCity(),
							c.getState(), c.getZip(), c.getPhoneNumber(), c.getEmail()))
					.toList();

			Files.write(Path.of(bookName + ".txt"), lines);

			System.out.println("Address Book saved to file.");

		} catch (IOException e) {
			System.out.println("Error writing file.");
		}
	}

	@Override
	public void loadAddressBookFromFile(String bookName) {

		AddressBook book = manager.getAddressBook(bookName);

		if (book == null) {
			manager.addAddressBook(bookName);
			book = manager.getAddressBook(bookName);
		}

		try {

			book.getContacts().clear();

			List<Contact> contacts = Files.readAllLines(Path.of(bookName + ".txt")).stream()
					.map(line -> line.split(","))
					.map(data -> new Contact(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]))
					.toList();

			book.getContacts().addAll(contacts);

			System.out.println("Address Book loaded from file.");

		} catch (IOException e) {
			System.out.println("File not found.");
		}
	}

	@Override
	public void saveAddressBookToCSV(String bookName) {

		AddressBook book = manager.getAddressBook(bookName);

		if (book == null) {
			System.out.println("Address Book not found.");
			return;
		}

		try {

			FileWriter writer = new FileWriter(bookName + ".csv");

			StatefulBeanToCsv<Contact> beanToCsv = new StatefulBeanToCsvBuilder<Contact>(writer).build();

			beanToCsv.write(book.getContacts());

			writer.close();

			System.out.println("Address Book saved as CSV file.");

		} catch (Exception e) {
			System.out.println("Error writing CSV file.");
		}
	}

	@Override
	public void loadAddressBookFromCSV(String bookName) {

		AddressBook book = manager.getAddressBook(bookName);

		if (book == null) {
			manager.addAddressBook(bookName);
			book = manager.getAddressBook(bookName);
		}

		try {

			FileReader reader = new FileReader(bookName + ".csv");

			CsvToBean<Contact> csvToBean = new CsvToBeanBuilder<Contact>(reader).withType(Contact.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			List<Contact> contacts = csvToBean.parse();

			book.getContacts().clear();
			book.getContacts().addAll(contacts);

			System.out.println("Address Book loaded from CSV file.");

		} catch (Exception e) {
			System.out.println("CSV file not found.");
		}
	}

	@Override
	public void saveAddressBookToJSON(String bookName) {

		AddressBook book = manager.getAddressBook(bookName);

		if (book == null) {
			System.out.println("Address Book not found.");
			return;
		}

		try {

			Gson gson = new Gson();

			FileWriter writer = new FileWriter(bookName + ".json");

			gson.toJson(book.getContacts(), writer);

			writer.close();

			System.out.println("Address Book saved as JSON file.");

		} catch (Exception e) {
			System.out.println("Error writing JSON file.");
		}
	}

	@Override
	public void loadAddressBookFromJSON(String bookName) {

		AddressBook book = manager.getAddressBook(bookName);

		if (book == null) {
			manager.addAddressBook(bookName);
			book = manager.getAddressBook(bookName);
		}

		try {

			Gson gson = new Gson();

			FileReader reader = new FileReader(bookName + ".json");

			Type contactListType = new TypeToken<List<Contact>>() {
			}.getType();

			List<Contact> contacts = gson.fromJson(reader, contactListType);

			book.getContacts().clear();
			book.getContacts().addAll(contacts);

			System.out.println("Address Book loaded from JSON file.");

		} catch (Exception e) {
			System.out.println("JSON file not found.");
		}
	}
}

