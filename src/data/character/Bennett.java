package data.character;

import java.util.ArrayList;

import logic.card.CharacterCard;
import utils.EffectHandler;
import utils.TalentMethod;
import utils.enums.*;

// temp (Ayaka dupe)
public class Bennett extends CharacterCard {
	public Bennett()
	{
		this.setCharacterName("Bennett");
		this.setMaxHp(10);
		this.setMaxEnergy(2);
		this.setCharacterElement(ElementType.FIRE);
		this.setCharacterWeaponType(WeaponType.SWORD);
		this.setCharacterFaction(FactionType.MONDO);
		this.setId(1303);
		
		this.setCurrentHp(10);
		this.setCurrentEnergy(0);
		this.setAffectedByElement(ElementType.NONE);
		this.setCardImgFilePath("card_img/Character/Bennett.png");
		
		this.getTalents().add(new TalentMethod() {
			public TalentType getTalentType() {
				return TalentType.NORMAL_ATTACK;
			}
			public ArrayList<CostType> getCost() {
				ArrayList<CostType> cost = new ArrayList<CostType>();
				cost.add(CostType.FIRE);
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
				return "Strike of Fortune\n"
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
				cost.add(CostType.FIRE);
				cost.add(CostType.FIRE);
				cost.add(CostType.FIRE);
				return cost;
			}
			public int activate() {
				EffectHandler.attack(3, ElementType.FIRE);
				EffectHandler.normalGainEnergy(getOwner());
				return 0;
			}
			public String getDescription() {
				return "Passion Overload\n"
						+ "Elemental Skill\n"
						+ "Deals 3 Pyro DMG.";
			}
		});
	}
}
