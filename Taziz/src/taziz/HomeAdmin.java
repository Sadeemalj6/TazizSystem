package taziz;

import javax.swing.*;
import java.awt.*;

public class HomeAdmin extends JFrame {
    public HomeAdmin() {
        setTitle("TAAZIZ - Admin Home");
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

        JButton addEventButton = new JButton("Add an Event");
        styleButton(addEventButton);
        addEventButton.setPreferredSize(new Dimension(220, 45));
        addEventButton.setMaximumSize(new Dimension(220, 45));
        addEventButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(addEventButton);
        contentPanel.add(Box.createVerticalStrut(15));

        JButton logoutButton = new JButton("Logout");
        styleButton(logoutButton);
        logoutButton.setPreferredSize(new Dimension(220, 45));
        logoutButton.setMaximumSize(new Dimension(220, 45));
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(logoutButton);

        addEventButton.addActionListener(e -> {
            new AddEvent().setVisible(true);
            dispose();
        });
        logoutButton.addActionListener(e -> {
            CurrentUser.clear();
            new Login().setVisible(true);
            dispose();
        });

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(27, 94, 32));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomeAdmin().setVisible(true));
    }
}
