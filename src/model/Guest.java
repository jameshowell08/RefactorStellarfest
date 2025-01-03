package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.Connect;
import factory.GuestFactory;

public class Guest extends User{
	private static Connect connect = Connect.getInstance();
	
	public Vector<Guest> getGuests(String eventId){
		Vector<Guest> guests = new Vector<>();
		String query = "SELECT * FROM user u LEFT JOIN Invitation iv ON u.user_id = iv.user_id AND iv.event_id = ? WHERE u.user_role = ? AND iv.user_id IS NULL;";
		PreparedStatement pst = connect.prepareStatement(query);
		try {
		
			pst.setString(1, eventId);
			pst.setString(2, "Guest");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Guest guest = GuestFactory.createGuest(rs);
				guests.add(guest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guests;
	}
}
