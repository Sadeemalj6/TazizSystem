package taziz;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Login extends JFrame {
    private JTextField studentIDField;
    private JPasswordField passwordField;

    public Login() {
        setTitle("TAAZIZ - Login");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(27, 94, 32));
        topPanel.setPreferredSize(new Dimension(500, 60));
        JLabel logoLabel = new JLabel("TAAZIZ");
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(logoLabel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(25, 60, 20, 60));

        JLabel titleLabel = new JLabel("University Events Registration System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(titleLabel);
        formPanel.add(Box.createVerticalStrut(25));

        JPanel studentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        studentPanel.setBackground(Color.WHITE);
        studentPanel.setMaximumSize(new Dimension(400, 30));
        studentPanel.add(new JLabel("ID:"));
        studentIDField = new JTextField();
        studentIDField.setPreferredSize(new Dimension(220, 30));
        studentPanel.add(studentIDField);
        formPanel.add(studentPanel);
        formPanel.add(Box.createVerticalStrut(5));

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        passwordPanel.setBackground(Color.WHITE);
        passwordPanel.setMaximumSize(new Dimension(400, 30));
        passwordPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(220, 30));
        passwordPanel.add(passwordField);
        formPanel.add(passwordPanel);
        formPanel.add(Box.createVerticalStrut(18));

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(27, 94, 32));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 13));
        loginButton.setPreferredSize(new Dimension(100, 32));
        loginButton.setMaximumSize(new Dimension(100, 32));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(loginButton);

        loginButton.addActionListener(e -> login());
        passwordField.addActionListener(e -> login());

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void login() {
        String id = studentIDField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (id.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter ID and Password.");
            return;
        }

        try {
            Account account = AccountFile.authenticate(id, password);
            if (account == null) {
                JOptionPane.showMessageDialog(this, "Invalid ID or password.",
                        "Login Failed", JOptionPane.ERROR_MESSAGE);
                return;
            }

            CurrentUser.setAccount(account);
            if (account.isAdmin()) {
                new HomeAdmin().setVisible(true);
            } else {
                new Home().setVisible(true);
            }
            dispose();
        } catch (IOException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                    "Could not read account data: " + ex.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
