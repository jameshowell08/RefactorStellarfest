package factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Guest;

public class GuestFactory {
	 public static Guest createGuest(ResultSet rs) throws SQLException {
	    	Guest guest = new Guest();
			guest.setUser_email(rs.getString("user_email"));
			guest.setUser_name(rs.getString("user_name"));
			guest.setUser_password(rs.getString("user_password"));
			guest.setUser_role(rs.getString("user_role"));
			guest.setUser_id(rs.getString("user_id"));
	        return guest;
	    }
}
