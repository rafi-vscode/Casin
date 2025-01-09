package dao;

import database.DatabaseConnection;
import java.sql.*;

public class BetDAO {
    public void addBet(int playerId, int gameId, float betAmount, String outcome, float balanceAfter) {
        String sql = "INSERT INTO bets (player_id, game_id, bet_amount, outcome, balance_after) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, playerId);
            stmt.setInt(2, gameId);
            stmt.setFloat(3, betAmount);
            stmt.setString(4, outcome);
            stmt.setFloat(5, balanceAfter);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

