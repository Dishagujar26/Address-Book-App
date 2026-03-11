package com.addressbookapp.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.addressbookapp.service.AddressBookService;

@SpringBootTest
class AddressBookDBCountTest {

	@Autowired
	AddressBookService service;

	@Test
	void testCountContactsByCityDB() {

		Map<String, Long> result = service.getContactCountByCityFromDB();

		assertNotNull(result);
		assertFalse(result.isEmpty());
	}
}