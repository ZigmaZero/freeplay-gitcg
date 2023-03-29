package logic.card;

import java.util.ArrayList;

import logic.effect.EffectString;
import logic.enums.CostType;

public class EquipCard extends ActionCard {

	public EquipCard(String name, ArrayList<CostType> cost, EffectString effects) {
		super(name, cost, effects);
		this.setCardCategory(logic.enums.CardCategoryType.EQUIP);
	}
	
	public boolean canEquip(CharacterCard character)
	{
		return true;
	}

}
