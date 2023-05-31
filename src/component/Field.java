package component;

import config.Config;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.card.CharacterCard;
import logic.game.GameInstance;
import logic.game.PlayArea;

public class Field extends HBox{
	private ArrayList<CharacterCard> characters;
	private ImageView activeCharacter;
	private boolean playerField;

	public Field(PlayArea player, boolean playerField) {
		this.setPrefSize(Config.CHARACTER_FIELD_WIDTH, Config.CHARACTER_FIELD_HEIGHT);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(Config.CHARACTER_FIELD_SPACING);
		
		setPlayerField(playerField);
		setCharacters(player);
	}

	public ArrayList<CharacterCard> getCharacters() {
		return characters;
	}

	public void setCharacters(PlayArea player) {
		this.characters = player.getPlayerDeck().getCharacterCards();
		
		for (CharacterCard card : characters) {
			
			VBox imageWithHealth = new VBox();
			imageWithHealth.setAlignment(Pos.TOP_CENTER);
			ImageView cardImage1 = (ImageView) card.render();
			
			cardImage1.setFitWidth(Config.CHARACTER_CARD_WIDTH);
			cardImage1.setPreserveRatio(true);
			
			if (card.equals(player.getActiveCharacter())) {
				setActiveCharacter(cardImage1);
			}
			
			if (card.getCurrentHp() <= 0) {
				cardImage1.setEffect(new ColorAdjust(0, 0, -0.4, 0));
			} else {
				cardImage1.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						if (isPlayerField() && !player.getActiveCharacter().equals(card)) {
							Alert a = new Alert(AlertType.CONFIRMATION);
							a.setTitle("");
							a.setHeaderText("Change active character");
							a.setContentText("Are you sure u want to change to this character?");
							if (a.showAndWait().get() == ButtonType.OK) {
								GameInstance.getInstance().switchCharacter(card);
								updateActiveCharacter(cardImage1);
							}
							
						}
					}
					
				});
			}
			
			HBox statContainer = new HBox();
			statContainer.setAlignment(Pos.CENTER);
			statContainer.setSpacing(10);
			
			Text health = new Text(card.getCurrentHp() + "/" + card.getMaxHp());
			health.setFill(Color.WHITE);
			health.setEffect(new DropShadow(5, Color.RED));
			
			Text energy = new Text(card.getCurrentEnergy() + "/" + card.getMaxEnergy());
			energy.setFill(Color.WHITE);
			energy.setEffect(new DropShadow(5, Color.BLUE));
			
			statContainer.getChildren().addAll(health, energy);
			
			imageWithHealth.getChildren().add(cardImage1);
			imageWithHealth.getChildren().add(statContainer);
			
			this.getChildren().add(imageWithHealth);
		}
	}

	public ImageView getActiveCharacter() {
		return activeCharacter;
	}

	public void setActiveCharacter(ImageView activeCharacter) {
		this.activeCharacter = activeCharacter;
		activeCharacter.setEffect(new DropShadow(5, Color.YELLOW));
	}

	public boolean isPlayerField() {
		return playerField;
	}

	public void setPlayerField(boolean playerField) {
		this.playerField = playerField;
	}
	
	public void updateActiveCharacter(ImageView card) {
		if (getActiveCharacter() != null && getActiveCharacter().getEffect() instanceof DropShadow) {
			getActiveCharacter().setEffect(null);
		}
		
		setActiveCharacter(card);
	}
	
}
