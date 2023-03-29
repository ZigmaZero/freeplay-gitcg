package logic.game;

import java.util.ArrayList;

import logic.card.ActionCard;
import logic.card.CharacterCard;

public class Deck {
	private String deckName;
	private ArrayList<CharacterCard> characterCards;
	private ArrayList<ActionCard> actionCards;
	
	public String getDeckName() {
		return deckName;
	}


	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}


	public ArrayList<CharacterCard> getCharacterCards() {
		return characterCards;
	}


	public void setCharacterCards(ArrayList<CharacterCard> characterCards) {
		this.characterCards = characterCards;
	}


	public ArrayList<ActionCard> getActionCards() {
		return actionCards;
	}


	public void setActionCards(ArrayList<ActionCard> actionCards) {
		this.actionCards = actionCards;
	}


	public Deck()
	{
		this.setDeckName("My Deck");
		this.setCharacterCards(new ArrayList<CharacterCard>());
		this.setActionCards(new ArrayList<ActionCard>());
	}
	
}
