package logic.card;

import data.CardText;

public abstract class BaseCard {
	private CardText card;
	
	public BaseCard(CardText card)
	{
		this.card = card;
	}
	
	public CardText getCard()
	{
		return card;
	}
	
}
