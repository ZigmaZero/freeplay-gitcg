package logic.game;

import java.util.ArrayList;

import logic.card.ActionCard;
import logic.card.CharacterCard;
import logic.enums.Response;

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
	
	public Response addCard(ActionCard card)
	{
		if(!card.canBeAdded(this))
			return Response.ADD_CARD_INVALID;
		
		if(this.getActionCards().size() == 30)
			return Response.ADD_CARD_FULLDECK;
		
		if(this.getActionCards().size() == 0)
		{
			this.getActionCards().add(card);
			return Response.SUCCESS;
		}
		
		for(int i=0;i<this.getActionCards().size();i++)
		{
			if(this.getActionCards().get(i).getId() > card.getId())
			{
				this.getActionCards().add(i, card);
				return Response.SUCCESS;
			}
		}
		//this card is last on the deck list.
		this.getActionCards().add(card);
		return Response.SUCCESS;
	}
	
	public Response removeCard(ActionCard card)
	{
		if(this.getActionCards().size() == 0)
			return Response.REMOVE_CARD_EMPTYDECK;
		
		for(int i=0;i<this.getActionCards().size();i++)
		{
			if(this.getActionCards().get(i).getId() == card.getId())
			{
				this.getActionCards().remove(i);
				return Response.SUCCESS;
			}
		}
		//no such card in list.
		return Response.REMOVE_CARD_EMPTYDECK;
	}
}
