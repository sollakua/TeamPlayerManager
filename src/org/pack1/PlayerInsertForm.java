package org.pack1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;

public class PlayerInsertForm extends JFrame {
    private JTextField firstNameField = new JTextField(15);
    private JTextField lastNameField = new JTextField(15);
    private JTextField jerseyNumberField = new JTextField(5);
    private JButton addButton = new JButton("Προσθήκη");

    private PlayerDAO playerDAO;

    public PlayerInsertForm(Connection connection) {
        super("Εισαγωγή Νέου Παίκτη");
        this.playerDAO = new PlayerDAO(connection);

        setLayout(new FlowLayout());

        add(new JLabel("Όνομα:"));
        add(firstNameField);

        add(new JLabel("Επώνυμο:"));
        add(lastNameField);

        add(new JLabel("Αριθμός Φανέλας:"));
        add(jerseyNumberField);

        add(addButton);

        addButton.addActionListener(e -> {
            try {
                String firstName = firstNameField.getText().trim();
                String lastName = lastNameField.getText().trim();
                int jerseyNumber = Integer.parseInt(jerseyNumberField.getText().trim());

                Player player = new Player(0, firstName, lastName, jerseyNumber);
                playerDAO.insertPlayer(player);

                JOptionPane.showMessageDialog(this, "Ο παίκτης προστέθηκε επιτυχώς!");
                
               
                firstNameField.setText("");
                lastNameField.setText("");
                jerseyNumberField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ εισάγετε έγκυρο αριθμό φανέλας.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Σφάλμα κατά την εισαγωγή στη βάση: " + ex.getMessage(), "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            }
        });

        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
