package data.action;

import logic.card.ActionCard;
import logic.game.PlayArea;
import utils.EffectHandler;
import utils.EffectMethod;
import utils.enums.CardCategoryType;
import utils.enums.CardSubCategoryType;
import utils.enums.ConditionType;
import utils.enums.DiceType;

public class WovenOmniscience extends ActionCard {
	public WovenOmniscience()
	{
		this.setName("Woven Omniscience");
		this.setId(331801);
		this.setCardCategory(CardCategoryType.EVENT);
		this.setCardSubCategory(CardSubCategoryType.NONE);
		this.setCardImgFilePath("card_img/Events/Woven Omniscience.png");
		this.setEffect(new EffectMethod()
				{
					private PlayArea owner;
					@Override
					public int activate() {
						EffectHandler.createDice(this.owner, DiceType.OMNI);
						return 0;
					}

					@Override
					public String getDescription() {
						return "Woven Omniscience\n"
								+ "Event Card\n"
								+ "Creates 1 Omni Element.";
					}

					@Override
					public void setOwner(PlayArea owner) {
						this.owner = owner;
					}

					@Override
					public boolean meetsCondition(PlayArea player, ConditionType condition) {
						return true;
					}
			
				});
		this.setDescription(this.getEffect().getDescription());
	}
}
