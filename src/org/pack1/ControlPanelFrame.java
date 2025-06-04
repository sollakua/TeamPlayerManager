package org.pack1;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class ControlPanelFrame extends JFrame {
    public ControlPanelFrame(Connection connection) {
        super("Πίνακας Ελέγχου");

        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton searchButton = new JButton("Αναζήτηση");
        JButton insertButton = new JButton("Εγγραφή");
        JButton deleteButton = new JButton("Διαγραφή");
        JButton closeButton = new JButton("Κλείσιμο");

        
        JButton[] buttons = { searchButton, insertButton, deleteButton, closeButton };
        for (JButton btn : buttons) {
            UIUtils.styleButton(btn, new Color(52, 152, 219));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(200, 35));
            panel.add(btn);
            panel.add(Box.createVerticalStrut(15));
        }


        searchButton.addActionListener(e -> new PlayerSearchForm(connection).setVisible(true));
        insertButton.addActionListener(e -> new PlayerInsertForm(connection).setVisible(true));
        deleteButton.addActionListener(e -> new PlayerDeleteForm(connection).setVisible(true));
        closeButton.addActionListener(e -> this.dispose());

        add(panel);

        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
