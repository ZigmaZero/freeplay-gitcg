package logic.enums;

import java.util.Random;

import utils.Randomizer;

public enum ElementType{
	ICE,
	FIRE,
	THUNDER,
	WIND,
	STONE,
	WATER,
	PLANT,
	PHYSICAL;
	
	private static final Random RNG = Randomizer.getRandomizer();

    public static ElementType randomBasicElement() {
    	ElementType[] elements = values();
        return elements[RNG.nextInt(elements.length - 1)];
    }
}
