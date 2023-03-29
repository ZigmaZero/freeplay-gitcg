package logic.card;

import java.util.ArrayList;

import logic.effect.EffectString;
import logic.enums.CostType;

public class EquipCard extends ActionCard {

	public EquipCard(String name, ArrayList<CostType> cost, EffectString effects, int id) {
		super(name, cost, effects, id);
		this.setCardCategory(logic.enums.CardCategoryType.EQUIP);
	}
	
	public boolean canEquip(CharacterCard character)
	{
		return true;
	}

}
