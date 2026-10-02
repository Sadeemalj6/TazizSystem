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

public class AvailableEvents extends JFrame {

    private JPanel eventsPanel;

    public AvailableEvents() {

        setTitle("Taaziz University Events Registration System");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(Color.WHITE);

        // =========================
        // Header
        // =========================

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(
                new Color(27, 94, 32)
        );

        header.setPreferredSize(
                new Dimension(500, 55)
        );

        JLabel title =
                new JLabel("Available Events");

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        title.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        header.add(title);

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        // =========================
        // Events Panel
        // =========================

        eventsPanel = new JPanel();

        eventsPanel.setLayout(
                new BoxLayout(
                        eventsPanel,
                        BoxLayout.Y_AXIS
                )
        );

        eventsPanel.setBackground(Color.WHITE);

        eventsPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 40, 10, 40
                )
        );

        loadEvents();

        JScrollPane scrollPane =
                new JScrollPane(eventsPanel);

        scrollPane.setBorder(null);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =========================
        // Back
        // =========================

        JButton backButton =
                new JButton("Back");

        backButton.setBackground(
                new Color(27, 94, 32)
        );

        backButton.setForeground(Color.WHITE);

        backButton.setFocusPainted(false);

        backButton.setPreferredSize(
                new Dimension(100, 35)
        );

        backButton.addActionListener(e -> {

            new Home().setVisible(true);
            dispose();

        });

        JPanel bottomPanel =
                new JPanel();

        bottomPanel.setBackground(Color.WHITE);

        bottomPanel.add(backButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    // =========================
    // Load Events
    // =========================

    private void loadEvents() {

        eventsPanel.removeAll();

        if (EventManager.getEvents().isEmpty()) {

            JLabel noEventsLabel =
                    new JLabel("No events available.");

            noEventsLabel.setFont(
                    new Font("Arial", Font.BOLD, 16)
            );

            noEventsLabel.setAlignmentX(
                    Component.CENTER_ALIGNMENT
            );

            eventsPanel.add(noEventsLabel);

        } else {

            for (int i = 0;
                    i < EventManager.getEvents().size();
                    i++) {

                Event event =
                        EventManager.getEvent(i);

                JButton eventButton =
                        new JButton(
                                event.getName()
                                + "  |  Seats: "
                                + event.getAvailableSeats()
                );

                eventButton.setFont(
                        new Font("Arial", Font.BOLD, 14)
                );

                eventButton.setMaximumSize(
                        new Dimension(400, 45)
                );

                eventButton.setAlignmentX(
                        Component.CENTER_ALIGNMENT
                );

                final int eventIndex = i;

                eventButton.addActionListener(e -> {

                    Event selectedEvent =
                            EventManager.getEvent(eventIndex);

                    new Register(selectedEvent)
                            .setVisible(true);

                    dispose();
                });

                eventsPanel.add(eventButton);

                eventsPanel.add(
                        Box.createVerticalStrut(10)
                );
            }
        }

        eventsPanel.revalidate();
        eventsPanel.repaint();
    }

    public static void main(String[] args) {

        new AvailableEvents().setVisible(true);
    }
}
