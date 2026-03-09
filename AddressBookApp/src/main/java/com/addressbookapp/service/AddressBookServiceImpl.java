package com.addressbookapp.service;
import com.addressbookapp.model.Contact;
import com.addressbookapp.repository.AddressBookRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl implements AddressBookService {

	private final AddressBookRepository repository;

	public AddressBookServiceImpl(AddressBookRepository repository) {
		this.repository = repository;
	}

	@Override
	public void addContact(Contact contact) {
		repository.save(contact);
		System.out.println("Contact Added Success");
	}

	@Override
	public Contact findContact(String name) {
		return repository.findByFirstName(name);
	}

	@Override
	public void editContact(String name, Contact updatedContact) {

		Contact existing = repository.findByFirstName(name);

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
	public void deleteContact(String name) {

		Contact contact = repository.findByFirstName(name);

		if (contact != null) {
			repository.delete(contact);
			System.out.println("Contact deleted successfully.");
		} else {
			System.out.println("Contact not found.");
		}
	}
	
	@Override
	public List<Contact> getAllContacts() {
	    return repository.findAll();
	}
}