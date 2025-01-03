package factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Vendor;

public class VendorFactory {
	    public static Vendor createVendor(ResultSet rs) throws SQLException {
	    	Vendor vendor = new Vendor();
			vendor.setUser_email(rs.getString("user_email"));
			vendor.setUser_name(rs.getString("user_name"));
			vendor.setUser_password(rs.getString("user_password"));
			vendor.setUser_role(rs.getString("user_role"));
			vendor.setUser_id(rs.getString("user_id"));
	        return vendor;
	    }

}
