package contactCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
	// helper method to convert a ResultSet object returned from JDBC call to a Contact object.
	private Contact createContactPerson(ResultSet rs) {
		Contact p = new Contact();
		try {
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			p.setAddress(rs.getString("address"));
			p.setCellPhone(rs.getString("cellphone"));
			p.setEmail(rs.getString("email"));
		} catch (SQLException ex) {
		}
		return p;
	}

	public List<Contact> findAll() {
		String sql = "Select * from contact order by id";
		List<Contact> list = new ArrayList<>();
		try {
			Connection connection = DbConnection.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Contact p = createContactPerson(rs);
				list.add(p);
			}
			rs.close();
			connection.close();
		} catch (SQLException ex) {
		}
		return list;
	}

	public Contact find(int id) {
		try {
			Contact contact = null;
			PreparedStatement ps = DbConnection.getConnection().prepareStatement("SELECT * from Contact WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				contact = createContactPerson(rs);
			}
			return contact;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean delete(Contact contact) {
		try {
			PreparedStatement ps = DbConnection.getConnection().prepareStatement("DELETE FROM contact where id=?");
			ps.setInt(1, contact.getId());
			return ps.executeUpdate() > 0;
			
		} catch (Exception e) {
			return false;
		}
	}

	public boolean create(Contact contact) {
		try {
			PreparedStatement ps = DbConnection.getConnection().prepareStatement("INSERT INTO contact(name, address, cellphone, email) values(?,?,?,?);");
			ps.setString(1, contact.getName());
			ps.setString(2, contact.getAddress());
			ps.setString(3, contact.getCellPhone());
			ps.setString(4, contact.getEmail());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	} 

	public boolean edit(Contact contact) {
		try {
			PreparedStatement ps = DbConnection.getConnection().prepareStatement("UPDATE contact set name=?, address=?, cellphone=?, email=?" + " where id=?");
			ps.setString(1, contact.getName());
			ps.setString(2, contact.getAddress());
			ps.setString(3, contact.getCellPhone());
			ps.setString(4, contact.getEmail());
			ps.setInt(5, contact.getId());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}
}