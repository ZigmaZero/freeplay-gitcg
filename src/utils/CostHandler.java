package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import logic.enums.CostType;
import logic.enums.DiceType;

public class CostHandler {

	public static void fulfillCost(ArrayList<CostType> cost, ArrayList<DiceType> dice) {

	}

	public static boolean isCostPayable(ArrayList<CostType> cost, ArrayList<DiceType> dicetray) {

		if (cost == null || dicetray == null) {
			// dont know if this will ever happen
		}

		if (cost.size() == 0) {
			return true;
		}
		if (cost.size() > dicetray.size()) {
			return false;
		}

		// count amount of each dicetype in dicetray
		Map<String, Integer> countDiceType = new HashMap<>();
		for (DiceType diceType : dicetray) {
			if (countDiceType.containsKey(diceType.toString())) {
				countDiceType.put(diceType.toString(), countDiceType.get(diceType.toString()) + 1);
			} else {
				countDiceType.put(diceType.toString(), 1);
			}
		}
		// case only have omni in dicetray
		if (countDiceType.containsKey("OMNI") && countDiceType.size() == 1) {
			return countDiceType.get("OMNI") >= cost.size();
		}

		// case contains aligned
		if (cost.contains(CostType.ALIGNED)) {

			// find the cost type that we need for aligned
			String alignedCostType = "ALIGNED";
			for (CostType costType : cost) {
				if (costType != CostType.ALIGNED) {
					alignedCostType = costType.toString();
				}
			}

			// count omni as any type
			if (countDiceType.containsKey("OMNI")) {
				int omniAsOther = countDiceType.get("OMNI");
				for (String diceType : countDiceType.keySet()) {
					countDiceType.put(diceType, countDiceType.get(diceType) + omniAsOther);
				}
				countDiceType.remove("OMNI");
			}

			// case have only aligned as cost
			if (alignedCostType == "ALIGNED") {
				int maxCount = Collections.max(countDiceType.values());
				return maxCount >= cost.size();
			}
			// case have other type with aligned
			else {
				for (String dicetype : countDiceType.keySet()) {
					if (alignedCostType.equals(dicetype)) {
						return countDiceType.get(dicetype) >= cost.size();
					}
				}
			}
			return false;
		}

		// case do not contains aligned
		int unalignedCount = 0;
		for (CostType costType : cost) {
			String costTypeString = costType.toString();
			if (costType == CostType.UNALIGNED) {
				unalignedCount += 1;
				continue;
			}

			if (countDiceType.containsKey(costTypeString)) {
				if (countDiceType.get(costTypeString) != 0) {
					countDiceType.put(costTypeString, countDiceType.get(costTypeString) - 1);
				} else if (countDiceType.containsKey("OMNI")) {
					if (countDiceType.get("OMNI") != 0) {
						countDiceType.put("OMNI", countDiceType.get("OMNI") - 1);
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else if (countDiceType.containsKey("OMNI")) {
				if (countDiceType.get("OMNI") != 0) {
					countDiceType.put("OMNI", countDiceType.get("OMNI") - 1);
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		// deal with unaligned
		if (unalignedCount != 0) {
			int diceLeft = 0;
			for (int count : countDiceType.values()) {
				diceLeft += count;
			}
			return diceLeft >= unalignedCount;
		}

		return true;

	}

}
