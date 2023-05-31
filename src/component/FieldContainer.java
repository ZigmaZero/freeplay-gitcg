package component;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import logic.game.GameInstance;
import logic.game.PlayArea;

public class FieldContainer extends VBox{
	private Field enemyField;
	private Field playerField;
	
	public FieldContainer() {
		this.setPrefSize(1024, 317);
		this.setLayoutX(0);
		this.setLayoutY(70);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);
		
		update();
	}

	public Field getEnemyField() {
		return enemyField;
	}

	public void setEnemyField(PlayArea enemy) {
		this.enemyField = new Field(enemy, false);
		this.getChildren().add(getEnemyField());
	}

	public Field getPlayerField() {
		return playerField;
	}

	public void setPlayerField(PlayArea player) {
		this.playerField = new Field(player, true);
		this.getChildren().add(getPlayerField());
	}
	
	public void update() {
		this.getChildren().clear();
		setEnemyField(GameInstance.getInstance().getOpponent());
		setPlayerField(GameInstance.getInstance().getPlayer());
	}
	
}
