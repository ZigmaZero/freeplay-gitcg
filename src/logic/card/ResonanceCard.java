package logic.card;

import java.util.ArrayList;

import logic.effect.EffectString;
import logic.enums.CostType;
import logic.enums.ElementType;
import logic.game.Deck;

public class ResonanceCard extends ActionCard {

	private ElementType resonatingElement;
	
	public ResonanceCard(String name, ArrayList<CostType> cost, EffectString effects, ElementType resonatingElement) {
		super(name, cost, effects);
		this.setCardCategory(logic.enums.CardCategoryType.EVENT);
		this.setCardSubCategory(logic.enums.CardSubCategoryType.RESONANCE);
		this.resonatingElement = resonatingElement;
	}
	
	public boolean canBeAdded(Deck deck)
	{
		int count = 0;
		ArrayList<CharacterCard> characters = deck.getCharacterCards();
		for(int i=0;i<characters.size();i++)
		{
			if(characters.get(i).getCharacterElement() == this.resonatingElement)
				count++;
		}
		if(count >= 2)
			return true;
		return false;
	}

}
