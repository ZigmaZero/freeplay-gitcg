package logic.card;

import java.util.ArrayList;

import logic.entity.ArtifactEntity;
import logic.entity.TalentEntity;
import logic.entity.WeaponEntity;
import logic.enums.ElementType;
import logic.enums.FactionType;
import logic.enums.WeaponType;
import logic.game.Deck;
import logic.effect.TalentEffect;

public class CharacterCard {
	//constant
	private String characterName;
	private int maxHp;
	private int maxEnergy;
	private ElementType characterElement;
	private WeaponType characterWeaponType;
	private FactionType characterFaction;
	private ArrayList<TalentEffect> characterTalents;
	
	//dynamic
	private int currentHp;
	private int currentEnergy;
	private WeaponEntity equippedWeapon;
	private ArtifactEntity equippedArtifact;
	private TalentEntity equippedTalent;
	private ElementType affectedByElement;
	
	
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
	public ArrayList<TalentEffect> getCharacterTalents() {
		return characterTalents;
	}
	public void setCharacterTalents(ArrayList<TalentEffect> characterTalents) {
		this.characterTalents = characterTalents;
	}
	public int getCurrentHp() {
		return currentHp;
	}
	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}
	public int getCurrentEnergy() {
		return currentEnergy;
	}
	public void setCurrentEnergy(int currentEnergy) {
		this.currentEnergy = currentEnergy;
	}
	public WeaponEntity getEquippedWeapon() {
		return equippedWeapon;
	}
	public void setEquippedWeapon(WeaponEntity equippedWeapon) {
		this.equippedWeapon = equippedWeapon;
	}
	public ArtifactEntity getEquippedArtifact() {
		return equippedArtifact;
	}
	public void setEquippedArtifact(ArtifactEntity equippedArtifact) {
		this.equippedArtifact = equippedArtifact;
	}
	public TalentEntity getEquippedTalent() {
		return equippedTalent;
	}
	public void setEquippedTalent(TalentEntity equippedTalent) {
		this.equippedTalent = equippedTalent;
	}
	public ElementType getAffectedByElement() {
		return affectedByElement;
	}
	public void setAffectedByElement(ElementType affectedByElement) {
		this.affectedByElement = affectedByElement;
	}
	
	public CharacterCard(String characterName, int maxHp, int maxEnergy, ElementType characterElement, WeaponType characterWeaponType, FactionType characterFaction, ArrayList<TalentEffect> characterTalents)
	{
		//set statics
		this.setCharacterName(characterName);
		this.setMaxHp(maxHp);
		this.setMaxEnergy(maxEnergy);
		this.setCharacterElement(characterElement);
		this.setCharacterWeaponType(characterWeaponType);
		this.setCharacterFaction(characterFaction);
		this.setCharacterTalents(characterTalents);
		
		//set dynamics to initial
		this.setCurrentHp(this.getMaxHp());
		this.setCurrentEnergy(0);
		this.setEquippedWeapon(null);
		this.setEquippedArtifact(null);
		this.setEquippedTalent(null);
		this.setAffectedByElement(null);
	}
	
	public boolean canBeAdded(Deck deck)
	{
		return true;
	}
}
