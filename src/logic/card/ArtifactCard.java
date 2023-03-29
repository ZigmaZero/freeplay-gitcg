package logic.card;

import java.util.ArrayList;

import logic.effect.EffectString;
import logic.enums.CostType;

public class ArtifactCard extends EquipCard {

	public ArtifactCard(String name, ArrayList<CostType> cost, EffectString effects) {
		super(name, cost, effects);
		this.setCardSubCategory(logic.enums.CardSubCategoryType.ARTIFACT);
	}

}
