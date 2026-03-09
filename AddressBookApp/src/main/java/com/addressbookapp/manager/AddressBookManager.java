package com.addressbookapp.manager;

import com.addressbookapp.model.AddressBook;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AddressBookManager {

	private Map<String, AddressBook> addressBooks = new HashMap<>();

	public void addAddressBook(String name) {

		if (addressBooks.containsKey(name)) {
			System.out.println("Address Book already exists.");
			return;
		}

		addressBooks.put(name, new AddressBook(name));
		System.out.println("Address Book created successfully.");
	}

	public AddressBook getAddressBook(String name) {
		return addressBooks.get(name);
	}

	public Map<String, AddressBook> getAllAddressBooks() {
		return addressBooks;
	}
}