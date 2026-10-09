package taziz;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Register extends JFrame {
    private final long eventId;
    private JLabel seatsLabel;
    private JButton registerButton;

    public Register(Event event) {
        this.eventId = event.getId();

        setTitle("TAAZIZ - Event Details");
        setSize(500, 370);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(27, 94, 32));
        topPanel.setPreferredSize(new Dimension(500, 60));
        JLabel logoLabel = new JLabel("TAAZIZ", SwingConstants.CENTER);
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(logoLabel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 15, 40));

        JLabel titleLabel = new JLabel("Event Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(titleLabel);
        detailsPanel.add(Box.createVerticalStrut(15));

        addCenteredLabel(detailsPanel, "Event Name: " + event.getName(), true);
        addCenteredLabel(detailsPanel, "Date: " + event.getDate(), false);
        addCenteredLabel(detailsPanel, "Time: " + event.getTime(), false);
        addCenteredLabel(detailsPanel, "Location: " + event.getLocation(), false);
        seatsLabel = new JLabel("Available Seats: " + event.getAvailableSeats());
        seatsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        seatsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(seatsLabel);
        detailsPanel.add(Box.createVerticalStrut(15));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(Color.WHITE);
        registerButton = new JButton("Register");
        styleButton(registerButton);
        registerButton.setPreferredSize(new Dimension(100, 32));
        registerButton.setEnabled(event.getAvailableSeats() > 0);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 32));
        backButton.setFocusPainted(false);
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);
        detailsPanel.add(buttonPanel);

        registerButton.addActionListener(e -> registerForEvent());
        backButton.addActionListener(e -> {
            new AvailableEvents().setVisible(true);
            dispose();
        });

        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void addCenteredLabel(JPanel panel, String text, boolean bold) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", bold ? Font.BOLD : Font.PLAIN, 14));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createVerticalStrut(8));
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(27, 94, 32));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setFocusPainted(false);
    }

    private void registerForEvent() {
        Account student = CurrentUser.getAccount();
        if (student == null || student.isAdmin()) {
            JOptionPane.showMessageDialog(this, "Please log in with a student account.");
            return;
        }

        try {
            Event latest = EventFile.getEvent(eventId);
            if (latest == null) {
                JOptionPane.showMessageDialog(this, "This event could not be found.");
                return;
            }
            if (latest.getAvailableSeats() <= 0) {
                seatsLabel.setText("Available Seats: 0");
                registerButton.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Sorry, no seats are available.");
                return;
            }

            if (!EventFile.reserveSeat(eventId)) {
                JOptionPane.showMessageDialog(this, "Sorry, no seats are available.");
                refreshSeats();
                return;
            }

            Event updated = EventFile.getEvent(eventId);
            ConfirmationFile.createConfirmation(student, updated);

            refreshSeats();
            JOptionPane.showMessageDialog(this, "Registration Successful!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not process registration: " + ex.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshSeats() throws IOException {
        Event latest = EventFile.getEvent(eventId);
        if (latest != null) {
            seatsLabel.setText("Available Seats: " + latest.getAvailableSeats());
            registerButton.setEnabled(latest.getAvailableSeats() > 0);
        }
    }
}
