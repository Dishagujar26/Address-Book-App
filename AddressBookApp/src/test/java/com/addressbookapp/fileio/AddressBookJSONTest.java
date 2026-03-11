package com.addressbookapp.fileio;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.addressbookapp.manager.AddressBookManager;
import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBookService;

@SpringBootTest
class AddressBookJSONTest {

	@Autowired
	AddressBookService service;

	@Autowired
	AddressBookManager manager;

	@Test
	void testJSONSaveLoad() {

		manager.addAddressBook("Friends");

		Contact contact = new Contact("Rahul", "K", "A", "B", "MP", "1", "1", "a");

		service.addContact("Friends", contact);

		service.saveAddressBookToJSON("Friends");

		service.loadAddressBookFromJSON("Friends");

		assertFalse(service.getAllContacts("Friends").isEmpty());
	}
}