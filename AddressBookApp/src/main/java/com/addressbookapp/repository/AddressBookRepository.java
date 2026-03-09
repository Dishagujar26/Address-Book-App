package com.addressbookapp.repository;

import com.addressbookapp.model.Contact;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressBookRepository {

	public void save(Contact contact, List<Contact> contacts) {
		contacts.add(contact);
	}

	public Contact findByFirstName(String name, List<Contact> contacts) {

		for (Contact contact : contacts) {
			if (contact.getFirstName().equalsIgnoreCase(name)) {
				return contact;
			}
		}

		return null;
	}

	public void delete(Contact contact, List<Contact> contacts) {
		contacts.remove(contact);
	}
}