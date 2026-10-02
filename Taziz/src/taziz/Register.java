package taziz;


import javax.swing.*;
import java.awt.*;

public class Register extends JFrame {

    public Register() {

        // Window
        setTitle("Frame 3 - Event Details");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // =========================
        // Main Panel
        // =========================
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // =========================
        // Top Green Bar
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
        // Event Details
        // =========================
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);

        detailsPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 60, 20, 60)
        );

        // Title
        JLabel titleLabel = new JLabel("Event Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        detailsPanel.add(titleLabel);
        detailsPanel.add(Box.createVerticalStrut(20));

        // Event Name
        JLabel eventNameLabel = new JLabel("Event Name: University Innovation Day");
        eventNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        eventNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        detailsPanel.add(eventNameLabel);
        detailsPanel.add(Box.createVerticalStrut(10));

        // Date
        JLabel dateLabel = new JLabel("Date: October 10, 2026");
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        detailsPanel.add(dateLabel);
        detailsPanel.add(Box.createVerticalStrut(10));

        // Location
        JLabel locationLabel = new JLabel("Location: King Abdulaziz University");
        locationLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        locationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        detailsPanel.add(locationLabel);
        detailsPanel.add(Box.createVerticalStrut(25));

        // =========================
        // Buttons
        // =========================
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 15, 0)
        );

        buttonPanel.setBackground(Color.WHITE);
        JButton registerButton = new JButton("Register") {

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(new Color(27, 94, 32));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

                super.paintComponent(g2);
                g2.dispose();
            }
        };

        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 13));
        registerButton.setPreferredSize(new Dimension(100, 32));
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setFocusPainted(false);

       JButton backButton = new JButton("Back");

        backButton.setFont(new Font("Arial", Font.BOLD, 13));

        backButton.setPreferredSize(new Dimension(100, 32));
        backButton.setMinimumSize(new Dimension(100, 32));
        backButton.setMaximumSize(new Dimension(100, 32));

        backButton.setFocusPainted(false);
        
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        detailsPanel.add(buttonPanel);

        // =========================
        // Button Actions
        // =========================

        // Register button
        registerButton.addActionListener(e -> {
             ConfirmationFile.createConfirmation(
            "University Innovation Day",
            "October 10, 2026",
            "King Abdulaziz University"
    );
            JOptionPane.showMessageDialog(
                    this,
                    "Registration Successful!"
            );
        });

        // Back button
        backButton.addActionListener(e -> {
            dispose();
        });

        // =========================
        // Add to Window
        // =========================
        mainPanel.add(detailsPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    // =========================
    // Main Method
    // =========================
    public static void main(String[] args) {

        Register registerFrame = new Register();
        registerFrame.setVisible(true);
    }
}
