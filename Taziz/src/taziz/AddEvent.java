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

    public AddEvent() {

        setTitle("Taaziz University Events Registration System");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        // =========================
        // Header
        // =========================

        JPanel header = new JPanel();
        header.setBackground(new Color(0, 128, 60));
        header.setBounds(0, 0, 500, 55);
        
        JLabel title = new JLabel("Add Event");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        header.add(title);
        panel.add(header);


        // =========================
        // Event Name
        // =========================
        JLabel eventNameLabel = new JLabel("Event Name:");
        eventNameLabel.setBounds(50, 75, 400, 45);
        eventNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(eventNameLabel);

        eventNameField = new JTextField();
        eventNameField.setBounds(180, 80, 275, 35);
        eventNameField.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(eventNameField);


        // =========================
        // Date
        // =========================
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(50, 120, 400, 45);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(180, 125, 275, 35);
        dateField.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(dateField);


        // =========================
        // Location
        // =========================
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(50, 165, 400, 45);
        locationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(locationLabel);

        locationField = new JTextField();
        locationField.setBounds(180, 170, 275, 35);
        locationField.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(locationField);


        // =========================
        // Add Event Button
        // =========================
        JButton addButton = new JButton("Add Event");
        addButton.setBounds(300, 240, 120, 35);
        addButton.setBackground(new Color(0, 128, 60));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setFocusPainted(false);
        panel.add(addButton);


        // =========================
        // Back Button
        // =========================
        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(190, 240, 90, 35);
        backButton.setBackground(new Color(0, 128, 60));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        panel.add(backButton);


        // =========================
        // Add Event Action
        // =========================
        addButton.addActionListener(e -> {

            String eventName = eventNameField.getText();
            String date = dateField.getText();
            String location = locationField.getText();

            if (eventName.isEmpty() || date.isEmpty() || location.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Event added successfully!",
                        "Confirmation",
                        JOptionPane.INFORMATION_MESSAGE
                );

                eventNameField.setText("");
                dateField.setText("");
                locationField.setText("");
            }
        });


        // =========================
        // Back Action
        // =========================
        backButton.addActionListener(e -> {
            dispose();
        });


        add(panel);
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new AddEvent().setVisible(true);
        });
    }
}