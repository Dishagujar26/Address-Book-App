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

## UC7 – Prevent Duplicate Entry of Same Person

Implemented validation to prevent duplicate contacts in the same Address Book.

**Features Implemented**

- Checked duplicate entries based on **person name**.
- Used **Collection methods** to search contacts.
- Overrode **equals() method** in Contact class.
- Used **Java Streams** to check duplicate entries before adding.

**GitHub Branch**

[feature/UC7-prevent-duplicate-contact](https://github.com/rashi-raj/AddressBookApp/tree/feature/UC7-prevent-duplicate-contact)

---

## UC8 – Ability to Search Person by City or State

Implemented functionality to search for contacts across multiple Address Books by city or state.

**Features Implemented**

- Search persons using **city or state**.
- Results can return **multiple persons**.
- Used **Java Streams** for filtering contacts.

**GitHub Branch**

[feature/UC8-search-person-by-city-or-state](https://github.com/rashi-raj/AddressBookApp/tree/feature/UC8-search-person-by-city-or-state)

---

## UC9 – Ability to View Persons by City or State

Implemented functionality to view contacts grouped by city or state.

**Features Implemented**

- Maintained **Dictionary (HashMap)** for:
  - City → Person
  - State → Person
- Used **Collection Library** for mapping.
- Used **Java Streams** for processing and displaying results.

**GitHub Branch**

[feature/UC9-view-persons-by-city-or-state](https://github.com/rashi-raj/AddressBookApp/tree/feature/UC9-view-persons-by-city-or-state)

---

## UC10 – Ability to Count Contacts by City or State

Implemented functionality to count number of contacts in each city or state.

**Features Implemented**

- Display **number of persons in a particular city**.
- Display **number of persons in a particular state**.
- Used **Java Streams** for counting and aggregation.

**GitHub Branch**

[feature/UC10-count-contacts-by-city-or-state](https://github.com/rashi-raj/AddressBookApp/tree/feature/UC10-count-contacts-by-city-or-state)

---

## UC11 – Ability to Sort Entries Alphabetically by Person’s Name

Implemented functionality to sort all contact entries in the Address Book alphabetically by person's name.

**Features Implemented**

- Used **console option** to trigger sorting of contacts.
- Sorted entries **alphabetically by person name**.
- Used **Collection Library sorting methods**.
- Overrode **toString() method** to properly print contact details in the console.
- Used **Java Streams** for processing and sorting data.

**GitHub Branch**

[feature/UC11-sort-contacts-by-name](https://github.com/rashi-raj/AddressBookApp/tree/feature/UC11-sort-contacts-by-name)

---

## UC12 – Ability to Sort Entries by City, State, or Zip

Implemented functionality to sort contact entries in the Address Book based on city, state, or zip code.

**Features Implemented**

- Created **separate functions** to sort contacts by:
  - City
  - State
  - Zip
- Used **Collection Library** for sorting operations.
- Used **Java Streams** to perform sorting and display results in the console.

**GitHub Branch**

[feature/UC12-sort-contacts-by-city-state-zip](https://github.com/rashi-raj/AddressBookApp/tree/feature/UC12-sort-contacts-by-city-state-zip)

---

# Section 2: IO Streams 
---

## UC13 – Ability to Read or Write Address Book to a File (File IO)

Implemented functionality to store and retrieve Address Book contacts using Java File IO.

**Features Implemented**

- Used **Java File IO** to write Address Book data to a file.
- Implemented functionality to **read contacts from the file**.
- Stored contact details such as name, address, city, state, zip, phone, and email.
- Displayed file content in the **console** after reading.

**GitHub Branch**

[feature/UC13-read-write-addressbook-file-io](https://github.com/rashi-raj/AddressBookApp/tree/feature/UC13-file-io)

---

## UC14 – Ability to Read/Write Address Book as CSV File

Implemented functionality to store and retrieve Address Book contacts in **CSV format**.

**Features Implemented**

- Used **OpenCSV Library** to handle CSV file operations.
- Implemented **write operation** to store contacts in CSV format.
- Implemented **read operation** to load contacts from CSV file.
- Displayed retrieved data in the console.

**GitHub Branch**

[feature/UC14-read-write-addressbook-csv](https://github.com/rashi-raj/AddressBookApp/tree/feature/UC14-csv-file)

---
