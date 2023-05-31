package utils;

import logic.game.PlayArea;
import utils.enums.ConditionType;

public interface EffectMethod {
	public void setOwner(PlayArea owner);
	public int activate();
	public boolean meetsCondition(PlayArea player, ConditionType condition);
	public String getDescription();
}
