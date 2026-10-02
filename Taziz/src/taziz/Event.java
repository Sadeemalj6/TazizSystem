package taziz;

public class Event {

    private String name;
    private String date;
    private String location;
    private int totalSeats;
    private int availableSeats;

    public Event(String name, String date, String location, int totalSeats) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public boolean registerStudent() {

        if (availableSeats > 0) {
            availableSeats--;
            return true;
        }

        return false;
    }
}
