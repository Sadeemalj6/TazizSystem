/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taziz;

/**
 *
 * @author sdoom
 */
import javax.swing.*;
import java.awt.*;

public class AddEvent extends JFrame {

    private JTextField eventNameField;
    private JTextField dateField;
    private JTextField locationField;
    private JTextField seatsField;

    public AddEvent() {

        setTitle("Taaziz University Events Registration System");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        // =========================
        // Header
        // =========================

        JPanel header = new JPanel();
        header.setBackground(new Color(27, 94, 32));
        header.setBounds(0, 0, 500, 55);

        JLabel title =
                new JLabel("Add Event");

        title.setForeground(Color.WHITE);
        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        header.add(title);
        panel.add(header);

        // =========================
        // Event Name
        // =========================

        JLabel eventNameLabel =
                new JLabel("Event Name:");

        eventNameLabel.setBounds(
                50, 70, 120, 35
        );

        eventNameLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        panel.add(eventNameLabel);

        eventNameField = new JTextField();

        eventNameField.setBounds(
                180, 70, 275, 35
        );

        panel.add(eventNameField);

        // =========================
        // Date
        // =========================

        JLabel dateLabel =
                new JLabel("Date:");

        dateLabel.setBounds(
                50, 115, 120, 35
        );

        dateLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        panel.add(dateLabel);

        dateField = new JTextField();

        dateField.setBounds(
                180, 115, 275, 35
        );

        panel.add(dateField);

        // =========================
        // Location
        // =========================

        JLabel locationLabel =
                new JLabel("Location:");

        locationLabel.setBounds(
                50, 160, 120, 35
        );

        locationLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        panel.add(locationLabel);

        locationField = new JTextField();

        locationField.setBounds(
                180, 160, 275, 35
        );

        panel.add(locationField);

        // =========================
        // Seats
        // =========================

        JLabel seatsLabel =
                new JLabel("Seats:");

        seatsLabel.setBounds(
                50, 205, 120, 35
        );

        seatsLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        panel.add(seatsLabel);

        seatsField = new JTextField();

        seatsField.setBounds(
                180, 205, 275, 35
        );

        panel.add(seatsField);

        // =========================
        // Add Button
        // =========================

        JButton addButton =
                new JButton("Add Event");

        addButton.setBounds(
                300, 280, 120, 35
        );

        addButton.setBackground(
                new Color(27, 94, 32)
        );

        addButton.setForeground(Color.WHITE);

        addButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        addButton.setFocusPainted(false);

        panel.add(addButton);

        // =========================
        // Back Button
        // =========================

        JButton backButton =
                new JButton("Back");

        backButton.setBounds(
                190, 280, 90, 35
        );

        backButton.setBackground(
                new Color(27, 94, 32)
        );

        backButton.setForeground(Color.WHITE);

        backButton.setFocusPainted(false);

        panel.add(backButton);

        // =========================
        // Add Event Action
        // =========================

        addButton.addActionListener(e -> {

            String eventName =
                    eventNameField.getText().trim();

            String date =
                    dateField.getText().trim();

            String location =
                    locationField.getText().trim();

            String seatsText =
                    seatsField.getText().trim();

            if (eventName.isEmpty()
                    || date.isEmpty()
                    || location.isEmpty()
                    || seatsText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            try {

                int seats =
                        Integer.parseInt(seatsText);

                if (seats <= 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Seats must be greater than 0.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                // Create Event
                Event event = new Event(
                        eventName,
                        date,
                        location,
                        seats
                );

                // Add to shared ArrayList
                EventManager.addEvent(event);

                JOptionPane.showMessageDialog(
                        this,
                        "Event added successfully!",
                        "Confirmation",
                        JOptionPane.INFORMATION_MESSAGE
                );

                // Clear fields
                eventNameField.setText("");
                dateField.setText("");
                locationField.setText("");
                seatsField.setText("");

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seats must be a number.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // =========================
        // Back
        // =========================

        backButton.addActionListener(e -> {

            new HomeAdmin().setVisible(true);
            dispose();

        });

        add(panel);
    }

    public static void main(String[] args) {

        new AddEvent().setVisible(true);
    }
}
