package logic.game;

import java.util.ArrayList;
import java.util.Random;

import application.view.MainController;
import application.view.UtilityScene;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.card.CharacterCard;
import utils.EffectHandler;
import utils.Randomizer;
import utils.TalentMethod;
import utils.enums.ConditionType;
import utils.enums.TalentType;

public class AutoPlayArea extends PlayArea {
	
	public AutoPlayArea()
	{
		super();
	}
	
	private class IntentElement {
		public CharacterCard characterToAct;
		public TalentMethod talentToAct;
		
		public IntentElement(CharacterCard characterToAct, TalentMethod talentToAct)
		{
			this.characterToAct = characterToAct;
			this.talentToAct = talentToAct;
		}
	}
	
	private static final Random RNG = Randomizer.getRandomizer();
	private static ArrayList<IntentElement> intent = new ArrayList<IntentElement>();
	
	public void setIntent()
	{
		intent.clear();
		//pool all usable talents on this side of the field
		ArrayList<IntentElement> usableTalents = new ArrayList<IntentElement>();
		for(CharacterCard character : this.getPlayerDeck().getCharacterCards())
		{
			if(!character.isDefeated())
				for(TalentMethod talent : character.getTalents())
				{
					if(talent.getTalentType() != TalentType.ELEMENTAL_BURST || character.getCurrentEnergy() == character.getMaxEnergy())
					{
						usableTalents.add(new IntentElement(character, talent));
					}
				}
		}
		//all usable talents pooled, now proceeding to generate intent.
		while(intent.size() < 2)
		{
			if (usableTalents.size() == 0) {
				break;
			}
			IntentElement talent = usableTalents.get(RNG.nextInt(usableTalents.size()));
			intent.add(talent);
			if(talent.talentToAct.getTalentType() == TalentType.ELEMENTAL_BURST)
			{
				usableTalents.remove(talent);
			}
		}
	}
	
	public void switchOnDeath()
	{
		
		if(!this.getActiveCharacter().isDefeated())
			return; //don't switch if you ain't ded

		if(intent.size() == 0)
		{
			//just switch to the first character that isn't dead.
			for(CharacterCard character : this.getPlayerDeck().getCharacterCards())
			{
				if(!character.isDefeated())
				{
					this.setActiveCharacter(character);
					break;
				}
			}
			
			if(this.getActiveCharacter().isDefeated())
			{
				Stage stage = MainController.getStage();
				Scene scene = new Scene(new UtilityScene(true));
				stage.setScene(scene);
				stage.show();

			}
			return; //don't want to make it hit the bottom.
		}
		while(intent.get(0).characterToAct.isDefeated()) {
			intent.remove(0);
			if (intent.size() == 0) {
				for(CharacterCard character : this.getPlayerDeck().getCharacterCards())
				{
					if(!character.isDefeated())
					{
						this.setActiveCharacter(character);
						break;
					}
				}
				
				if(this.getActiveCharacter().isDefeated())
				{
					Stage stage = MainController.getStage();
					Scene scene = new Scene(new UtilityScene(true));
					stage.setScene(scene);
					stage.show();
					System.out.println(GameInstance.getInstance().isGameOver());

					return;
				}
				return;
			}
		}
			
		
		this.setActiveCharacter(intent.get(0).characterToAct);
		GameInstance.queueConditionedEffects(this, ConditionType.SWITCH_CHARACTER);
		EffectHandler.resolveEffect();
	}
	
	public void autoSelectAction()
	{		
		//cannot act outside of own turn, except for character death
		if(GameInstance.getInstance().getCurrentPlayer() != (PlayArea)this)
			return;
		
		if(intent.size() == 0 && !this.isEndedRound())
		{
			GameInstance.getInstance().declareEndRound();
			return;
		}
		
		while(intent.get(0).characterToAct.isDefeated()) {
			intent.remove(0);
			if (intent.size() == 0) {
				GameInstance.getInstance().declareEndRound();
				return;
			}
		}
			
		
		if(intent.get(0).characterToAct != this.getActiveCharacter())
		{
			GameInstance.getInstance().switchCharacter(intent.get(0).characterToAct);
		}
		else
		{
			GameInstance.getInstance().useTalent(intent.remove(0).talentToAct);

		}
	}
}
