package factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;

public class ProductFactory {
	public static Product createProduct(ResultSet rs) throws SQLException {
		Product product = new Product();
		product.setProduct_id(rs.getString("product_id"));
		product.setProduct_name(rs.getString("product_name"));
		product.setProduct_description(rs.getString("product_description"));
        return product;
    }
}
