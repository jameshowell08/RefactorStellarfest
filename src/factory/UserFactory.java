package factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserFactory {
	public static User createUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUser_email(rs.getString("user_email"));
		user.setUser_name(rs.getString("user_name"));
		user.setUser_password(rs.getString("user_password"));
		user.setUser_role(rs.getString("user_role"));
		user.setUser_id(rs.getString("user_id"));
        return user;
    }
}
