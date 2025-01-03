package controller;

import java.util.Vector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Event;
import model.Invitation;
import model.Product;
import model.Vendor;
import observer.ValidationErrorNotifier;

public class VendorController {
	private Invitation invitation = new Invitation();
	private Event event = new Event();
	private Product product = new Product();
	private Vendor vendor = new Vendor();
	private static ValidationErrorNotifier vn = ValidationErrorNotifier.getInstance();
	public VendorController() {
	}
	
	public void accInvitation(String userId, String eventId) {
		if(invitation.acceptInv(userId, eventId)) {
			vn.notifyObservers("Success to accept invitation");
		}else {
			vn.notifyObservers("Failed to accept invitation");
		}
		
	}
	public ObservableList<Event> getAcceptedInvitations(String email) {
		Vector<Event> temp = event.getAccEvents(email);
		ObservableList<Event> evntObs = FXCollections.observableArrayList(temp);
		return evntObs;
	}
	public ObservableList<Product> getProducts(String userId) {
		Vector<Product> temp = product.getProducts(userId);
		ObservableList<Product> productObs = FXCollections.observableArrayList(temp);
		return productObs;
	}
	public void createProduct(String product_name, String product_desc, String user_id) {
		String validationError = checkProductInput(product_name, product_desc);
		if (validationError != null) {
			vn.notifyObservers(validationError);
			return;
		}
		if(vendor.createProduct(product_name, product_desc, user_id)) {
			vn.notifyObservers("Success Created Product");
		}else {
			vn.notifyObservers("Failed Created Product");
		}
	}

	public String checkProductInput(String product_name, String product_desc) {
		if (product_name.isEmpty()) {
			return "Product cannot be empty";
		}
		if (product_desc.length() > 200) {
			return "Description cannot be more than 200 Characters";
		}
		return null;
	}
}
