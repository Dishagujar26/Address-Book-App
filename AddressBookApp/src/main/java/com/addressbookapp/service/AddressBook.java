package com.addressbookapp.service;
import com.addressbookapp.model.Contact;
import java.util.ArrayList;

public class AddressBook {

	private ArrayList<Contact> contacts = new ArrayList<>();

	public void addContact(Contact person) {
		contacts.add(person);
		System.out.println("Contact added successfully.");
	}

	public void displayContacts() {

		if (contacts.isEmpty()) {
			System.out.println("No contacts available.");
			return;
		}

		for (Contact person : contacts) {
			System.out.println(person);
		}
	}
}