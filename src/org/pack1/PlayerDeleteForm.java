package org.pack1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

public class PlayerDeleteForm extends JFrame {
    private JTextField idField = new JTextField(5);
    private JButton deleteButton = new JButton("Διαγραφή");

    private PlayerDAO playerDAO;

    public PlayerDeleteForm(Connection connection) {
        super("Διαγραφή Παίκτη");
        this.playerDAO = new PlayerDAO(connection);

        setLayout(new GridLayout(2, 2, 10, 10));

        add(new JLabel("ID Παίκτη προς διαγραφή:"));
        add(idField);
        add(deleteButton);

        deleteButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                playerDAO.deletePlayer(id);
                JOptionPane.showMessageDialog(this, "Ο παίκτης διαγράφηκε επιτυχώς!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Σφάλμα κατά τη διαγραφή: " + ex.getMessage());
            }
        });

        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
