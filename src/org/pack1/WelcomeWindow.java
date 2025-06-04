package org.pack1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

public class WelcomeWindow extends JFrame {
    private Connection connection;

    public WelcomeWindow(Connection connection) {
        super("Καλωσόρισμα");
        this.connection = connection;

        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));

        JLabel welcomeLabel = new JLabel("Καλωσήρθατε στη Διαχείριση Παικτών!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 3, 15, 15));
        buttonsPanel.setBackground(new Color(240, 240, 240));

        JButton loginButton = createStyledButton("Είσοδος", new Color(40, 167, 69));
        JButton logoutButton = createStyledButton("Αποσύνδεση", new Color(220, 53, 69));
        JButton exitButton = createStyledButton("Έξοδος", new Color(108, 117, 125));

        buttonsPanel.add(loginButton);
        buttonsPanel.add(logoutButton);
        buttonsPanel.add(exitButton);

        add(buttonsPanel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> {
            new PlayerManagementWindow(connection).setVisible(true);
            this.dispose();
        });

        logoutButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(this, "Δεν είστε συνδεδεμένοι.");
        });

        exitButton.addActionListener(e -> System.exit(0));

        setSize(500, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static void main(String[] args) {
        try {
            Connection conn = Classjdbc.getConnection();
            new WelcomeWindow(conn).setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Αποτυχία σύνδεσης με τη βάση: " + e.getMessage());
        }
    }
}

class PlayerManagementWindow extends JFrame {
    private Connection connection;

    public PlayerManagementWindow(Connection connection) {
        super("Διαχείριση Παικτών");
        this.connection = connection;

        setLayout(new GridLayout(3, 2, 15, 15));
        getContentPane().setBackground(new Color(240, 240, 240));
        JButton insertButton = createStyledButton("Εισαγωγή Παίκτη", new Color(40, 167, 69));
        JButton searchButton = createStyledButton("Αναζήτηση Παίκτη", new Color(0, 123, 255));
        JButton deleteButton = createStyledButton("Διαγραφή Παίκτη", new Color(220, 53, 69));
        JButton updateButton = createStyledButton("Ενημέρωση Παίκτη", new Color(255, 193, 7));
        JButton logoutButton = createStyledButton("Αποσύνδεση", new Color(220, 53, 69));
        JButton exitSaveButton = createStyledButton("Έξοδος και Αποθήκευση", new Color(108, 117, 125));

        add(insertButton);
        add(searchButton);
        add(deleteButton);
        add(updateButton);
        add(logoutButton);
        add(exitSaveButton);
        add(new JLabel());

        insertButton.addActionListener(e -> new PlayerInsertForm(connection).setVisible(true));
        searchButton.addActionListener(e -> new PlayerSearchForm(connection).setVisible(true));
        deleteButton.addActionListener(e -> new PlayerDeleteForm(connection).setVisible(true));
        updateButton.addActionListener(e -> new PlayerUpdateForm(connection).setVisible(true));


        logoutButton.addActionListener(e -> {
            new WelcomeWindow(connection).setVisible(true);
            this.dispose();
        });

        exitSaveButton.addActionListener(e -> {
            
            JOptionPane.showMessageDialog(this, "Οι αλλαγές αποθηκεύτηκαν.");
            System.exit(0);
        });

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
