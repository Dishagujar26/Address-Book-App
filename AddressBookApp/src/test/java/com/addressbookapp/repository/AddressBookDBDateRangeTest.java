package com.addressbookapp.repository;

import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressBookDBDateRangeTest {

	@Autowired
	AddressBookService service;

	@Test
	void testRetrieveContactsByDateRange() {

		LocalDate start = LocalDate.now().minusDays(5);
		LocalDate end = LocalDate.now().plusDays(1);

		List<Contact> contacts = service.getContactsByDateRange(start, end);

		assertNotNull(contacts);

		// Should retrieve at least one contact from DB
		assertFalse(contacts.isEmpty());
	}
}