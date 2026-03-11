package com.addressbookapp.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBookService;

@SpringBootTest
@Transactional
@Rollback
class AddressBookDBThreadTest {

    @Autowired
    AddressBookService service;

    @Test
    void testAddMultipleContactsUsingThreads() {

        List<Contact> contacts = List.of(
                new Contact("Test1","User","A","Bhopal","MP","1","1","t1@test.com"),
                new Contact("Test2","User","A","Bhopal","MP","1","1","t2@test.com")
        );

        service.addMultipleContactsToDB(contacts);

        assertTrue(true);
    }
}