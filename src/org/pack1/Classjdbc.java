package org.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Classjdbc {
    private static final String URL = "jdbc:mysql://localhost:3306/team_database";
    private static final String USER = "ardit";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection con = getConnection()) {
            System.out.println("Connection created");
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }
}
