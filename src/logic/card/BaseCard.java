package logic.card;

public abstract class BaseCard {
	private int cardId;
	
	public BaseCard(int cardId)
	{
		this.cardId = cardId;
	}
	
	public int getCardId()
	{
		return cardId;
	}
	
}
