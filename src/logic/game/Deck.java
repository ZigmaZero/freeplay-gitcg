package logic.game;

import java.util.ArrayList;

import logic.card.ActionCard;
import logic.card.CharacterCard;

public class Deck {
	private String deckName;
	private ArrayList<CharacterCard> characterCards;
	private ArrayList<ActionCard> actionCards;
	private PlayArea owner;
	
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


	public Deck(PlayArea playArea)
	{
		this.setDeckName("My Deck");
		this.setCharacterCards(new ArrayList<CharacterCard>());
		this.setActionCards(new ArrayList<ActionCard>());
		this.owner = playArea;
	}
	
	public int addCard(ActionCard card)
	{
		if(!card.canBeAdded(this))
			return 1;
		
		if(this.getActionCards().size() == 30)
			return 1;
		
		if(this.getActionCards().size() == 0)
		{
			this.getActionCards().add(card);
			card.setOwner(owner);
			return 0;
		}
		
		for(int i=0;i<this.getActionCards().size();i++)
		{
			if(this.getActionCards().get(i).getId() > card.getId())
			{
				this.getActionCards().add(i, card);
				card.setOwner(owner);
				return 0;
			}
		}
		//this card is last on the deck list.
		this.getActionCards().add(card);
		card.setOwner(owner);
		return 0;
	}
	
	public int addCard(CharacterCard card)
	{
		if(this.getCharacterCards().size() == 3)
			return 1;
		
		this.getCharacterCards().add(card);
		card.setOwner(owner);
		return 0;
	}
	
	public int removeCard(ActionCard card)
	{
		if(this.getActionCards().size() == 0)
			return 1;
		
		if(this.getActionCards().remove(card))
			return 0;
		return 1;
	}
	
	public int removeCard(CharacterCard card)
	{
		if(this.getCharacterCards().size() == 0)
			return 1;
		
		if(this.getCharacterCards().remove(card))
			return 0;
		return 1;
	}
}
