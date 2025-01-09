package dao;

import database.DatabaseConnection;
import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {

    // CREATE: Tambahkan pemain baru
    public void addPlayer(String name, String email, float balance) {
        String sql = "INSERT INTO players (name, email, balance) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setFloat(3, balance);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ: Ambil semua pemain
    public List<Player> getAllPlayers() {
        String sql = "SELECT * FROM players";
        List<Player> players = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                float balance = rs.getFloat("balance");

                // Membuat objek Player dan menambahkannya ke daftar
                Player player = new Player(id, name, email, balance);
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    // UPDATE: Perbarui data pemain berdasarkan ID
    public void updatePlayer(int id, String name, String email, float balance) {
        String sql = "UPDATE players SET name = ?, email = ?, balance = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setFloat(3, balance);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE: Perbarui balance pemain berdasarkan ID
    public void updatePlayerBalance(int playerId, float newBalance) {
        String sql = "UPDATE players SET balance = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setFloat(1, newBalance);
            stmt.setInt(2, playerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE: Hapus pemain berdasarkan ID
    public void deletePlayer(int id) {
        String sql = "DELETE FROM players WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
