package com.addressbookapp.repository;
import com.addressbookapp.model.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressBookDBRepositoryTest {

	@Autowired
	AddressBookDBRepository repository;

	@Test
	void testRetrieveContacts() {

		List<Contact> contacts = repository.getAllContacts();

		assertNotNull(contacts);
	}
}