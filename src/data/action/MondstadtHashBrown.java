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

public class MondstadtHashBrown extends ActionCard {
	public MondstadtHashBrown()
	{
		
		this.setName("Mondstadt Hash Brown");
		this.setId(333006);
		this.setCardCategory(CardCategoryType.EVENT);
		this.setCardSubCategory(CardSubCategoryType.FOOD);
		this.getCost().add(CostType.ALIGNED);
		this.setCardImgFilePath("card_img/Events/Mondstadt Hash Brown.png");
		this.setEffect(new EffectMethod()
				{

					private PlayArea owner;

					@Override
					public int activate() {
						EffectHandler.healActive(owner, 2);
						return 0;
					}

					@Override
					public String getDescription() {
						return "Mondstadt Hash Brown\n"
								+ "Event Card\n"
								+ "Heals 2 HP to the active character.";
					}

					@Override
					public void setOwner(PlayArea owner) {
						this.owner = owner;
						return; 
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
