package controller;

import java.util.Vector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Event;
import model.Invitation;
import observer.ValidationErrorNotifier;

public class GuestController {
	private Invitation invitation = new Invitation();
	private Event event = new Event();
	private static final ValidationErrorNotifier vn = ValidationErrorNotifier.getInstance();
	public GuestController() {
	}
	public void accInvitation(String userId, String eventId) {
		if(invitation.acceptInv(userId, eventId)) {
			vn.notifyObservers("Invitation Accepted");
		}else {
			vn.notifyObservers("Invitation Failed");
		}
	}
	public ObservableList<Event> getAcceptedInvitations(String email){
		Vector<Event> temp = event.getAccEvents(email);
		ObservableList<Event> evntObs = FXCollections.observableArrayList(temp);
		return evntObs;
	}
}