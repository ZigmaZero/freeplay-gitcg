package data.action;

import logic.card.ActionCard;
import logic.game.GameInstance;
import logic.game.PlayArea;
import utils.EffectHandler;
import utils.EffectMethod;
import utils.enums.CardCategoryType;
import utils.enums.CardSubCategoryType;
import utils.enums.ConditionType;
import utils.enums.CostType;

public class Starsigns extends ActionCard {
	public Starsigns()
	{
		
		this.setName("Starsigns");
		this.setId(332008);
		this.setCardCategory(CardCategoryType.EVENT);
		this.setCardSubCategory(CardSubCategoryType.NONE);
		this.getCost().add(CostType.UNALIGNED);
		this.getCost().add(CostType.UNALIGNED);
		this.setCardImgFilePath("card_img/Events/Starsigns.png");
		this.setEffect(new EffectMethod()
		{
			private PlayArea owner;
			@Override
			public int activate() {
				EffectHandler.normalGainEnergy(owner);
				return 0;
			}

			@Override
			public String getDescription() {
				return "Starsigns\n"
						+ "Event Card\n"
						+ "Your current Active Character gains 1 Energy.";
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
		setOwner(GameInstance.getInstance().getPlayer());
	}
}
