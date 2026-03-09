package com.addressbookapp.service;

import java.util.List;

import com.addressbookapp.model.Contact;

public interface AddressBookService {

	void addContact(Contact contact);

	void editContact(String name, Contact updatedContact);
	
	void deleteContact(String name);
	
	List<Contact> getAllContacts();
	
	Contact findContact(String name);
}