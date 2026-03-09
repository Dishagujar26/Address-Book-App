package com.addressbookapp.repository;
import com.addressbookapp.model.Contact;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressBookRepository {

	private final List<Contact> contacts = new ArrayList<>();

	public void save(Contact contact) {
		contacts.add(contact);
	}

	public List<Contact> findAll() {
		return contacts;
	}

	public Contact findByFirstName(String name) {

		for (Contact contact : contacts) {
			if (contact.getFirstName().equalsIgnoreCase(name)) {
				return contact;
			}
		}

		return null;
	}
}