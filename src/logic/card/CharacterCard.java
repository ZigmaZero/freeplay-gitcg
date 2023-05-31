package logic.card;

import java.util.ArrayList;

import img.ImageLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import logic.game.Deck;
import logic.game.PlayArea;
import utils.RenderObject;
import utils.TalentMethod;
import utils.enums.ElementType;
import utils.enums.FactionType;
import utils.enums.WeaponType;

public class CharacterCard implements RenderObject {
	//constant
	private String characterName;
	private int maxHp;
	private int maxEnergy;
	private ElementType characterElement;
	private WeaponType characterWeaponType;
	private FactionType characterFaction;
	private int id;
	private ArrayList<TalentMethod> talents = new ArrayList<TalentMethod>();
	
	//dynamic
	private int currentHp;
	private int currentEnergy;
	private ElementType affectedByElement;
	
	//render parameters
	private String cardImgFilePath;
	
	private PlayArea owner;
	
	
	//getters and setters for everything (why do i do this to myself)
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public int getMaxHp() {
		return maxHp;
	}
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
	public int getMaxEnergy() {
		return maxEnergy;
	}
	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}
	public ElementType getCharacterElement() {
		return characterElement;
	}
	public void setCharacterElement(ElementType characterElement) {
		this.characterElement = characterElement;
	}
	public WeaponType getCharacterWeaponType() {
		return characterWeaponType;
	}
	public void setCharacterWeaponType(WeaponType characterWeaponType) {
		this.characterWeaponType = characterWeaponType;
	}
	public FactionType getCharacterFaction() {
		return characterFaction;
	}
	public void setCharacterFaction(FactionType characterFaction) {
		this.characterFaction = characterFaction;
	}

	public int getCurrentHp() {
		return currentHp;
	}
	public void setCurrentHp(int currentHp) {
		if(currentHp < 0)
			currentHp = 0;
		if(currentHp > maxHp)
			currentHp = maxHp;
		this.currentHp = currentHp;
	}
	public int getCurrentEnergy() {
		return currentEnergy;
	}
	public void setCurrentEnergy(int currentEnergy) {
		if(currentEnergy < 0)
			currentEnergy = 0;
		if(currentEnergy > maxEnergy)
			currentEnergy = maxEnergy;
		this.currentEnergy = currentEnergy;
	}
	public ElementType getAffectedByElement() {
		return affectedByElement;
	}
	public void setAffectedByElement(ElementType affectedByElement) {
		this.affectedByElement = affectedByElement;
	}
	public String getCardImgFilePath() {
		return cardImgFilePath;
	}
	public void setCardImgFilePath(String cardImgFilePath) {
		this.cardImgFilePath = cardImgFilePath;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public PlayArea getOwner() {
		return owner;
	}
	public void setOwner(PlayArea owner) {
		this.owner = owner;
	}
	public ArrayList<TalentMethod> getTalents() {
		return talents;
	}
	public void setTalents(ArrayList<TalentMethod> talents) {
		this.talents = talents;
	}
	public boolean canBeAdded(Deck deck)
	{
		return true;
	}
	
	public String toString()
	{
		return this.getCharacterName();
	}
	
	@Override
	public Node render() {
		return new ImageView(ImageLoader.getInstance().getImage(cardImgFilePath));
	}
	
	public boolean isDefeated()
	{
		return getCurrentHp() == 0;
	}
	
	public int activate(int talent_number)
	{
		return talents.get(talent_number).activate();
	}
	
	public int activate(TalentMethod talent)
	{
		if(talents.contains(talent))
			return talent.activate();
		else
			return 1;
	}
}
