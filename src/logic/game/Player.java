package logic.game;

import java.io.Serializable;
import java.util.ArrayList;

import logic.card.ActionCard;
import logic.card.CharacterCard;
import logic.enums.DiceType;

public class Player {
	private Deck playerDeck;
	private ArrayList<ActionCard> playerHand;
	private CharacterCard activeCharacter;
	private ArrayList<DiceType> playerDice;

	public Player(Deck playerDeck) {
		super();
		setPlayerDeck(playerDeck);
		playerHand = new ArrayList<>();
	}
	
	public void draw(int amount) {
		for (int i = 0; i < amount; i++) {
			if (getPlayerDeck().getActionCards().isEmpty()) {
				break;
			}
			getPlayerHand().add(getPlayerDeck().getActionCards().get(0));
			getPlayerDeck().getActionCards().remove(0);
		}
	}
	
	public Deck getPlayerDeck() {
		return playerDeck;
	}

	public void setPlayerDeck(Deck deck) {
		this.playerDeck = deck;
	}

	public ArrayList<ActionCard> getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(ArrayList<ActionCard> playerHand) {
		this.playerHand = playerHand;
	}

	public CharacterCard getActiveCharacter() {
		return activeCharacter;
	}

	public void setActiveCharacter(CharacterCard activeCharacter) {
		this.activeCharacter = activeCharacter;
	}

	public ArrayList<DiceType> getPlayerDice() {
		return playerDice;
	}

	public void setPlayerDice(ArrayList<DiceType> playerDice) {
		this.playerDice = playerDice;
	}
	
}
