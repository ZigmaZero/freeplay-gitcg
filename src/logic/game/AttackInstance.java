package logic.game;

import utils.enums.ElementType;

public class AttackInstance {
	private static AttackInstance ongoingAttackInstance;
	public static AttackInstance getOngoingAttackInstance()
	{
		if(ongoingAttackInstance == null)
			ongoingAttackInstance = new AttackInstance();
		return ongoingAttackInstance;
	}
	
	private int attackDmg;
	private ElementType attackElement;
	private int deltaAtk;
	private int deltaDef;
	
	public int getOriginalDmg()
	{
		return attackDmg;
	}
	
	public void clearInstance()
	{
		ongoingAttackInstance.attackDmg = 0;
		ongoingAttackInstance.attackElement = ElementType.NONE;
		deltaAtk = 0;
		deltaDef = 0;
	}
	
	public void setInstance(int attackDmg, ElementType attackElement)
	{
		ongoingAttackInstance.attackDmg = attackDmg;
		ongoingAttackInstance.attackElement = attackElement;
		deltaAtk = 0;
		deltaDef = 0;
	}
	
	public void upAtk(int deltaAtk)
	{
		ongoingAttackInstance.deltaAtk += deltaAtk;
	}
	
	public void upDef(int deltaDef)
	{
		ongoingAttackInstance.deltaDef += deltaDef;
	}
	
	public int getMaxDmg()
	{
		return attackDmg + deltaAtk;
	}
	
	public int getFinalDmg()
	{
		return attackDmg + deltaAtk - deltaDef;
	}
	
	public ElementType getAttackElement()
	{
		return attackElement;
	}
}
