package component;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import logic.game.GameInstance;
import utils.enums.DiceType;

public class DiceTray extends VBox{
	private ArrayList<Dice> playerDice;
	
	public DiceTray() {
		playerDice = new ArrayList<>();
		
		this.setPrefSize(50, 317);
		this.setLayoutX(974);
		this.setLayoutY(88);
		this.setSpacing(10);
		this.setPadding(new Insets(5));
		this.setAlignment(Pos.TOP_CENTER);
		this.setStyle("-fx-background-color: #4e5a5f");
		
		updatePlayerDice(GameInstance.getInstance().getPlayer().getPlayerDice());
	}

	public ArrayList<Dice> getPlayerDice() {
		return playerDice;
	}

	public void updatePlayerDice(ArrayList<DiceType> playerDiceType) {
		this.getChildren().clear();
		
		ArrayList<Dice> updatedPlayerDice = new ArrayList<>();
		
		for (DiceType diceType : playerDiceType) {
			updatedPlayerDice.add(new Dice(diceType));
		}
		
		this.playerDice = updatedPlayerDice;
		this.getChildren().addAll(updatedPlayerDice);
	}
	
	public void spendDice(int index) {
		getPlayerDice().remove(index);
		this.getChildren().remove(index);
	}
	
}
