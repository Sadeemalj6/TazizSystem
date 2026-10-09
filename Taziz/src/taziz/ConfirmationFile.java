/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taziz;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Appends a human-readable receipt for each successful event registration. */
public final class ConfirmationFile {
    private static final String FILE_NAME = "confirmation.txt";
    private ConfirmationFile() {}

    public static void createConfirmation(Account student, Event event) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write("TAAZIZ - Registration Confirmation");
            writer.newLine();
            writer.write("==================================");
            writer.newLine();
            writer.write("Registration time: " + LocalDateTime.now().format(formatter));
            writer.newLine();
            writer.write("Student name: " + student.getName());
            writer.newLine();
            writer.write("University ID: " + student.getUniversityId());
            writer.newLine();
            writer.write("Event ID: " + event.getId());
            writer.newLine();
            writer.write("Event name: " + event.getName());
            writer.newLine();
            writer.write("Date: " + event.getDate());
            writer.newLine();
            writer.write("Time: " + event.getTime());
            writer.newLine();
            writer.write("Location: " + event.getLocation());
            writer.newLine();
            writer.write("Status: Confirmed");
            writer.newLine();
            writer.newLine();
        }
    }
}
