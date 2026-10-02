/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taziz;
import java.io.FileWriter;
import java.io.IOException;

public class ConfirmationFile {

    public static void createConfirmation() {

        String studentID = "2212345";
        String eventName = "AI Workshop";
        String date = "10/10/2026";
        String location = "FCIT Building";

        try {
            FileWriter writer = new FileWriter("confirmation.txt");

            writer.write("TAAZIZ - Registration Confirmation\n");
            writer.write("--------------------------------\n");
            writer.write("Student ID: " + studentID + "\n");
            writer.write("Event: " + eventName + "\n");
            writer.write("Date: " + date + "\n");
            writer.write("Location: " + location + "\n");
            writer.write("Status: Confirmed\n");

            writer.close();

            System.out.println("Confirmation file created successfully.");

        } catch (IOException e) {
            System.out.println("Error creating confirmation file.");
        }
    }
}