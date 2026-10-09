/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taziz;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    public Home() {
        setTitle("TAAZIZ - Student Home");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(35, 60, 20, 60));

        JLabel welcomeLabel = new JLabel("Welcome, " + CurrentUser.getName());
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(welcomeLabel);
        contentPanel.add(Box.createVerticalStrut(30));

        JButton registerButton = new JButton("Register for Event");
        styleButton(registerButton);
        registerButton.setPreferredSize(new Dimension(220, 45));
        registerButton.setMaximumSize(new Dimension(220, 45));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(registerButton);
        contentPanel.add(Box.createVerticalStrut(15));

        JButton logoutButton = new JButton("Logout");
        styleButton(logoutButton);
        logoutButton.setPreferredSize(new Dimension(220, 45));
        logoutButton.setMaximumSize(new Dimension(220, 45));
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(logoutButton);

        registerButton.addActionListener(e -> {
            new AvailableEvents().setVisible(true);
            dispose();
        });
        logoutButton.addActionListener(e -> logout());

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(27, 94, 32));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
    }

    private void logout() {
        CurrentUser.clear();
        new Login().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Home().setVisible(true));
    }
}
