package org.pack1;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class WelcomeFrame extends JFrame {
    public WelcomeFrame(Connection connection) {
        super("Καλωσορίσατε στο Σύστημα");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel welcomeLabel = new JLabel("Καλωσορίσατε στο Σύστημα Διαχείρισης Ομάδας", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton loginButton = new JButton("Login");
        JButton logoutButton = new JButton("Logout");

        UIUtils.styleButton(loginButton, new Color(40, 167, 69));
        UIUtils.styleButton(logoutButton, new Color(220, 53, 69));

        loginButton.setMaximumSize(new Dimension(150, 35));
        logoutButton.setMaximumSize(new Dimension(150, 35));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(welcomeLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(loginButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(logoutButton);

        loginButton.addActionListener(e -> new ControlPanelFrame(connection).setVisible(true));
        logoutButton.addActionListener(e -> System.exit(0));

        add(panel);
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
