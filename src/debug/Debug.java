package debug;

import data.Database;
import data.character.Ayaka;
import data.character.HilichurlShooter;
import data.summon.FrostflakeSekiNoTo;
import logic.game.GameInstance;
import utils.EffectHandler;
import utils.enums.ConditionType;

public class Debug {
	public static void main(String[] args)
	{
		//instantiate.
		Database.getDatabase();
		
		//setup GameInstance
		GameInstance instance = GameInstance.getInstance();
		
		instance.getPlayer().getPlayerDeck().addCard(new Ayaka());
		instance.getOpponent().getPlayerDeck().addCard(new HilichurlShooter());
		instance.getOpponent().getPlayerDeck().addCard(new HilichurlShooter());
		instance.getPlayer().setActiveCharacter(instance.getPlayer().getPlayerDeck().getCharacterCards().get(0));
		instance.getOpponent().setActiveCharacter(instance.getOpponent().getPlayerDeck().getCharacterCards().get(0));
		//warm effecthandler
		EffectHandler.resolveEffect();
		
		//begin the game.
		instance.initGame();
		instance.beginRollPhase();
		instance.rollDiceForRound();
		instance.beginActionPhase();
		//try out the experiment here.
		instance.setCurrentPlayer(instance.getPlayer());
		instance.getPlayer().getPlayerDeck().getCharacterCards().get(0).setCurrentEnergy(3);
		//confirmed in position.
		
		//create the summon.
		EffectHandler.summon(new FrostflakeSekiNoTo(instance.getPlayer()));
		
		System.out.println(instance.getPlayer().getSummonZone().get(0).getUsages());
		System.out.println("Queue: "+EffectHandler.getEffectQueue().size());
		
		//queue the summon's effect.
		GameInstance.queueConditionedEffects(instance.getPlayer(), ConditionType.END_PHASE);
		System.out.println("Queue: "+EffectHandler.getEffectQueue().size());
		
		//resolve the effect.
		EffectHandler.resolveEffect();
		System.out.println("Queue: "+EffectHandler.getEffectQueue().size());
		System.out.println(instance.getOpponent().getActiveCharacter().getCurrentHp());
		System.out.println(instance.getPlayer().getSummonZone().get(0).getUsages());
	}
}
