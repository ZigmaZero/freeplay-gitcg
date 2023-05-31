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

public class Strategize extends ActionCard {
	public Strategize()
	{
		this.setName("Strategize");
		this.setId(332004);
		this.setCardCategory(CardCategoryType.EVENT);
		this.setCardSubCategory(CardSubCategoryType.NONE);
		this.getCost().add(CostType.ALIGNED);
		this.setCardImgFilePath("card_img/Events/Strategize.png");
		this.setEffect(new EffectMethod()
		{
			private PlayArea owner;
			@Override
			public int activate() {
				EffectHandler.draw(owner, 2);
				return 0;
			}

			@Override
			public String getDescription() {
				return "Strategize\n"
						+ "Event Card\n"
						+ "Draw 2 cards.";
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
