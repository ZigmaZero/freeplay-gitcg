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
	
	public boolean addCard(ActionCard card)
	{
		if(!card.canBeAdded(this))
			return false;
		
		if(this.getActionCards().size() == 30)
			return false;
		
		if(this.getActionCards().size() == 0)
		{
			this.getActionCards().add(card);
			return true;
		}
		
		for(int i=0;i<this.getActionCards().size();i++)
		{
			if(this.getActionCards().get(i).getId() > card.getId())
			{
				this.getActionCards().add(i, card);
				return true;
			}
		}
		//this card is last on the deck list.
		this.getActionCards().add(card);
		return true;
	}
	
	public boolean removeCard(ActionCard card)
	{
		if(this.getActionCards().size() == 0)
			return false;
		
		for(int i=0;i<this.getActionCards().size();i++)
		{
			if(this.getActionCards().get(i).getId() == card.getId())
			{
				this.getActionCards().remove(i);
				return true;
			}
		}
		//no such card in list.
		return false;
	}
}
