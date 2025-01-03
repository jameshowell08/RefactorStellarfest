package factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Event;

public class EventFactory {
    public static Event createEvent(ResultSet rs) throws SQLException {
        Event event = new Event();
        event.setEvent_id(rs.getString("event_id"));
        event.setEvent_name(rs.getString("event_name"));
        event.setEvent_date(rs.getString("event_date"));
        event.setEvent_location(rs.getString("event_location"));
        event.setEvent_description(rs.getString("event_description"));
        event.setOrganizer_id(rs.getString("organizer_id"));
        return event;
    }
}

