package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Connect;
import factory.UserFactory;
import template.UserIdGenerator;

public class User {
	private String user_id;
	private String user_email;
	private String user_name;
	private String user_password;
	private String user_role;
	private static Connect connect = Connect.getInstance();
	private final UserIdGenerator idGenerator = new UserIdGenerator();
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	
	public boolean Register(String name, String email, String role , String password) {
		 String query = "INSERT INTO user VALUES (?, ?, ?, ?, ?)";
		 PreparedStatement pst = connect.prepareStatement(query);
		 try {
			 pst.setString(1, idGenerator.generateId(getLastUserId()));
			pst.setString(2, email);
			pst.setString(3, name);
			pst.setString(4, password);
			pst.setString(5, role);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return false;
	}
	
	public User getUserByUserName(String name) {
		String query = "SELECT * FROM user WHERE user_name = ?";
		PreparedStatement pst = connect.prepareStatement(query);
		try {
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				User user = UserFactory.createUser(rs);
                return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUserByEmail(String email) {
		String query = "SELECT * FROM user WHERE user_email = ?";
		PreparedStatement pst = connect.prepareStatement(query);
		try {
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				User user = UserFactory.createUser(rs);
                return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUserById(String id) {
		String query = "SELECT * FROM user WHERE user_id = ?";
		PreparedStatement pst = connect.prepareStatement(query);
		try {
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				User user = UserFactory.createUser(rs);
                return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User login(String email,String password) {
		User temp = getUserByEmail(email);
		if(temp==null) {
			return null;
		}else if(temp.getUser_password().equals(password)) {
			return temp;
		}
		else {
			return null;
		}
	}
	
	public boolean changeUserName(String name, String userId) {
		 String query = "UPDATE user SET user_name = ? WHERE user_id = ?";
		 PreparedStatement pst = connect.prepareStatement(query);
		 try {
			pst.setString(1, name);
			pst.setString(2, userId);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	public boolean changeUserEmail(String email, String userId) {
		 String query = "UPDATE user SET user_email = ? WHERE user_id = ?";
		 PreparedStatement pst = connect.prepareStatement(query);
		 try {
			pst.setString(1, email);
			pst.setString(2, userId);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	public boolean changeUserPassword(String password, String userId) {
		 String query = "UPDATE user SET user_password = ? WHERE user_id = ?";
		 PreparedStatement pst = connect.prepareStatement(query);
		 try {
			pst.setString(1, password);
			pst.setString(2, userId);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	private static String getLastUserId() {
		String query =  "SELECT MAX(user_id) AS latest_id FROM User";
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
