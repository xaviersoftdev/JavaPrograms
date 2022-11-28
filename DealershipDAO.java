package dealershipCRUD;

import java.util.*;
import java.sql.*;

public class DealershipDAO{
	public List<Dealership> findAll() {
		try {
			List<Dealership> listDealership = new ArrayList<>();
			PreparedStatement ps = DbConnection.getConnection().prepareStatement("SELECT * from dealership");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				int mileage = rs.getInt("mileage");
				int mpg = rs.getInt("mpg");
				Double cost = rs.getDouble("cost");
				Double salesPrice = rs.getDouble("salesPrice");
				Boolean sold = rs.getBoolean("sold");
				Double profit = rs.getDouble("profit");
				Dealership d = new Dealership(id, mileage, mpg, cost, salesPrice, sold, profit);
				listDealership.add(d);
			}
			return listDealership;
		} catch(Exception e) {
			return null;
		}
				
	}
	
	public Dealership find(String id) {
		try {
			Dealership d = null;
			PreparedStatement ps = DbConnection.getConnection().prepareStatement("SELECT * from dealership WHERE id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				//String id = rs.getString("id");
				int mileage = (rs.getInt("mileage"));
				int mpg = rs.getInt("mpg");
				Double cost =rs.getDouble("cost");
				Double salesPrice = rs.getDouble("salesPrice");
				Boolean sold = rs.getBoolean("sold");
				Double profit = rs.getDouble("profit");
				d = new Dealership(id, mileage, mpg, cost, salesPrice, sold, profit);
			}
			return d;
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean create(Dealership d) {
		try {
			PreparedStatement ps = DbConnection.getConnection().prepareStatement(
					"INSERT INTO dealership(id,mileage,mpg,cost,salesPrice,sold,profit) values(?,?,?,?,?,?,?);");
			ps.setString(1, d.getId());
			ps.setInt(2, d.getMileage());
			ps.setInt(3, d.getMpg());
			ps.setDouble(4, d.getCost());
			ps.setDouble(5, d.getSalesPrice());
			ps.setBoolean(6, d.getSold());
			ps.setDouble(7, d.getProfit());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean edit(Dealership d) {
		try {
			PreparedStatement ps = DbConnection.getConnection()
					.prepareStatement("UPDATE dealership set id=?,mileage=?,mpg=?,cost=?,salesPrice=?,sold=?,profit=?" + " where id=?");
			ps.setString(1, d.getId());
			ps.setInt(2, d.getMileage());
			ps.setInt(3, d.getMpg());
			ps.setDouble(4, d.getCost());
			ps.setDouble(5, d.getSalesPrice());
			ps.setBoolean(6, d.getSold());
			ps.setDouble(7, d.getProfit());
			//System.out.println(ps.toString());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean delete(Dealership d) {
		try {
			PreparedStatement ps = DbConnection.getConnection().prepareStatement("DELETE FROM dealership where id=?");
			ps.setString(1, d.getId());
			//System.out.println(ps.toString());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	
}
