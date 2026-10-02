package taziz;

import javax.swing.*;
import java.awt.*;

public class Register extends JFrame {

    private Event event;

    public Register(Event event) {

        this.event = event;

        setTitle("Frame 3 - Event Details");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // =========================
        // Main Panel
        // =========================

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(Color.WHITE);

        // =========================
        // Header
        // =========================

        JPanel topPanel =
                new JPanel(new BorderLayout());

        topPanel.setBackground(
                new Color(27, 94, 32)
        );

        topPanel.setPreferredSize(
                new Dimension(500, 60)
        );

        JLabel logoLabel =
                new JLabel("TAAZIZ");

        logoLabel.setForeground(Color.WHITE);

        logoLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        logoLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        topPanel.add(logoLabel);

        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        // =========================
        // Details
        // =========================

        JPanel detailsPanel =
                new JPanel();

        detailsPanel.setLayout(
                new BoxLayout(
                        detailsPanel,
                        BoxLayout.Y_AXIS
                )
        );

        detailsPanel.setBackground(Color.WHITE);

        detailsPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 60, 20, 60
                )
        );

        JLabel titleLabel =
                new JLabel("Event Details");

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        detailsPanel.add(titleLabel);

        detailsPanel.add(
                Box.createVerticalStrut(20)
        );

        // Event Name

        JLabel eventNameLabel =
                new JLabel(
                        "Event Name: "
                        + event.getName()
                );

        eventNameLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        eventNameLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        detailsPanel.add(eventNameLabel);

        detailsPanel.add(
                Box.createVerticalStrut(10)
        );

        // Date

        JLabel dateLabel =
                new JLabel(
                        "Date: "
                        + event.getDate()
                );

        dateLabel.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        dateLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        detailsPanel.add(dateLabel);

        detailsPanel.add(
                Box.createVerticalStrut(10)
        );

        // Location

        JLabel locationLabel =
                new JLabel(
                        "Location: "
                        + event.getLocation()
                );

        locationLabel.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        locationLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        detailsPanel.add(locationLabel);

        detailsPanel.add(
                Box.createVerticalStrut(10)
        );

        // Seats

        JLabel seatsLabel =
                new JLabel(
                        "Available Seats: "
                        + event.getAvailableSeats()
                );

        seatsLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        seatsLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        detailsPanel.add(seatsLabel);

        detailsPanel.add(
                Box.createVerticalStrut(20)
        );

        // =========================
        // Buttons
        // =========================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                0
                        )
                );

        buttonPanel.setBackground(Color.WHITE);

        JButton registerButton =
                new JButton("Register");

        registerButton.setBackground(
                new Color(27, 94, 32)
        );

        registerButton.setForeground(Color.WHITE);

        registerButton.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        registerButton.setPreferredSize(
                new Dimension(100, 32)
        );

        registerButton.setFocusPainted(false);

        JButton backButton =
                new JButton("Back");

        backButton.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        backButton.setPreferredSize(
                new Dimension(100, 32)
        );

        backButton.setFocusPainted(false);

        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        detailsPanel.add(buttonPanel);

        // =========================
        // Register Action
        // =========================

        registerButton.addActionListener(e -> {

            if (event.getAvailableSeats() <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Sorry, no seats are available.",
                        "Registration Full",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // Decrease seat
            boolean registered =
                    event.registerStudent();

            if (registered) {

                ConfirmationFile.createConfirmation(
                        event.getName(),
                        event.getDate(),
                        event.getLocation()
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Registration Successful!"
                );

                // Update displayed seats
                seatsLabel.setText(
                        "Available Seats: "
                        + event.getAvailableSeats()
                );

                registerButton.setEnabled(
                        event.getAvailableSeats() > 0
                );
            }
        });

        // =========================
        // Back
        // =========================

        backButton.addActionListener(e -> {

            new AvailableEvents().setVisible(true);
            dispose();

        });

        mainPanel.add(
                detailsPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }
}
