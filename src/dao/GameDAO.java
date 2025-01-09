package dao;

import database.DatabaseConnection;
import java.sql.*;

public class GameDAO {
    public void addGame(String name, String category, String description) {
        String sql = "INSERT INTO games (name, category, description) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, category);
            stmt.setString(3, description);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
