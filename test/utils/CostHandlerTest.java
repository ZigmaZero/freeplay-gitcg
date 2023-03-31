package utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import logic.enums.CostType;
import logic.enums.DiceType;

class CostHandlerTest {

	@Test
	void testElementalCost() {
		ArrayList<CostType> cost = new ArrayList<CostType>();
		cost.add(CostType.FIRE);
		
		ArrayList<DiceType> dicetray = new ArrayList<DiceType>();
		dicetray.add(DiceType.FIRE);
		
		assertTrue(CostHandler.isCostPayable(cost, dicetray));
		
		dicetray.clear();
		dicetray.add(DiceType.OMNI);
		
		assertTrue(CostHandler.isCostPayable(cost, dicetray));
		
		dicetray.clear();
		dicetray.add(DiceType.PLANT);
		
		assertFalse(CostHandler.isCostPayable(cost, dicetray));
		
		cost.add(CostType.FIRE);
		dicetray.add(DiceType.FIRE);
		
		assertFalse(CostHandler.isCostPayable(cost, dicetray));
	}

}
