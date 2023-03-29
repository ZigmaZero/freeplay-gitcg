package logic.card;

import java.util.ArrayList;

import logic.effect.EffectString;
import logic.enums.CostType;
import logic.enums.WeaponType;

public class WeaponCard extends EquipCard {
	
	private WeaponType weaponType;

	public WeaponType getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(WeaponType weaponType) {
		this.weaponType = weaponType;
	}

	public WeaponCard(String name, ArrayList<CostType> cost, EffectString effects, int id, WeaponType weaponType) {
		super(name, cost, effects, id);
		this.setCardSubCategory(logic.enums.CardSubCategoryType.WEAPON);
		this.setWeaponType(weaponType);
	}

	public boolean canEquip(CharacterCard character)
	{
		return this.getWeaponType() == character.getCharacterWeaponType();
	}
}
