package logic.effect;

import java.util.ArrayList;

import logic.enums.ConditionType;
import logic.enums.EffectType;

public class EffectBlock {
	private ConditionType condition;
	private EffectType effect;
	private ArrayList<Object> params;
	
	//getter and setter
	public ConditionType getCondition() {
		return condition;
	}

	public void setCondition(ConditionType condition) {
		this.condition = condition;
	}
	
	public EffectType getEffect() {
		return effect;
	}


	public void setEffect(EffectType effect) {
		this.effect = effect;
	}


	public ArrayList<Object> getParams() {
		return params;
	}

	public void setParams(ArrayList<Object> params) {
		this.params = params;
	}
	
	//constructor
	public EffectBlock(ConditionType condition, EffectType effect, ArrayList<Object> params) {
		this.setCondition(condition);
		this.setEffect(effect);
		this.setParams(params);
	}
	
	public void activate()
	{
		utils.EffectHandler.activate(effect, params);
	}
	
	public boolean equals(EffectBlock other)
	{
		if(this.getCondition() != other.getCondition())
			return false;
		if(this.getEffect() != other.getEffect())
			return false;
		if(this.getParams() != other.getParams())
			return false;
		
		return true;
	}
	
	public String toString()
	{
		return this.getCondition() + " " + this.getEffect();
	}
}
