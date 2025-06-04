package org.pack1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

public class MainMenu extends JFrame {

    public MainMenu(Connection connection) {
        super("Διαχείριση Παικτών - Κεντρικό Μενού");
        setLayout(new GridLayout(5, 1, 15, 15));
        getContentPane().setBackground(new Color(240, 240, 240));

        // Κουμπιά με εικονίδια
        JButton insertButton = createStyledButton("Εισαγωγή Παίκτη", new Color(40, 167, 69), "");
        JButton searchButton = createStyledButton("Αναζήτηση Παίκτη", new Color(0, 123, 255), "");
        JButton updateButton = createStyledButton("Ενημέρωση Παίκτη", new Color(255, 193, 7), "");
        JButton deleteButton = createStyledButton("Διαγραφή Παίκτη", new Color(220, 53, 69), "");
        JButton exitButton = createStyledButton("Έξοδος", new Color(108, 117, 125), "");

        // Event listeners
        insertButton.addActionListener(e -> new PlayerInsertForm(connection).setVisible(true));
        searchButton.addActionListener(e -> new PlayerSearchForm(connection).setVisible(true));
        updateButton.addActionListener(e -> new PlayerUpdateForm(connection).setVisible(true));
        deleteButton.addActionListener(e -> new PlayerDeleteForm(connection).setVisible(true));
        exitButton.addActionListener(e -> System.exit(0));

        // Προσθήκη κουμπιών
        add(insertButton);
        add(searchButton);
        add(updateButton);
        add(deleteButton);
        add(exitButton);

        // Ρυθμίσεις παραθύρου
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Δημιουργεί κουμπί με εικονίδιο και στυλ
    private JButton createStyledButton(String text, Color bgColor, String iconText) {
        JButton button = new JButton(iconText + "  " + text);
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        return button;
    }

    public static void main(String[] args) {
        try {
            Connection conn = Classjdbc.getConnection(); // Σύνδεση με τη βάση
            new MainMenu(conn).setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Αποτυχία σύνδεσης με τη βάση: " + e.getMessage());
        }
    }
}
