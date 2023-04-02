package logic.game;

import java.util.Collections;

public class GameInstance {
		
	private Player player;
	private Player opponent;
	private Player currentPlayer;
	
	public GameInstance(Player player, Player opponent, Player currentPlayer) {
		super();
		this.player = player;
		this.opponent = opponent;
		this.currentPlayer = currentPlayer;
	}
	
	public void initGame() {
		Collections.shuffle(player.getPlayerDeck().getActionCards());
		Collections.shuffle(opponent.getPlayerDeck().getActionCards());
		player.draw(5);
		opponent.draw(5);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getOpponent() {
		return opponent;
	}

	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
}
