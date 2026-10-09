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
import java.io.IOException;
import java.util.List;

public class AvailableEvents extends JFrame {
    private final JPanel eventsPanel = new JPanel();

    public AvailableEvents() {
        setTitle("Taaziz - Available Events");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(27, 94, 32));
        header.setPreferredSize(new Dimension(500, 55));
        JLabel title = new JLabel("Available Events", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        header.add(title);
        mainPanel.add(header, BorderLayout.NORTH);

        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
        eventsPanel.setBackground(Color.WHITE);
        eventsPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 10, 25));

        JScrollPane scrollPane = new JScrollPane(eventsPanel);
        scrollPane.setBorder(null);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(27, 94, 32));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(100, 35));
        backButton.addActionListener(e -> {
            new Home().setVisible(true);
            dispose();
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(backButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        loadEvents();
    }

    private void loadEvents() {
        eventsPanel.removeAll();
        try {
            List<Event> events = EventFile.getAllEvents();
            if (events.isEmpty()) {
                JLabel noEvents = new JLabel("No events available.");
                noEvents.setAlignmentX(Component.CENTER_ALIGNMENT);
                eventsPanel.add(noEvents);
            } else {
                for (Event event : events) {
                    String buttonText = event.getName() + " | Seats: "
                            + event.getAvailableSeats() + " | " + event.getDate();
                    JButton eventButton = new JButton(buttonText);
                    eventButton.setFont(new Font("Arial", Font.BOLD, 13));
                    eventButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                    eventButton.setMaximumSize(new Dimension(420, 45));
                    eventButton.addActionListener(e -> {
                        try {
                            Event latestEvent = EventFile.getEvent(event.getId());
                            if (latestEvent == null) {
                                JOptionPane.showMessageDialog(this, "This event no longer exists.");
                                loadEvents();
                            } else {
                                new Register(latestEvent).setVisible(true);
                                dispose();
                            }
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(this, "Could not read event file: " + ex.getMessage());
                        }
                    });
                    eventsPanel.add(eventButton);
                    eventsPanel.add(Box.createVerticalStrut(10));
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not read events: " + ex.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
        eventsPanel.revalidate();
        eventsPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AvailableEvents().setVisible(true));
    }
}
