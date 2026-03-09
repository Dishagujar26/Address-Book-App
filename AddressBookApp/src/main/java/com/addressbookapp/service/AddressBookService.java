package com.addressbookapp.service;

import com.addressbookapp.model.Contact;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface AddressBookService {

	void addContact(String bookName, Contact contact);

	void editContact(String bookName, String name, Contact updatedContact);

	void deleteContact(String bookName, String name);

	List<Contact> getAllContacts(String bookName);

	Contact findContact(String bookName, String name);

	List<Contact> searchByCity(String city);

	List<Contact> searchByState(String state);

	Map<String, List<Contact>> getPersonsByCity();

	Map<String, List<Contact>> getPersonsByState();

	Map<String, Long> getContactCount(Function<Contact, String> classifier);
}