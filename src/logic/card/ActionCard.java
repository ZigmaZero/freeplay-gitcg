package logic.card;

import java.util.ArrayList;

import logic.effect.EffectString;
import logic.enums.CardCategoryType;
import logic.enums.CardSubCategoryType;
import logic.enums.CostType;
import logic.game.Deck;

public class ActionCard {

	//same as talent
	private String name;
	private EffectString effectString;
	private ArrayList<CostType> cost;
	//card categories
	private int id;
	private CardCategoryType cardCategory;
	private CardSubCategoryType cardSubCategory;
	
	public boolean canBeAdded(Deck deck)
	{
		return true;
	}
	
	public CardCategoryType getCardCategory() {
		return cardCategory;
	}

	public void setCardCategory(CardCategoryType cardCategory) {
		this.cardCategory = cardCategory;
	}

	public CardSubCategoryType getCardSubCategory() {
		return cardSubCategory;
	}

	public void setCardSubCategory(CardSubCategoryType cardSubCategory) {
		this.cardSubCategory = cardSubCategory;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
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

	public ActionCard(String name, ArrayList<CostType> cost, EffectString effects, int id)
	{
		this.setName(name);
		this.setEffectString(effects);
		if(this.getEffectString().getEffects().get(0).getCondition() != logic.enums.ConditionType.CARD_ACTIVATE)
			this.getEffectString().getEffects().get(0).setCondition(logic.enums.ConditionType.CARD_ACTIVATE);
		
		this.setCost(cost);
		this.setCardCategory(null);
		this.setCardSubCategory(null);
	}
	
	public ActionCard(String name, ArrayList<CostType> cost, EffectString effects, int id, CardCategoryType cardCategory)
	{
		this(name, cost, effects, id);
		this.setCardCategory(cardCategory);
		this.setCardSubCategory(null);
	}
	
	public ActionCard(String name, ArrayList<CostType> cost, EffectString effects, int id, CardCategoryType cardCategory, CardSubCategoryType cardSubCategory)
	{
		this(name, cost, effects, id, cardCategory);
		this.setCardSubCategory(cardSubCategory);
	}
	
	public void activate()
	{
		this.getEffectString().activate(logic.enums.ConditionType.CARD_ACTIVATE);
	}
	
	public String toString()
	{
		return this.getName();
	}
}