package data.character;

import java.util.ArrayList;

import data.summon.FrostflakeSekiNoTo;
import logic.card.CharacterCard;
import utils.EffectHandler;
import utils.TalentMethod;
import utils.enums.*;

public class Ayaka extends CharacterCard {
	public Ayaka()
	{
		this.setCharacterName("Kamisato Ayaka");
		this.setMaxHp(10);
		this.setMaxEnergy(3);
		this.setCharacterElement(ElementType.ICE);
		this.setCharacterWeaponType(WeaponType.SWORD);
		this.setCharacterFaction(FactionType.INAZUMA);
		this.setId(1105);
		
		this.setCurrentHp(10);
		this.setCurrentEnergy(0);
		this.setAffectedByElement(ElementType.NONE);
		this.setCardImgFilePath("card_img/Character/Ayaka.png");
		
		this.getTalents().add(new TalentMethod() {
			public TalentType getTalentType() {
				return TalentType.NORMAL_ATTACK;
			}
			public ArrayList<CostType> getCost() {
				ArrayList<CostType> cost = new ArrayList<CostType>();
				cost.add(CostType.ICE);
				cost.add(CostType.UNALIGNED);
				cost.add(CostType.UNALIGNED);
				return cost;
			}
			public int activate() {
				EffectHandler.attack(2, ElementType.PHYSICAL);
				EffectHandler.normalGainEnergy(getOwner());
				return 0;
			}
			public String getDescription() {
				return "Kamisato Art: Kabuki\n"
						+ "Normal Attack\n"
						+ "Deals 2 Physical DMG.";
			}
		});
		
		this.getTalents().add(new TalentMethod() {
			public TalentType getTalentType() {
				return TalentType.ELEMENTAL_SKILL;
			}
			public ArrayList<CostType> getCost() {
				ArrayList<CostType> cost = new ArrayList<CostType>();
				cost.add(CostType.ICE);
				cost.add(CostType.ICE);
				cost.add(CostType.ICE);
				return cost;
			}
			public int activate() {
				EffectHandler.attack(3, ElementType.ICE);
				EffectHandler.normalGainEnergy(getOwner());
				return 0;
			}
			public String getDescription() {
				return "Kamisato Art: Hyouka\n"
						+ "Elemental Skill\n"
						+ "Deals 3 Cryo DMG.";
			}
		});
		
		this.getTalents().add(new TalentMethod() {
			public TalentType getTalentType() {
				return TalentType.ELEMENTAL_BURST;
			}
			public ArrayList<CostType> getCost() {
				ArrayList<CostType> cost = new ArrayList<CostType>();
				cost.add(CostType.ICE);
				cost.add(CostType.ICE);
				cost.add(CostType.ICE);
				return cost;
			}
			public int activate() {
				EffectHandler.attack(4, ElementType.ICE);
				EffectHandler.summon(new FrostflakeSekiNoTo(getOwner()));
				return 0;
			}
			public String getDescription() {
				return "Kamisato Art: Soumetsu\n"
						+ "Elemental Burst\n"
						+ "Deals 4 Cryo DMG, summons 1 Frostflake Seki no To.";
			}
		});
	}
}
