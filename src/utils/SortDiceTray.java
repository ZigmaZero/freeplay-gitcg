package utils;

import java.util.ArrayList;
import java.util.Comparator;

import logic.card.CharacterCard;
import logic.game.PlayArea;
import utils.enums.DiceType;

public class SortDiceTray {
	public static void sort(PlayArea player)
	{
		ArrayList<DiceType> playerDice = player.getPlayerDice();
		//sort by first, second, and third character's element
		//and then sort by amount of the same dice in the dicetray
		Comparator<DiceType> diceComparator = new Comparator<DiceType>() {
			public int compare(DiceType d1, DiceType d2)
			{
				if(d1.equals(d2))
				{
					return 0;
				}
				if(d1.equals(DiceType.OMNI))
					return -1;
				if(d2.equals(DiceType.OMNI))
					return 1;
				if(d1.toString().equals(player.getActiveCharacter().getCharacterElement().toString()))
					return -1;
				if(d2.toString().equals(player.getActiveCharacter().getCharacterElement().toString()))
					return 1;
				for(CharacterCard character : player.getPlayerDeck().getCharacterCards())
				{
					if(d1.toString().equals(character.getCharacterElement().toString()))
						return -1;
					if(d2.toString().equals(character.getCharacterElement().toString()))
						return 1;
				}
				return d1.compareTo(d2);
			}
		};
		playerDice.sort(diceComparator);
	}
}
