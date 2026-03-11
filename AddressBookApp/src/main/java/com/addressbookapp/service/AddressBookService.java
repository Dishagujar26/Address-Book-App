package com.addressbookapp.service;

import com.addressbookapp.model.Contact;

import java.time.LocalDate;
import java.util.Comparator;
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

	List<Contact> sortContactsByName(String bookName);

	List<Contact> sortContacts(String bookName, Comparator<Contact> comparator);

	void saveAddressBookToFile(String bookName);

	void loadAddressBookFromFile(String bookName);

	void saveAddressBookToCSV(String bookName);

	void loadAddressBookFromCSV(String bookName);

	void saveAddressBookToJSON(String bookName);

	void loadAddressBookFromJSON(String bookName);

	List<Contact> getContactsFromDatabase();

	boolean updateContactInDB(String bookName, Contact contact);

	List<Contact> getContactsByDateRange(LocalDate start, LocalDate end);

	Map<String, Long> getContactCountByCityFromDB();

	Map<String, Long> getContactCountByStateFromDB();

	boolean addContactToDatabase(Contact contact);

	void addMultipleContactsToDB(List<Contact> contacts);
}