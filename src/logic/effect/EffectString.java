package logic.effect;

import java.util.ArrayList;

import logic.enums.ConditionType;

public class EffectString {
	private ArrayList<EffectBlock> effects;
	
	public ArrayList<EffectBlock> getEffects() {
		return effects;
	}

	public void setEffects(ArrayList<EffectBlock> effects) {
		this.effects = effects;
	}

	public EffectString(ArrayList<EffectBlock> effects)
	{
		this.setEffects(effects);
	}
	
	public void activate(ConditionType condition)
	{
		for(int i=0;i < effects.size(); i++)
		{
			if(condition == effects.get(i).getCondition())
			{
				effects.get(i).activate();
			}
		}
	}
	
	public void addEffect(EffectBlock newEffect)
	{
		this.effects.add(newEffect);
	}
	
	public void removeEffect(EffectBlock newEffect)
	{
		for(int i=0;i<this.effects.size();i++)
		{
			if(newEffect.equals(this.effects.get(i)))
			{
				this.effects.remove(i);
				break;
			}
		}
	}
}
