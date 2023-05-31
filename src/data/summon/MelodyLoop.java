package data.summon;

import logic.card.SummonCard;
import logic.game.PlayArea;
import utils.EffectHandler;
import utils.EffectMethod;
import utils.enums.ConditionType;
import utils.enums.ElementType;

public class MelodyLoop extends SummonCard{
	public MelodyLoop(PlayArea owner) {
		super(owner);
		setName("Melody Loop");
		setId(112011);
		setCardImgFilePath("card_img/Summons/Melody Loop.png");
		setUsageBased(true);
		setUsages(2);
		getEffects().add(new EffectMethod()
			{

				@Override
				public int activate() {
					EffectHandler.healAll(owner, 1);
					EffectHandler.applyElement(owner.getActiveCharacter(), ElementType.WATER);
					return 0;
				}

				@Override
				public String getDescription() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void setOwner(PlayArea owner) {
					//im pretty sure these things don't need to set owners, could be wrong though.
				}

				@Override
				public boolean meetsCondition(PlayArea player, ConditionType condition) {
					return condition==ConditionType.END_PHASE && player==owner;
				}

			});
	}
}
