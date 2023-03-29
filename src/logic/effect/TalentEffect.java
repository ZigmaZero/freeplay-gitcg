package logic.effect;

import java.util.ArrayList;

import logic.enums.CostType;

public class TalentEffect {

	private String name;
	private EffectString effectString;
	private ArrayList<CostType> cost;
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public EffectString getEffectString()
	{
		return effectString;
	}
	
	public void setEffectString(EffectString effects)
	{
		this.effectString = effects;
	}
	
	public ArrayList<CostType> getCost() {
		return cost;
	}

	public void setCost(ArrayList<CostType> cost) {
		this.cost = cost;
	}

	public TalentEffect(String name, ArrayList<CostType> cost, EffectString effects)
	{
		this.setName(name);
		this.setEffectString(effects);
		if(this.getEffectString().getEffects().get(0).getCondition() != logic.enums.ConditionType.TALENT_ACTIVATE)
			this.getEffectString().getEffects().get(0).setCondition(logic.enums.ConditionType.TALENT_ACTIVATE);
		
		this.setCost(cost);
	}
	
	public void activate()
	{
		this.getEffectString().activate(logic.enums.ConditionType.TALENT_ACTIVATE);
	}
}
