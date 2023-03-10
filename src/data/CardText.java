package data;

public enum CardText {
	SAMPLE("Sample Name", "Sample Flavor", "Sample Card Text");
	
	// fields
	private final String name, flavor, text;
	
	
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
}
