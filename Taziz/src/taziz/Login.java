package taziz;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    private JTextField studentIDField;
    private JPasswordField passwordField;

    public Login() {

        setTitle("Frame 1 - Login");
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
        // Form
        // =========================

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 60, 20, 60)
        );

        // Title

        JLabel titleLabel =
                new JLabel("University Events Registration System");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.add(titleLabel);

        formPanel.add(Box.createVerticalStrut(25));

        // =========================
        // Student ID
        // =========================

        JPanel studentPanel =
                new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));

        studentPanel.setBackground(Color.WHITE);
        studentPanel.setMaximumSize(new Dimension(400, 30));

        JLabel studentIDLabel = new JLabel("Student ID:");
        studentIDLabel.setFont(new Font("Arial", Font.BOLD, 13));

        studentIDField = new JTextField();
        studentIDField.setPreferredSize(new Dimension(220, 30));

        studentPanel.add(studentIDLabel);
        studentPanel.add(studentIDField);

        formPanel.add(studentPanel);

        formPanel.add(Box.createVerticalStrut(5));

        // =========================
        // Password
        // =========================

        JPanel passwordPanel =
                new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));

        passwordPanel.setBackground(Color.WHITE);
        passwordPanel.setMaximumSize(new Dimension(400, 30));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 13));

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(220, 30));

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        formPanel.add(passwordPanel);

        formPanel.add(Box.createVerticalStrut(18));

        // =========================
        // Login Button
        // =========================

        JButton loginButton = new JButton("Login");

        loginButton.setBackground(new Color(27, 94, 32));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 13));

        loginButton.setPreferredSize(new Dimension(100, 32));
        loginButton.setMaximumSize(new Dimension(100, 32));

        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.add(loginButton);

        // =========================
        // Login Action
        // =========================

        loginButton.addActionListener(e -> {

            String id = studentIDField.getText().trim();
            String password =
                    new String(passwordField.getPassword());

            if (id.isEmpty() || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter Student ID and Password."
                );

                return;
            }

            // Admin Login
            if (id.equals("admin") && password.equals("admin123")) {

                new HomeAdmin().setVisible(true);
                dispose();

            } else {

                // Student Login
                new Home().setVisible(true);
                dispose();
            }
        });

        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    public static void main(String[] args) {

        new Login().setVisible(true);
    }
}
