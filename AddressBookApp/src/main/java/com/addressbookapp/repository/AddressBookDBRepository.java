package com.addressbookapp.repository;

import com.addressbookapp.model.Contact;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<Contact> getContactsByDateRange(LocalDate start, LocalDate end) {

		List<Contact> contacts = new ArrayList<>();

		String query = """
				SELECT * FROM contacts
				WHERE date_added BETWEEN ? AND ?
				""";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setDate(1, java.sql.Date.valueOf(start));
			ps.setDate(2, java.sql.Date.valueOf(end));

			ResultSet rs = ps.executeQuery();

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

	public Map<String, Long> countContactsByCity() {

		Map<String, Long> result = new HashMap<>();

		String query = """
				SELECT city, COUNT(*) AS total
				FROM contacts
				GROUP BY city
				""";

		try (Connection connection = dataSource.getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				result.put(rs.getString("city"), rs.getLong("total"));
			}

		} catch (Exception e) {
			System.out.println("Database error");
		}

		return result;
	}

	public Map<String, Long> countContactsByState() {

		Map<String, Long> result = new HashMap<>();

		String query = """
				SELECT state, COUNT(*) AS total
				FROM contacts
				GROUP BY state
				""";

		try (Connection connection = dataSource.getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				result.put(rs.getString("state"), rs.getLong("total"));
			}

		} catch (Exception e) {
			System.out.println("Database error");
		}

		return result;
	}

	public boolean addContactToDB(Contact contact) {

		String query = """
				INSERT INTO contacts
				(first_name,last_name,address,city,state,zip,phone,email)
				VALUES (?,?,?,?,?,?,?,?)
				""";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {

			connection.setAutoCommit(false);

			ps.setString(1, contact.getFirstName());
			ps.setString(2, contact.getLastName());
			ps.setString(3, contact.getAddress());
			ps.setString(4, contact.getCity());
			ps.setString(5, contact.getState());
			ps.setString(6, contact.getZip());
			ps.setString(7, contact.getPhoneNumber());
			ps.setString(8, contact.getEmail());

			ps.executeUpdate();

			connection.commit();

			return true;

		} catch (Exception e) {
			System.out.println("Insert failed");
			return false;
		}
	}

}