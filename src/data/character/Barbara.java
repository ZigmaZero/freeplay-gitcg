package data.character;

import java.util.ArrayList;

import data.summon.MelodyLoop;
import logic.card.CharacterCard;
import utils.EffectHandler;
import utils.TalentMethod;
import utils.enums.*;

public class Barbara extends CharacterCard {
	public Barbara()
	{
		this.setCharacterName("Barbara");
		this.setMaxHp(10);
		this.setMaxEnergy(3);
		this.setCharacterElement(ElementType.WATER);
		this.setCharacterWeaponType(WeaponType.CATALYST);
		this.setCharacterFaction(FactionType.MONDO);
		this.setId(1201);
		
		this.setCurrentHp(10);
		this.setCurrentEnergy(0);
		this.setAffectedByElement(ElementType.NONE);
		this.setCardImgFilePath("card_img/Character/Barbara.png");
		
		this.getTalents().add(new TalentMethod() {
			public TalentType getTalentType() {
				return TalentType.NORMAL_ATTACK;
			}
			public ArrayList<CostType> getCost() {
				ArrayList<CostType> cost = new ArrayList<CostType>();
				cost.add(CostType.WATER);
				cost.add(CostType.UNALIGNED);
				cost.add(CostType.UNALIGNED);
				return cost;
			}
			public int activate() {
				EffectHandler.attack(1, ElementType.WATER);
				EffectHandler.normalGainEnergy(getOwner());
				return 0;
			}
			public String getDescription() {
				return "Whisper of Water\n"
						+ "Normal Attack\n"
						+ "Deals 1 Hydro DMG.";
			}
		});
		
		this.getTalents().add(new TalentMethod() {
			public TalentType getTalentType() {
				return TalentType.ELEMENTAL_SKILL;
			}
			public ArrayList<CostType> getCost() {
				ArrayList<CostType> cost = new ArrayList<CostType>();
				cost.add(CostType.WATER);
				cost.add(CostType.WATER);
				cost.add(CostType.WATER);
				return cost;
			}
			public int activate() {
				EffectHandler.attack(1, ElementType.WATER);
				EffectHandler.summon(new MelodyLoop(getOwner()));
				EffectHandler.normalGainEnergy(getOwner());
				return 0;
			}
			public String getDescription() {
				return "Let the Show Begin♪\n"
						+ "Elemental Skill\n"
						+ "Deals 1 Hydro DMG, summons 1 Melody Loop.";
			}
		});
	}
}
