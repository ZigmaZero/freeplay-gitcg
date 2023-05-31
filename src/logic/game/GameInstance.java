package logic.game;

import java.util.Collections;

import application.view.BattleController;
import data.Database;
import data.character.Ayaka;
import data.character.Barbara;
import data.character.Bennett;
import data.character.HilichurlFighter;
import data.character.HilichurlShooter;
import logic.card.ActionCard;
import logic.card.CharacterCard;
import utils.EffectHandler;
import utils.TalentMethod;
import utils.enums.ConditionType;
import utils.enums.DiceType;
import utils.enums.ElementType;
import utils.enums.TalentType;

public class GameInstance {
	
	//fields
	private PlayArea player;
	private AutoPlayArea opponent;
	private PlayArea currentPlayer;
	private int roundCount;
	private boolean isGameOver;
	private boolean isOpenGameState;
	private int endRoundCalls;
	
	//singleton
	private static GameInstance instance;
	public static GameInstance getInstance() {
		if (instance == null) {
			instance = new GameInstance();
		}
		return instance;
	}
	
	//getters/setters
	public int getRoundCount() {
		return roundCount;
	}
	
	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public PlayArea getPlayer() {
		return player;
	}

	public void setPlayer(PlayArea player) {
		this.player = player;
	}

	public AutoPlayArea getOpponent() {
		return opponent;
	}

	public void setOpponent(AutoPlayArea opponent) {
		this.opponent = opponent;
	}

	public PlayArea getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(PlayArea currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public boolean isOpenGameState() {
		return isOpenGameState;
	}

	public void setOpenGameState(boolean isOpenGameState) {
		this.isOpenGameState = isOpenGameState;
	}

	public PlayArea getNextPlayer() {
		if(currentPlayer == player)
			return opponent;
		return player;
	}
	
	public GameInstance() {
		PlayArea newPlayer = new PlayArea();
		setPlayer(newPlayer);
		setOpponent(new AutoPlayArea());
		setCurrentPlayer(newPlayer);
		isGameOver = false;
		setOpenGameState(false);
	}
	
	//logic manipulation methods (these are called when requesting a certain interaction.)
	public static void queueConditionedEffects(PlayArea player, ConditionType condition)
	{
		instance.getCurrentPlayer().queueConditionedEffects(player, condition);
		instance.getNextPlayer().queueConditionedEffects(player, condition);
	}
	
	//game control methods (call these to progress the game.)
	public void initGame() {
		//called once when starting a new game.
		GameInstance.getInstance().getPlayer().resetPlayer();
		GameInstance.getInstance().getOpponent().resetPlayer();
		
		PlayArea player = GameInstance.getInstance().getPlayer();
		CharacterCard character1 = new Bennett();
		CharacterCard character2 = new Ayaka();
		CharacterCard character3 = new Barbara();
		player.getPlayerDeck().addCard(character1);
		player.getPlayerDeck().addCard(character2);
		player.getPlayerDeck().addCard(character3);
		player.setActiveCharacter(character2);
		character1.setOwner(player);
		character2.setOwner(player);
		character3.setOwner(player);
		
		AutoPlayArea enemy = GameInstance.getInstance().getOpponent();
		enemy.getPlayerDeck().getCharacterCards().clear();
		CharacterCard enemy1 = new HilichurlShooter();
		enemy1.setOwner(enemy);
		CharacterCard enemy2 = new HilichurlFighter();
		enemy2.setOwner(enemy);
		CharacterCard enemy3 = new HilichurlShooter();
		enemy3.setOwner(enemy);
		enemy.getPlayerDeck().getCharacterCards().add(enemy1);
		enemy.getPlayerDeck().getCharacterCards().add(enemy2);
		enemy.getPlayerDeck().getCharacterCards().add(enemy3);
		enemy.setActiveCharacter(enemy2);
		enemy.setIntent();

		for (int i = 0; i < 30; i++) {
			player.getPlayerDeck().addCard(Database.getDatabase().getRandomActionCard());
		}
		
		instance.roundCount = 0;
		GameInstance.getInstance().rollDiceForRound();
		Collections.shuffle(instance.getPlayer().getPlayerDeck().getActionCards());
		instance.getPlayer().draw(5);
	}
	
	public void beginRollPhase()
	{
		this.endRoundCalls = 0;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		player.setEndedRound(false);
		opponent.setEndedRound(false);
		queueConditionedEffects(getCurrentPlayer(), ConditionType.ROLL_PHASE);
		queueConditionedEffects(getNextPlayer(), ConditionType.ROLL_PHASE);
		EffectHandler.resolveEffect();
	}
	
	public void rollDiceForRound()
	{	
		instance.getPlayer().getPlayerDice().clear();
		for(int i=0;i<8;i++)
		{
			instance.getPlayer().getPlayerDice().add(DiceType.randomDiceType());
		}
		utils.SortDiceTray.sort(instance.getPlayer());
	}
	
	public void beginActionPhase()
	{
		queueConditionedEffects(getCurrentPlayer(), ConditionType.ACTION_PHASE);
		queueConditionedEffects(getNextPlayer(), ConditionType.ACTION_PHASE);
		EffectHandler.resolveEffect();
		setOpenGameState(true);
	}
	
	public void passTurn()
	{
		if(!getNextPlayer().isEndedRound())
			setCurrentPlayer(getNextPlayer());

		getOpponent().autoSelectAction();
	}
	
	public void switchCharacter(CharacterCard character)
	{
		if(character.isDefeated())
			return; //no.
		if(getCurrentPlayer().getPlayerDeck().getCharacterCards().contains(character))
		{
			getCurrentPlayer().setActiveCharacter(character);
			queueConditionedEffects(getCurrentPlayer(), ConditionType.SWITCH_CHARACTER);
			EffectHandler.resolveEffect();
			
			BattleController.getField().update();
			BattleController.getPlayerHand().update();
			BattleController.getDiceTray().updatePlayerDice(GameInstance.getInstance().getPlayer().getPlayerDice());
			BattleController.getTalents().update(getPlayer().getActiveCharacter());
			passTurn();
			
		}
	}
	
	public void useTalent(TalentMethod talent)
	{
		if(talent.getTalentType() == TalentType.ELEMENTAL_BURST)
		{
			instance.getCurrentPlayer().getActiveCharacter().setCurrentEnergy(0);
		}
		getCurrentPlayer().getActiveCharacter().activate(talent);
		
		BattleController.getField().update();
		BattleController.getDiceTray().updatePlayerDice(GameInstance.getInstance().getPlayer().getPlayerDice());
		BattleController.getSummons().update();
		passTurn();
	}
	
	public void playActionCard(ActionCard card)
	{
		getCurrentPlayer().playActionCard(card);
		BattleController.getPlayerHand().update();
		BattleController.getField().update();
		BattleController.getDiceTray().updatePlayerDice(GameInstance.getInstance().getPlayer().getPlayerDice());
	}
	
	public void tuneElement(ActionCard card, DiceType dice)
	{
		if(dice.toString() == getCurrentPlayer().getActiveCharacter().getCharacterElement().toString())
			return;
		
		if(getCurrentPlayer().getActiveCharacter().getCharacterElement() == ElementType.PHYSICAL)
			return;
		
		if(getCurrentPlayer().getActiveCharacter().getCharacterElement() == ElementType.NONE)
			return;
		//note: the dice must exist in the dicetray.
		getCurrentPlayer().getPlayerHand().remove(card);
		if(getCurrentPlayer().getPlayerDice().remove(dice))
			getCurrentPlayer().getPlayerDice().add(DiceType.valueOf(getCurrentPlayer().getActiveCharacter().getCharacterElement().toString()));
		utils.SortDiceTray.sort(currentPlayer);
	}
	
	public void declareEndRound()
	{
		getCurrentPlayer().setEndedRound(true);
		endRoundCalls++;
		if(endRoundCalls > 1)
			beginEndPhase();
		else
			passTurn();
	}
	
	public void beginEndPhase()
	{	
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setOpenGameState(false);
		player.setEndedRound(false);
		opponent.setEndedRound(false);
		opponent.setIntent();
		this.endRoundCalls = 0;
		passTurn();
		queueConditionedEffects(getCurrentPlayer(), ConditionType.END_PHASE);
		queueConditionedEffects(getNextPlayer(), ConditionType.END_PHASE);
		EffectHandler.resolveEffect();
		player.draw(2);
		beginRollPhase();
		rollDiceForRound();
		beginActionPhase();
		roundCount++;
		BattleController.getPlayerHand().update();
		BattleController.getField().update();
		BattleController.getDiceTray().updatePlayerDice(GameInstance.getInstance().getPlayer().getPlayerDice());
	}
	
}
