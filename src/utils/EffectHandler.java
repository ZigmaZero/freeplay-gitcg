package utils;

import java.util.ArrayList;

import application.view.MainController;
import application.view.UtilityScene;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.card.*;
import logic.game.AttackInstance;
import logic.game.GameInstance;
import logic.game.PlayArea;
import utils.enums.ConditionType;
import utils.enums.DiceType;
import utils.enums.ElementType;

public class EffectHandler {

	private static ArrayList<EffectMethod> effectQueue = new ArrayList<EffectMethod>();

	public static ArrayList<EffectMethod> getEffectQueue() {
		return effectQueue;
	}

	public static void queueEffect(EffectMethod effect) {
		effectQueue.add(effect);
	}

	public static void resolveEffect() {
		while (effectQueue.size() > 0) {
			effectQueue.get(0).activate();
			effectQueue.remove(0);
		}
	}

	private static void resolveAttack() {
		CharacterCard attackTarget = GameInstance.getInstance().getNextPlayer().getActiveCharacter();
		applyElement(attackTarget, AttackInstance.getOngoingAttackInstance().getAttackElement());
		attackTarget
				.setCurrentHp(attackTarget.getCurrentHp() - AttackInstance.getOngoingAttackInstance().getFinalDmg());
		
		AttackInstance.getOngoingAttackInstance().clearInstance();
		
		if (GameInstance.getInstance().getOpponent().getActiveCharacter().getCurrentHp() <= 0) {
			GameInstance.getInstance().getOpponent().switchOnDeath();
		}
		
		if (GameInstance.getInstance().getPlayer().getActiveCharacter().getCurrentHp() <= 0) {
			Stage stage = MainController.getStage();
			Scene scene = new Scene(new UtilityScene(GameInstance.getInstance().getPlayer().getPlayerDeck().getCharacterCards()));
			stage.setScene(scene);
			stage.show();
		}
		
	}

	public static void attack(int attackDmg, ElementType attackElement) {
		GameInstance instance = GameInstance.getInstance();

		// 1. queue an AttackEntity with attackDmg and attackElement
		AttackInstance.getOngoingAttackInstance().setInstance(attackDmg, attackElement);
		// 2. Shout "on attack"
		GameInstance.queueConditionedEffects(instance.getCurrentPlayer(), ConditionType.ATTACK_DECLARE);
		// 3. Resolve the AttackEntity.
		resolveAttack();
		// 4. Shout "after attack"
		GameInstance.queueConditionedEffects(instance.getCurrentPlayer(), ConditionType.ATTACK_CONCLUDE);
	}

	public static void damage(int attackDmg, ElementType attackElement) {
		// like attack, but don't shout anything
		AttackInstance.getOngoingAttackInstance().setInstance(attackDmg, attackElement);
		resolveAttack();
	}

	public static void applyElement(CharacterCard character, ElementType element) {
		if (element == ElementType.PHYSICAL || element == ElementType.NONE)
			return;

		if (character.getAffectedByElement() == ElementType.NONE) {
			character.setAffectedByElement(element);
			return;
		}

	}

	public static void summon(SummonCard summon) {
		ArrayList<SummonCard> summonZone = GameInstance.getInstance().getCurrentPlayer().getSummonZone();
		if (!summonZone.contains(summon))
			summonZone.add(summon);
		else {
			// refreshes the summon.
			int idx = summonZone.indexOf(summon);
			summonZone.remove(idx);
			summonZone.add(idx, summon);
		}
	}

	public static void healActive(PlayArea player, int healAmount) {
		player.getActiveCharacter().setCurrentHp(healAmount + player.getActiveCharacter().getCurrentHp());
	}

	public static void healAll(PlayArea player, int healAmount) {
		for (CharacterCard character : player.getPlayerDeck().getCharacterCards()) {
			character.setCurrentHp(character.getCurrentHp() + healAmount);
		}
	}

	public static void draw(PlayArea player, int amount) {
		player.draw(amount);
	}

	public static void createDice(PlayArea player, DiceType dice) {
		player.getPlayerDice().add(dice);
		utils.SortDiceTray.sort(player);
	}

	public static void normalGainEnergy(PlayArea player) {
		player.getActiveCharacter().setCurrentEnergy(1 + player.getActiveCharacter().getCurrentEnergy());
	}
}
