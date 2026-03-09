# AddressBookApp

AddressBookApp is a Spring Boot based application used to manage contacts in an address book with details.

---
## START

- Created **AddressBookMain** class.
- Displayed the message **"Welcome to Address Book Program"** when the program starts.
- Initialized the base structure of the Address Book application on the **main branch**.

[START](https://github.com/rashi-raj/AddressBookApp/tree/main/src)

---
# Section 1: Streams API
---

## UC1 – Ability to Create a Contact in Address Book

Implemented the ability to create a **Contact** with required personal and address details.

**Features Implemented**

- Created a `Contact` class.
- Added fields
- Applied Object-Oriented Programming concepts.
- Followed proper naming conventions and code hygiene.

**GitHub Branch**

[feature/UC1-create-contact](https://github.com/rashi-raj/AddressBookApp/tree/feature/UC1-create-contact/src)

## UC2 – Ability to Add a New Contact to Address Book

Implemented functionality to add a new contact to the Address Book using console input.

**Features Implemented**

- Created an `AddressBook` class to manage contacts.
- Implemented method to **add a contact** to the address book.
- Demonstrated **relationship between AddressBook and Contact classes** using OOP.

**GitHub Branch**

[feature/UC2-add-contact](https://github.com/rashi-raj/AddressBookApp/tree/feature/UC2-add-contact)

---

## UC3 – Ability to Edit Existing Contact Person

Implemented functionality to edit an existing contact using the person's name.

**Features Implemented**

- Used **console input** to select and edit a contact.
- Search contact using **person's name**.
- Updated fields such as address, city, state, phone number, email, etc.
- Modified contact details inside the Address Book.

**GitHub Branch**

[feature/UC3-edit-contact](https://github.com/rashi-raj/AddressBookApp/tree/feature/UC3-edit-contact)

---

## UC4 – Ability to Delete a Person Using Person's Name

Implemented functionality to delete a contact from the Address Book.

**Features Implemented**

- Used **console input** to enter the person's name.
- Searched the contact using the name.
- Deleted the contact from the Address Book.

**GitHub Branch**

[feature/UC4-delete-contact](https://github.com/rashi-raj/AddressBookApp/tree/feature/UC4-delete-contact)

---

## UC5 – Ability to Add Multiple Persons to Address Book

Implemented functionality to add multiple contacts to the Address Book.

**Features Implemented**

- Used **console input** to add person details one at a time.
- Used **Collection Class (ArrayList)** to store multiple contacts.
- Address Book can now manage multiple persons.

**GitHub Branch**

[feature/UC5-add-multiple-contacts](https://github.com/rashi-raj/AddressBookApp/tree/feature/UC5-add-multiple-contacts)

---

## UC6 – Ability to Add Multiple Address Books to the System

Refactored the program to support multiple Address Books in the system.

**Features Implemented**

- Each **Address Book has a unique name**.
- Used **console input** to create new Address Books.
- Maintained a **Dictionary (HashMap)** of AddressBookName → AddressBook.
- System can now manage multiple address books.

**GitHub Branch**

[feature/UC6-multiple-addressbooks](https://github.com/rashi-raj/AddressBookApp/tree/feature/UC6-multiple-addressbooks)

---
