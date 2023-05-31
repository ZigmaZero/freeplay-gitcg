package utils.enums;

import java.util.Random;

import utils.Randomizer;

public enum DiceType {
	ICE,
	FIRE,
	THUNDER,
	WIND,
	STONE,
	WATER,
	PLANT,
	OMNI;
	
	private static final Random RNG = Randomizer.getRandomizer();

    public static DiceType randomBasicElement() {
    	DiceType[] elements = values();
        return elements[RNG.nextInt(elements.length - 1)];
    }
    
    public static DiceType randomDiceType() {
    	DiceType[] elements = values();
        return elements[RNG.nextInt(elements.length)];
    }
}
