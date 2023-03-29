package logic.card;

import java.util.ArrayList;

import logic.effect.EffectString;
import logic.enums.CostType;

public class ArtifactCard extends EquipCard {

	public ArtifactCard(String name, ArrayList<CostType> cost, EffectString effects, int id) {
		super(name, cost, effects, id);
		this.setCardSubCategory(logic.enums.CardSubCategoryType.ARTIFACT);
	}

}
