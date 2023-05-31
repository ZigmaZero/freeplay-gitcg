package utils.enums;

public enum ConditionType{
	TALENT_COST, //activated when paying the cost for talent. (cost reduction)
	TALENT_ACTIVATE, //talent activated after paying cost. ("When using a Skill:")
	ULT_ACTIVATE, //ultimate: talent requires additional energy cost. ("When using an Elemental Burst:")
	CARD_COST, //activated when paying the cost for Action Cards. (cost reduction)
	CARD_ACTIVATE, //activated when playing an Action Card. ("When you play a card:")
	
	ATTACK_DECLARE,
	ATTACK_CONCLUDE,
	
	ROLL_PHASE,
	ACTION_PHASE,
	END_PHASE,
	
	SWITCH_CHARACTER,
	
	NONE, 
	SUPPRESSED; 
}
