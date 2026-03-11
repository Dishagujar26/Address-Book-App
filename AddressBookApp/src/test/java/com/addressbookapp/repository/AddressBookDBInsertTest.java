package com.addressbookapp.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBookService;

@SpringBootTest
class AddressBookDBInsertTest {

	@Autowired
	AddressBookService service;

	@Test
	void testInsertContactDB() {

		Contact contact = new Contact("Aman", "Verma", "A", "Bhopal", "MP", "1", "1", "a");

		boolean result = service.addContactToDatabase(contact);

		assertTrue(result);
	}
}