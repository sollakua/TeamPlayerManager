package org.pack1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

public class PlayerUpdateForm extends JFrame {
    private JTextField idField = new JTextField(5);
    private JTextField firstNameField = new JTextField(15);
    private JTextField lastNameField = new JTextField(15);
    private JTextField jerseyNumberField = new JTextField(5);
    private JButton updateButton = new JButton("Ενημέρωση");

    private PlayerDAO playerDAO;

    public PlayerUpdateForm(Connection connection) {
        super("Ενημέρωση Παίκτη");
        this.playerDAO = new PlayerDAO(connection);

        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("ID Παίκτη:"));
        add(idField);

        add(new JLabel("Όνομα:"));
        add(firstNameField);

        add(new JLabel("Επώνυμο:"));
        add(lastNameField);

        add(new JLabel("Αριθμός Φανέλας:"));
        add(jerseyNumberField);

        add(updateButton);

        updateButton.addActionListener(e -> {
            try {
                Player player = new Player(
                    Integer.parseInt(idField.getText()),
                    firstNameField.getText(),
                    lastNameField.getText(),
                    Integer.parseInt(jerseyNumberField.getText())
                );
                playerDAO.updatePlayer(player);
                JOptionPane.showMessageDialog(this, "Ο παίκτης ενημερώθηκε επιτυχώς!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Σφάλμα κατά την ενημέρωση: " + ex.getMessage());
            }
        });

        setSize(400, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
