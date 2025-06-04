package org.pack1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlayerSearchForm extends JFrame {
    private JTextField lastNameField = new JTextField(15);
    private JButton searchButton = new JButton("Αναζήτηση");
    private JTextArea resultArea = new JTextArea(10, 30);

    private PlayerDAO playerDAO;

    public PlayerSearchForm(Connection connection) {
        super("Αναζήτηση Παίκτη");
        this.playerDAO = new PlayerDAO(connection);

        setLayout(new FlowLayout());

        add(new JLabel("Επώνυμο:"));
        add(lastNameField);
        add(searchButton);

        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        searchButton.addActionListener(e -> {
            String lastName = lastNameField.getText().trim();
            try {
                List<Player> players = playerDAO.searchByLastName(lastName);
                resultArea.setText("");

                if (players.isEmpty()) {
                    resultArea.append("Δεν βρέθηκαν παίκτες με αυτό το επώνυμο.\n");
                } else {
                    for (Player p : players) {
                        resultArea.append("ID: " + p.getId() + ", Όνομα: " + p.getFirstName() +
                                ", Επώνυμο: " + p.getLastName() +
                                ", Αριθμός Φανέλας: " + p.getJerseyNumber() + "\n");
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Σφάλμα κατά την αναζήτηση: " + ex.getMessage(), "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            }
        });

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
