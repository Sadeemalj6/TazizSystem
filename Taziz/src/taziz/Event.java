package taziz;

public class Event {
    private final long id;
    private final String name;
    private final String date;
    private final String time;
    private final String location;
    private final int totalSeats;
    private final int availableSeats;
    private final String adminName;

    public Event(long id, String name, String date, String time, String location,
                 int totalSeats, int availableSeats, String adminName) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.adminName = adminName;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getLocation() { return location; }
    public int getTotalSeats() { return totalSeats; }
    public int getAvailableSeats() { return availableSeats; }
    public String getAdminName() { return adminName; }
}
