package com.addressbookapp.repository;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.addressbookapp.manager.AddressBookManager;
import com.addressbookapp.model.Contact;
import com.addressbookapp.service.AddressBookService;

@SpringBootTest
class AddressBookDBUpdateTest {

    @Autowired
    AddressBookService service;

    @Autowired
    AddressBookManager manager;

    @Test
    void testUpdateContactSync() {

        manager.addAddressBook("Friends");

        Contact contact =
                new Contact("Harsh","Sharma","A","B","MP","1","1","a");

        service.addContact("Friends", contact);

        contact.setCity("Delhi");

        boolean synced =
                service.updateContactInDB("Friends", contact);

        assertTrue(synced);
    }
}