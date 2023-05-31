package application.view;

import java.io.IOException;
import java.util.ArrayList;

import component.Dice;
import config.Config;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.card.ActionCard;
import logic.card.CharacterCard;
import logic.game.GameInstance;
import utils.CostHandler;
import utils.TalentMethod;
import utils.enums.DiceType;

public class UtilityScene extends HBox {
	private Stage stage;
	private Scene scene;
	private Parent root;
		
	public UtilityScene(CharacterCard character, TalentMethod talent) {		
		setupScene();
		
		selectDiceForCost(character, talent);
	}
	
	public UtilityScene(ActionCard card) {
		setupScene();
		
		selectDiceForCost(card);
	}
	
	public UtilityScene(ArrayList<CharacterCard> characters) {
		setupScene();
		
		selectActiveCharacter(characters);
	}
	
	public UtilityScene(boolean playerWin) {
		setupScene();
		BattleController.backgroundMusic.stop();
		
		VBox container = new VBox();
		container.setAlignment(Pos.CENTER);
		container.setSpacing(20);
		Label gameOverText = new Label("YOU WIN!");
		gameOverText.setFont(Font.font(100));
		gameOverText.setTextFill(Color.WHITE);
		Button backToMenu = new Button("back to main menu");
		backToMenu.setStyle("-fx-background-color: #4a4d54");
		backToMenu.setFont(Font.font(24));
		backToMenu.setTextFill(Color.WHITE);
		backToMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				try {
					root = FXMLLoader.load(getClass().getResource("Main.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				stage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
		
		});
		
		container.getChildren().addAll(gameOverText, backToMenu);
		this.getChildren().add(container);
	}
	
	public void selectActiveCharacter(ArrayList<CharacterCard> characters) {
		for (CharacterCard character : characters) {
			if (character.getCurrentHp() > 0) {
				ImageView characterImage = (ImageView) character.render();
				characterImage.setFitWidth(Config.CHARACTER_CARD_WIDTH*2);
				characterImage.setPreserveRatio(true);
				characterImage.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						GameInstance.getInstance().switchCharacter(character);
						try {
							root = FXMLLoader.load(getClass().getResource("Battle.fxml"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						stage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
						scene = new Scene(root);
						stage.setScene(scene);
						stage.show();
					}
				
				});
				this.getChildren().add(characterImage);
			}
			
		}
		
		if (this.getChildren().size() == 0) {
			GameInstance.getInstance().setGameOver(true);
			BattleController.backgroundMusic.stop();
			
			VBox container = new VBox();
			container.setAlignment(Pos.CENTER);
			container.setSpacing(20);
			Label gameOverText = new Label("GAME OVER!");
			gameOverText.setFont(Font.font(100));
			gameOverText.setTextFill(Color.WHITE);
			Button backToMenu = new Button("back to main menu");
			backToMenu.setStyle("-fx-background-color: #4a4d54");
			backToMenu.setFont(Font.font(24));
			backToMenu.setTextFill(Color.WHITE);
			backToMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					
					try {
						root = FXMLLoader.load(getClass().getResource("Main.fxml"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					stage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				}
			
			});
			
			container.getChildren().addAll(gameOverText, backToMenu);
			this.getChildren().add(container);
		}
	}
	
	public void selectDiceForCost(CharacterCard character, TalentMethod talent) {
		ArrayList<DiceType> selectedDice = new ArrayList<>();
		
		for (Dice dice : BattleController.getDiceTray().getPlayerDice()) {
			
			dice.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					if (!dice.isSelected()) {
						dice.setSelected(true);
						selectedDice.add(dice.getDiceType());
						dice.setEffect(new DropShadow(10, Color.YELLOW));
					} else {
						dice.setSelected(false);
						selectedDice.remove(dice.getDiceType());
						dice.setEffect(null);
					}
					
					if (selectedDice.size() == talent.getCost().size()) {
						try {
							root = FXMLLoader.load(getClass().getResource("Battle.fxml"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						stage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
						scene = new Scene(root);
						stage.setScene(scene);
						stage.show();
						
						if (CostHandler.isCostPayable(talent.getCost(), selectedDice)) {
							CostHandler.fulfillCost(talent.getCost(), selectedDice);
							GameInstance.getInstance().useTalent(talent);
							
						}
						
						
					}

				}
				
			});
			
			this.getChildren().add(dice);
		}
		
	}
	
	public void selectDiceForCost(ActionCard card) {
		ArrayList<DiceType> selectedDice = new ArrayList<>();
		
		for (Dice dice : BattleController.getDiceTray().getPlayerDice()) {
			dice.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					if (!dice.isSelected()) {
						dice.setSelected(true);
						selectedDice.add(dice.getDiceType());
						dice.setEffect(new DropShadow(10, Color.YELLOW));
					} else {
						dice.setSelected(false);
						selectedDice.remove(dice.getDiceType());
						dice.setEffect(null);
					}
					
					if (selectedDice.size() == card.getCost().size()) {
						if (CostHandler.isCostPayable(card.getCost(), selectedDice)) {
							CostHandler.fulfillCost(card.getCost(), selectedDice);
							GameInstance.getInstance().playActionCard(card);
						}
						try {
							root = FXMLLoader.load(getClass().getResource("Battle.fxml"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						stage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
						scene = new Scene(root);
						stage.setScene(scene);
						stage.show();
						
					}					
				}
				
			});
			
			this.getChildren().add(dice);
		}
	}
	
	private void setupScene() {
		this.setPrefSize(1024, 576);
		this.setStyle("-fx-background-color:black");
		this.setAlignment(Pos.CENTER);
		this.setSpacing(25);
	}
	
}
