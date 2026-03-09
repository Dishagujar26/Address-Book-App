package com.addressbookapp.service;

import com.addressbookapp.manager.AddressBookManager;
import com.addressbookapp.model.AddressBook;
import com.addressbookapp.model.Contact;
import com.addressbookapp.repository.AddressBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}