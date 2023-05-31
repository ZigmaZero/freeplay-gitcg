package data.summon;

import logic.card.SummonCard;
import logic.game.PlayArea;
import utils.EffectHandler;
import utils.EffectMethod;
import utils.enums.ConditionType;
import utils.enums.ElementType;

public class FrostflakeSekiNoTo extends SummonCard {

	public FrostflakeSekiNoTo(PlayArea owner) {
		super(owner);
		setName("Frostflake Seki No To");
		setId(112011);
		setCardImgFilePath("card_img/Summons/FrostflakeSekiNoTo.png");
		setUsageBased(true);
		setUsages(2);
		getEffects().add(new EffectMethod()
			{

				@Override
				public int activate() {
					EffectHandler.damage(2, ElementType.ICE);
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
