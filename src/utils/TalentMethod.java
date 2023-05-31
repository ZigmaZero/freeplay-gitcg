package utils;

import java.util.ArrayList;

import utils.enums.CostType;
import utils.enums.TalentType;

public interface TalentMethod {
	public TalentType getTalentType();
	public ArrayList<CostType> getCost();
	public int activate();
	public String getDescription();
}
