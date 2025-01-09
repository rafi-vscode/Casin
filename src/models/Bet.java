package models;

public class Bet {
    private int id;
    private int playerId;
    private int gameId;
    private float betAmount;
    private String outcome;
    private float balanceAfter;

    public Bet(int id, int playerId, int gameId, float betAmount, String outcome, float balanceAfter) {
        this.id = id;
        this.playerId = playerId;
        this.gameId = gameId;
        this.betAmount = betAmount;
        this.outcome = outcome;
        this.balanceAfter = balanceAfter;
    }

    // Getter and Setter Methods
}
