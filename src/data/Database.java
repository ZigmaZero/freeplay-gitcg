package data;

import java.util.ArrayList;
import java.util.Random;

import data.action.*;
import data.character.*;
import logic.card.*;
import utils.Randomizer;

public class Database {
	private ArrayList<ActionCard> actionCards = new ArrayList<ActionCard>();
	private ArrayList<CharacterCard> characterCards = new ArrayList<CharacterCard>();
	private static Database instance = null;
	private static final Random RNG = Randomizer.getRandomizer();
	public static Database getDatabase()
	{
		if(instance == null)
		{
			instance = new Database();
		}
		
		return instance;
	}
	
	public ArrayList<ActionCard> getActionCards() {
		return actionCards;
	}

	public ArrayList<CharacterCard> getCharacterCards() {
		return characterCards;
	}
	
	private Database()
	{
		this.characterCards.add(new Ayaka());
		this.characterCards.add(new Bennett());
		this.characterCards.add(new Barbara());
		this.characterCards.add(new HilichurlShooter());
		this.actionCards.add(new MondstadtHashBrown());
		this.actionCards.add(new WovenOmniscience());
		this.actionCards.add(new Starsigns());
		this.actionCards.add(new Strategize());
	}
	
	public ActionCard getActionCardReference(int id)
	{
		for(int i=0;i<Database.getDatabase().getActionCards().size();i++)
		{
			if(Database.getDatabase().getActionCards().get(i).getId() == id)
				return Database.getDatabase().getActionCards().get(i);
		}
		return null;
	}
	
	public CharacterCard getCharacterCardReference(int id)
	{
		for(int i=0;i<Database.getDatabase().getCharacterCards().size();i++)
		{
			if(Database.getDatabase().getCharacterCards().get(i).getId() == id)
				return Database.getDatabase().getCharacterCards().get(i);
		}
		return null;
	}
	
	private ActionCard clone(ActionCard template)
	{
		ActionCard clone = new ActionCard();
		
		clone.setCardCategory(template.getCardCategory());
		clone.setCardImgFilePath(template.getCardImgFilePath());
		clone.setCardSubCategory(template.getCardSubCategory());
		clone.setCost(template.getCost());
		clone.setDescription(template.getDescription());
		clone.setEffect(template.getEffect());
		clone.setId(template.getId());
		clone.setName(template.getName());
		return clone;
	}
	
	public ActionCard getRandomActionCard()
	{
		ActionCard template =  getDatabase().getActionCards().get(RNG.nextInt(getDatabase().getActionCards().size()));
		return clone(template);
	}
}
