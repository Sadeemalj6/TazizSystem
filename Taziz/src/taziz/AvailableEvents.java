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

    public AvailableEvents() {

        setTitle("Taaziz University Events Registration System");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 128, 60));
        header.setBounds(0, 0, 500, 55);

        JLabel title = new JLabel("Available Events");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        header.add(title);
        panel.add(header);

        // Event 1
        JButton event1 = new JButton("AI Workshop");
        event1.setBounds(50, 80, 400, 45);
        event1.setHorizontalAlignment(SwingConstants.CENTER);
        event1.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(event1);

        // Event 2
        JButton event2 = new JButton("Cybersecurity Seminar");
        event2.setBounds(50, 130, 400, 45);
        event2.setHorizontalAlignment(SwingConstants.CENTER);
        event2.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(event2);

        // Event 3
        JButton event3 = new JButton("Robotics Competition");
        event3.setBounds(50, 180, 400, 45);
        event3.setHorizontalAlignment(SwingConstants.CENTER);
        event3.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(event3);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(190, 240, 120, 35);
        backButton.setBackground(new Color(0, 128, 60));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        panel.add(backButton);

        // Event button actions
        event1.addActionListener(e -> {
            System.out.println("AI Workshop selected");
            // Later: open View Event Details
        });

        event2.addActionListener(e -> {
            System.out.println("Cybersecurity Seminar selected");
            // Later: open View Event Details
        });

        event3.addActionListener(e -> {
            System.out.println("Robotics Competition selected");
            // Later: open View Event Details
        });

        // Back action
        backButton.addActionListener(e -> {
            dispose();
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AvailableEvents().setVisible(true);
        });
    }
}