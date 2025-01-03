package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.Connect;
import factory.EventFactory;
import factory.GuestFactory;
import factory.UserFactory;
import factory.VendorFactory;

public class Admin extends User{
	
	private Connect connect = Connect.getInstance();
	
	public Vector<User> getUsers(){
		Vector<User> users = new Vector<>();
		String query = "SELECT * FROM user;";
		PreparedStatement pst = connect.prepareStatement(query);
		try {
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				User user = UserFactory.createUser(rs);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	public boolean deleteUser(String userId) {
		String query = "DELETE FROM user WHERE user_id=?;";
		PreparedStatement pst = connect.prepareStatement(query);
		try {
			pst.setString(1, userId);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Vector<Event> getEvents(){
		Vector<Event> events = new Vector<>();
		String query = "SELECT * FROM event;";
		PreparedStatement pst = connect.prepareStatement(query);
		try {
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				 Event event = EventFactory.createEvent(rs);
                 events.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return events;
	}
	public boolean deleteEvent(String eventId) {
		String query = "DELETE FROM event WHERE event_id=?;";
		PreparedStatement pst = connect.prepareStatement(query);
		try {
			pst.setString(1, eventId);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public Vector<Guest> getGuestAttendeeByEventId(String eventId) {
		Vector<Guest> guests = new Vector<>();
		String query = "SELECT * FROM user u LEFT JOIN invitation iv ON u.user_id = iv.user_id AND iv.event_id = ? WHERE u.user_role = ? AND iv.invitation_status = ?;";
		PreparedStatement pst = connect.prepareStatement(query);
		try {
			pst.setString(1, eventId);
			pst.setString(2, "Guest");
			pst.setString(3, "Accepted");
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
	public Vector<Vendor> getVendorAttendeeByEventId(String eventId) {
		Vector<Vendor> vendors = new Vector<>();
		String query = "SELECT * FROM user u LEFT JOIN invitation iv ON u.user_id = iv.user_id AND iv.event_id = ? WHERE u.user_role = ? AND iv.invitation_status = ?;";
		PreparedStatement pst = connect.prepareStatement(query);
		try {
			pst.setString(1, eventId);
			pst.setString(2, "Vendor");
			pst.setString(3, "Accepted");
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
}
