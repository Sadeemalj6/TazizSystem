/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taziz;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {

    public Home() {

        setTitle("Frame 2 - Home");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // =========================
        // Top Bar
        // =========================

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(27, 94, 32));
        topPanel.setPreferredSize(new Dimension(500, 60));

        JLabel logoLabel = new JLabel("TAAZIZ");
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        topPanel.add(logoLabel);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // =========================
        // Content
        // =========================

        JPanel contentPanel = new JPanel();

        contentPanel.setLayout(new BoxLayout(
                contentPanel,
                BoxLayout.Y_AXIS
        ));

        contentPanel.setBackground(Color.WHITE);

        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(35, 60, 20, 60)
        );

        JLabel welcomeLabel =
                new JLabel("Welcome to Taaziz");

        welcomeLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        welcomeLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        contentPanel.add(welcomeLabel);

        contentPanel.add(
                Box.createVerticalStrut(30)
        );

        // Register for Event

        JButton registerButton =
                new JButton("Register for Event");

        registerButton.setBackground(
                new Color(27, 94, 32)
        );

        registerButton.setForeground(Color.WHITE);

        registerButton.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        registerButton.setFocusPainted(false);

        registerButton.setPreferredSize(
                new Dimension(220, 45)
        );

        registerButton.setMaximumSize(
                new Dimension(220, 45)
        );

        registerButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        contentPanel.add(registerButton);

        contentPanel.add(
                Box.createVerticalStrut(15)
        );

        // Logout

        JButton logoutButton =
                new JButton("Logout");

        logoutButton.setBackground(
                new Color(27, 94, 32)
        );

        logoutButton.setForeground(Color.WHITE);

        logoutButton.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        logoutButton.setPreferredSize(
                new Dimension(220, 45)
        );

        logoutButton.setMaximumSize(
                new Dimension(220, 45)
        );

        logoutButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        contentPanel.add(logoutButton);

        // =========================
        // Actions
        // =========================

        registerButton.addActionListener(e -> {

            new AvailableEvents().setVisible(true);
            dispose();

        });

        logoutButton.addActionListener(e -> {

            new Login().setVisible(true);
            dispose();

        });

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    public static void main(String[] args) {

        new Home().setVisible(true);
    }
}
