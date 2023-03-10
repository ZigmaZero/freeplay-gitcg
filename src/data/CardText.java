package data;

import utils.Randomizer;

import java.util.Random;

public enum CardText {
	SAMPLE("Sample Name", "Sample Flavor", "Sample Card Text");
	
	// fields
	private final String name, flavor, text;
	
	private static final Random RNG = Randomizer.getRandomizer();
	
	// constructors
	private CardText(String name, String flavor, String text)
	{
		this.name = name;
		this.flavor = flavor;
		this.text = text;
	}
	
	// methods
	public String getName()
	{
		return this.name;
	}
	
	public String getFlavor()
	{
		return this.flavor;
	}
	
	public String getText()
	{
		return this.text;
	}
	
	public static CardText getRandomCardText()
	{
		CardText[] cards = values();
		return cards[RNG.nextInt(cards.length)];
	}

}
