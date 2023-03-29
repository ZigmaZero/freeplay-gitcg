package logic.enums;

public enum ConditionType{
	TALENT_COST, //activated when paying the cost for talent.
	TALENT_ACTIVATE, //talent activated after paying cost.
	CARD_COST, //activated when paying the cost for Action Cards.
	CARD_ACTIVATE, //activated when playing an Action Card.
	CHAIN,//consecutive effect blocks activate after the earlier block resolves.
	NONE; //special case or if i'm lazy
}
