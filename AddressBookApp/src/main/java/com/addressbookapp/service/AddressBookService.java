package com.addressbookapp.service;

import com.addressbookapp.model.Contact;

public interface AddressBookService {

	void addContact(Contact contact);

	void editContact(String name, Contact updatedContact);
	
	void deleteContact(String name);
	
	Contact findContact(String name);
}