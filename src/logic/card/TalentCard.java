package logic.card;

import java.util.ArrayList;

import logic.effect.EffectString;
import logic.enums.CostType;
import logic.game.Deck;

public class TalentCard extends EquipCard {
	
	private String characterName;

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public TalentCard(String name, ArrayList<CostType> cost, EffectString effects, String characterName) {
		super(name, cost, effects);
		this.setCardSubCategory(logic.enums.CardSubCategoryType.TALENT);
		this.setCharacterName(characterName);
	}
	
	public boolean canEquip(CharacterCard character)
	{
		return this.getCharacterName().equals(character.getCharacterName());
	}
	
	public boolean canBeAdded(Deck deck)
	{
		ArrayList<CharacterCard> characters = deck.getCharacterCards();
		for(int i=0;i<characters.size();i++)
		{
			if(this.getCharacterName().equals(characters.get(i).getCharacterName()))
				return true;
		}
		return false;
	}

}
