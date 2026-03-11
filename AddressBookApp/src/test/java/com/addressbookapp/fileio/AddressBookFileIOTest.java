package com.addressbookapp.fileio;
import com.addressbookapp.manager.AddressBookManager;
import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressBookFileIOTest {

	@Autowired
	AddressBookService service;

	@Autowired
	AddressBookManager manager;

	@Test
	void testSaveAndLoadTXT() {

		manager.addAddressBook("Friends");

		Contact contact = new Contact("Rahul", "K", "A", "B", "MP", "1", "1", "a");

		service.addContact("Friends", contact);

		service.saveAddressBookToFile("Friends");

		service.loadAddressBookFromFile("Friends");

		assertFalse(service.getAllContacts("Friends").isEmpty());
	}
}