package org.pack1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private Connection connection;

    public PlayerDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertPlayer(Player player) throws SQLException {
        String sql = "INSERT INTO players (first_name, last_name, jersey_number) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, player.getFirstName());
            stmt.setString(2, player.getLastName());
            stmt.setInt(3, player.getJerseyNumber());
            stmt.executeUpdate();
        }
    }

    public List<Player> searchByLastName(String lastName) throws SQLException {
        String sql = "SELECT * FROM players WHERE last_name = ?";
        List<Player> players = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, lastName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Player p = new Player(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("jersey_number"));
                    players.add(p);
                }
            }
        }
        return players;
    }
    
    public void updatePlayer(Player player) throws SQLException {
        String sql = "UPDATE players SET first_name = ?, last_name = ?, jersey_number = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, player.getFirstName());
            stmt.setString(2, player.getLastName());
            stmt.setInt(3, player.getJerseyNumber());
            stmt.setInt(4, player.getId());
            stmt.executeUpdate();
        }
    }

    public void deletePlayer(int id) throws SQLException {
        String sql = "DELETE FROM players WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
