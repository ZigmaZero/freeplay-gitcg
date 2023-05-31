package data.character;

import java.util.ArrayList;

import logic.card.CharacterCard;
import utils.EffectHandler;
import utils.TalentMethod;
import utils.enums.CostType;
import utils.enums.ElementType;
import utils.enums.FactionType;
import utils.enums.TalentType;
import utils.enums.WeaponType;

public class HilichurlFighter extends CharacterCard {
	public HilichurlFighter()
	{
		this.setCharacterName("Hilichurl Fighter");
		this.setMaxHp(4);
		this.setMaxEnergy(2);
		this.setCharacterElement(ElementType.NONE);
		this.setCharacterWeaponType(WeaponType.NONE);
		this.setCharacterFaction(FactionType.HILICHURL);
		this.setId(-1001);
		
		this.setCurrentHp(4);
		this.setCurrentEnergy(0);
		this.setAffectedByElement(ElementType.NONE);
		this.setCardImgFilePath("card_img/Character/Hilichurl Fighter.png");
		
		this.getTalents().add(new TalentMethod() {
			public TalentType getTalentType() {
				return TalentType.NORMAL_ATTACK;
			}
			public ArrayList<CostType> getCost() {
				ArrayList<CostType> cost = new ArrayList<CostType>();
				cost.add(CostType.UNALIGNED);
				cost.add(CostType.UNALIGNED);
				return cost;
			}
			public int activate() {
				EffectHandler.attack(1, ElementType.PHYSICAL);
				EffectHandler.normalGainEnergy(getOwner());
				return 0;
			}
			public String getDescription() {
				return "Hit\n"
						+ "Normal Attack\n"
						+ "Deals 1 Physical DMG.";
			}
		});
		
		this.getTalents().add(new TalentMethod() {
			public TalentType getTalentType() {
				return TalentType.ELEMENTAL_SKILL;
			}
			public ArrayList<CostType> getCost() {
				ArrayList<CostType> cost = new ArrayList<CostType>();
				cost.add(CostType.UNALIGNED);
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
				return "Jump and Hit\n"
						+ "Normal Attack\n"
						+ "Deals 2 Physical DMG.";
			}
		});
		
		this.getTalents().add(new TalentMethod() {
			public TalentType getTalentType() {
				return TalentType.ELEMENTAL_BURST;
			}
			public ArrayList<CostType> getCost() {
				ArrayList<CostType> cost = new ArrayList<CostType>();
				cost.add(CostType.UNALIGNED);
				cost.add(CostType.UNALIGNED);
				cost.add(CostType.UNALIGNED);
				return cost;
			}
			public int activate() {
				EffectHandler.attack(4, ElementType.PHYSICAL);
				return 0;
			}
			public String getDescription() {
				return "Swing That Club\n"
						+ "Elemental Burst\n"
						+ "Deals 4 Physical DMG.";
			}
		});
	}
}
