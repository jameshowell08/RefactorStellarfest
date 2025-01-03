package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.Connect;
import factory.VendorFactory;
import template.ProductIdGenerator;

public class Vendor extends User{
	private static Connect connect = Connect.getInstance();
	private final ProductIdGenerator idGenerator = new ProductIdGenerator();
	public Vector<Vendor> getVendors(String eventId){
		Vector<Vendor> vendors = new Vector<>();
		String query = "SELECT * FROM user u LEFT JOIN invitation iv ON u.user_id = iv.user_id AND iv.event_id = ? WHERE u.user_role = ? AND iv.user_id IS NULL;";
		PreparedStatement pst = connect.prepareStatement(query);
		try {
		
			pst.setString(1, eventId);
			pst.setString(2, "Vendor");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Vendor vendor = VendorFactory.createVendor(rs);
				vendors.add(vendor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendors;
	}
	public boolean createProduct(String name, String description,String userId) {
		String query = "INSERT INTO product VALUES (?, ?, ?, ?);";
		PreparedStatement pst = connect.prepareStatement(query);
		try {
			pst.setString(1, idGenerator.generateId(getLastProductId()));
			pst.setString(2, name);
			pst.setString(3, description);
			pst.setString(4, userId);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	private static String getLastProductId() {
		String query =  "SELECT MAX(product_id) AS latest_id FROM Product";
		PreparedStatement pst = connect.prepareStatement(query);
		try {
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				return rs.getString("latest_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
