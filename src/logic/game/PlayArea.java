package logic.game;

import java.util.ArrayList;
import java.util.Iterator;

import application.view.BattleController;
import logic.card.ActionCard;
import logic.card.CharacterCard;
import logic.card.SummonCard;
import utils.EffectHandler;
import utils.EffectMethod;
import utils.enums.ConditionType;
import utils.enums.DiceType;

public class PlayArea {
	private Deck playerDeck;
	private ArrayList<ActionCard> playerHand;
	private CharacterCard activeCharacter;
	private ArrayList<DiceType> playerDice;
	private ArrayList<ActionCard> discardPile = new ArrayList<ActionCard>();
	private ArrayList<SummonCard> summonZone = new ArrayList<SummonCard>();
	private boolean endedRound;

	public PlayArea() {
		setPlayerDeck(new Deck(this));
		playerHand = new ArrayList<ActionCard>();
		discardPile = new ArrayList<ActionCard>();
		playerDice = new ArrayList<>();
		summonZone = new ArrayList<>();
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
	
	//get/setters
	public void resetPlayer() {
		setPlayerDice(new ArrayList<>());
		getPlayerDeck().getActionCards().clear();
		getPlayerDeck().getCharacterCards().clear();
		getPlayerHand().clear();
		getDiscardPile().clear();
		getSummonZone().clear();
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

	public ArrayList<ActionCard> getDiscardPile() {
		return discardPile;
	}

	public void setDiscardPile(ArrayList<ActionCard> discardPile) {
		this.discardPile = discardPile;
	}

	public ArrayList<SummonCard> getSummonZone() {
		return summonZone;
	}

	public void setSummonZone(ArrayList<SummonCard> summonZone) {
		this.summonZone = summonZone;
	}
	
	public boolean isEndedRound() {
		return endedRound;
	}

	public void setEndedRound(boolean endedTurn) {
		this.endedRound = endedTurn;
	}

	//effect handling methods (called from the EffectHandler sometimes.)
	public void queueConditionedEffects(PlayArea player, ConditionType condition)
	{
		Iterator<SummonCard> it = this.getSummonZone().iterator();
		while(it.hasNext())
		{
			boolean isActivated = false;
			SummonCard summon = it.next();
			for(EffectMethod effect : summon.getEffects())
			{
				if(effect.meetsCondition(player, condition))
				{
					EffectHandler.queueEffect(effect);
					isActivated = true;
				}
			}
			if(isActivated && summon.isUsageBased())
			{
				summon.setUsages(summon.getUsages() - 1);
				
				if(summon.getUsages() == 0)
				{
					it.remove();
					BattleController.getSummons().update();
				}
			}
		}
	}
	
	public void playActionCard(ActionCard card)
	{
		//activate() the card and echo CARD_ACTIVATE to GameInstance.
		//then discard it from hand.
		int r = card.activate();
		if(r == 0)
		{
			GameInstance.queueConditionedEffects(this, ConditionType.CARD_ACTIVATE);
			this.getPlayerHand().remove(card);
			this.getDiscardPile().add(card);
			EffectHandler.resolveEffect();
		}
	}
}