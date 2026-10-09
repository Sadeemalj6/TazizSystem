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

public class AddEvent extends JFrame {
    private JTextField eventNameField, dateField, timeField, locationField, seatsField;

    public AddEvent() {
        setTitle("Taaziz - Add Event");
        setSize(500, 440);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        JPanel header = new JPanel();
        header.setBackground(new Color(27, 94, 32));
        header.setBounds(0, 0, 500, 55);
        JLabel title = new JLabel("Add Event");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        header.add(title);
        panel.add(header);

        eventNameField = addField(panel, "Event Name:", 70);
        dateField = addField(panel, "Date:", 115);
        timeField = addField(panel, "Time:", 160);
        locationField = addField(panel, "Location:", 205);
        seatsField = addField(panel, "Seats:", 250);

        JButton addButton = new JButton("Add Event");
        styleButton(addButton);
        addButton.setBounds(300, 325, 120, 35);
        panel.add(addButton);

        JButton backButton = new JButton("Back");
        styleButton(backButton);
        backButton.setBounds(190, 325, 90, 35);
        panel.add(backButton);

        addButton.addActionListener(e -> addEvent());
        backButton.addActionListener(e -> {
            new HomeAdmin().setVisible(true);
            dispose();
        });

        add(panel);
    }

    private JTextField addField(JPanel panel, String labelText, int y) {
        JLabel label = new JLabel(labelText);
        label.setBounds(50, y, 120, 35);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label);

        JTextField field = new JTextField();
        field.setBounds(180, y, 275, 35);
        panel.add(field);
        return field;
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(27, 94, 32));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
    }

    
private void addEvent() {
    String name = eventNameField.getText().trim();
    String date = dateField.getText().trim();
    String time = timeField.getText().trim();
    String location = locationField.getText().trim();
    String seatsText = seatsField.getText().trim();

    if (name.isEmpty() || date.isEmpty()
            || time.isEmpty() || location.isEmpty()
            || seatsText.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Please fill in all fields.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    try {
        int seats = Integer.parseInt(seatsText);

        if (seats <= 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seats must be greater than 0."
            );
            return;
        }

        String adminName = CurrentUser.getName();

        EventFile.addEvent(
                name, date, time, location,
                seats, adminName
        );

        JOptionPane.showMessageDialog(
                this,
                "Event added successfully!"
        );

        eventNameField.setText("");
        dateField.setText("");
        timeField.setText("");
        locationField.setText("");
        seatsField.setText("");

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(
                this,
                "Seats must be a whole number.",
                "Invalid Seats",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (java.io.IOException | IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(
                this,
                "Could not save event: " + ex.getMessage(),
                "File Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddEvent().setVisible(true));
    }
}
