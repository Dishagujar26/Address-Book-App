package com.addressbookapp.repository;

import com.addressbookapp.model.Contact;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressBookDBRepository {

	private final DataSource dataSource;

	public AddressBookDBRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Contact> getAllContacts() {

		List<Contact> contacts = new ArrayList<>();

		try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {

			String query = "SELECT * FROM contacts";

			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {

				Contact contact = new Contact(rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zip"),
						rs.getString("phone"), rs.getString("email"));

				contacts.add(contact);
			}

		} catch (Exception e) {
			System.out.println("Database error");
		}

		return contacts;
	}

	public boolean updateContact(Contact contact) {

		String query = """
				UPDATE contacts
				SET address=?, city=?, state=?, zip=?, phone=?, email=?
				WHERE first_name=? AND last_name=?
				""";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setString(1, contact.getAddress());
			ps.setString(2, contact.getCity());
			ps.setString(3, contact.getState());
			ps.setString(4, contact.getZip());
			ps.setString(5, contact.getPhoneNumber());
			ps.setString(6, contact.getEmail());

			ps.setString(7, contact.getFirstName());
			ps.setString(8, contact.getLastName());

			int rows = ps.executeUpdate();

			return rows > 0;

		} catch (Exception e) {
			System.out.println("Database update failed");
			return false;
		}
	}
}