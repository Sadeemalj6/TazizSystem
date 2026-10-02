package taziz;

import java.util.ArrayList;

public class EventManager {

    private static ArrayList<Event> events = new ArrayList<>();

    // Add Event
    public static void addEvent(Event event) {
        events.add(event);
    }

    // Get all Events
    public static ArrayList<Event> getEvents() {
        return events;
    }

    // Get Event by index
    public static Event getEvent(int index) {
        return events.get(index);
    }
}
