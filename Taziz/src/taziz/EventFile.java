package taziz;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * Fixed-length event records stored in events.dat.
 * Layout: ID(long), name(50), date(20), time(20), location(50),
 * total seats(int), available seats(int), admin name(50).
 */
public final class EventFile {
    private static final String FILE_NAME = "events.dat";
    private static final int NAME_LEN = 50;
    private static final int DATE_LEN = 20;
    private static final int TIME_LEN = 20;
    private static final int LOCATION_LEN = 50;
    private static final int ADMIN_LEN = 50;
    private static final long RECORD_SIZE =
            8L + 2L * (NAME_LEN + DATE_LEN + TIME_LEN + LOCATION_LEN)
            + 4L + 4L + 2L * ADMIN_LEN;

    // Offset of availableSeats inside each record:
    private static final long AVAILABLE_SEATS_OFFSET =
            8L + 2L * (NAME_LEN + DATE_LEN + TIME_LEN + LOCATION_LEN) + 4L;

    private EventFile() {}

    private static RandomAccessFile open() throws IOException {
        return new RandomAccessFile(new File(FILE_NAME), "rw");
    }

    private static void writeEvent(RandomAccessFile file, Event event) throws IOException {
        file.writeLong(event.getId());
        FixedRecordIO.writeFixedString(file, event.getName(), NAME_LEN);
        FixedRecordIO.writeFixedString(file, event.getDate(), DATE_LEN);
        FixedRecordIO.writeFixedString(file, event.getTime(), TIME_LEN);
        FixedRecordIO.writeFixedString(file, event.getLocation(), LOCATION_LEN);
        file.writeInt(event.getTotalSeats());
        file.writeInt(event.getAvailableSeats());
        FixedRecordIO.writeFixedString(file, event.getAdminName(), ADMIN_LEN);
    }

    private static Event readEvent(RandomAccessFile file) throws IOException {
        long id = file.readLong();
        String name = FixedRecordIO.readFixedString(file, NAME_LEN);
        String date = FixedRecordIO.readFixedString(file, DATE_LEN);
        String time = FixedRecordIO.readFixedString(file, TIME_LEN);
        String location = FixedRecordIO.readFixedString(file, LOCATION_LEN);
        int totalSeats = file.readInt();
        int availableSeats = file.readInt();
        String adminName = FixedRecordIO.readFixedString(file, ADMIN_LEN);
        return new Event(id, name, date, time, location, totalSeats, availableSeats, adminName);
    }

    public static synchronized Event addEvent(String name, String date, String time,
                                              String location, int seats, String adminName)
            throws IOException {
        if (seats <= 0) throw new IllegalArgumentException("Seats must be greater than zero.");
        try (RandomAccessFile file = open()) {
            long id = file.length() / RECORD_SIZE + 1;
            Event event = new Event(id, name, date, time, location, seats, seats, adminName);
            file.seek(file.length());
            writeEvent(file, event);
            return event;
        }
    }

    public static synchronized List<Event> getAllEvents() throws IOException {
        List<Event> events = new ArrayList<>();
        try (RandomAccessFile file = open()) {
            long validLength = file.length() - (file.length() % RECORD_SIZE);
            for (long offset = 0; offset < validLength; offset += RECORD_SIZE) {
                file.seek(offset);
                events.add(readEvent(file));
            }
        }
        return events;
    }

    public static synchronized Event getEvent(long eventId) throws IOException {
        try (RandomAccessFile file = open()) {
            long validLength = file.length() - (file.length() % RECORD_SIZE);
            for (long offset = 0; offset < validLength; offset += RECORD_SIZE) {
                file.seek(offset);
                Event event = readEvent(file);
                if (event.getId() == eventId) return event;
            }
        }
        return null;
    }

    /** Uses seek() to update only the available-seat integer in the matching record. */
    public static synchronized boolean reserveSeat(long eventId) throws IOException {
        try (RandomAccessFile file = open()) {
            long validLength = file.length() - (file.length() % RECORD_SIZE);
            for (long offset = 0; offset < validLength; offset += RECORD_SIZE) {
                file.seek(offset);
                Event event = readEvent(file);
                if (event.getId() == eventId) {
                    if (event.getAvailableSeats() <= 0) return false;
                    file.seek(offset + AVAILABLE_SEATS_OFFSET);
                    file.writeInt(event.getAvailableSeats() - 1);
                    return true;
                }
            }
        }
        return false;
    }

    /** Restores one seat if writing the confirmation fails after a reservation. */
    public static synchronized void restoreSeat(long eventId) throws IOException {
        try (RandomAccessFile file = open()) {
            long validLength = file.length() - (file.length() % RECORD_SIZE);
            for (long offset = 0; offset < validLength; offset += RECORD_SIZE) {
                file.seek(offset);
                Event event = readEvent(file);
                if (event.getId() == eventId) {
                    if (event.getAvailableSeats() < event.getTotalSeats()) {
                        file.seek(offset + AVAILABLE_SEATS_OFFSET);
                        file.writeInt(event.getAvailableSeats() + 1);
                    }
                    return;
                }
            }
        }
    }
}
