package component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import application.view.BattleController;
import application.view.UtilityScene;
import config.Config;
import img.ImageLoader;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.card.ActionCard;
import logic.game.GameInstance;
import logic.game.PlayArea;
import utils.enums.CostType;

public class HandZone extends HBox {
	
	private ArrayList<ActionCard> cardInHand;
	
	public HandZone() {
		PlayArea player = GameInstance.getInstance().getPlayer();
		cardInHand = player.getPlayerHand();
		
		this.setPrefWidth(1024);
		this.setLayoutX(0);
		this.setLayoutY(450);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);
		
		this.update();
	}
	
	public void update() {
		this.getChildren().clear();
		for (ActionCard card : cardInHand) {
			StackPane cardPane = new StackPane();
			
			AnchorPane imageAndCost = new AnchorPane();
			ImageView cardImg = (ImageView) card.render();
			cardImg.setFitWidth(Config.CHARACTER_CARD_WIDTH);
			cardImg.setPreserveRatio(true);
			HBox costContainer = new HBox();
			costContainer.setPrefHeight(30);

			Map<CostType, Integer> costCount = new HashMap<>();
			for (CostType cost : card.getCost()) {
				if (costCount.containsKey(cost)) {
					costCount.put(cost, costCount.get(cost) + 1);
				} else {
					costCount.put(cost, 1);
				}

			}
			for (CostType cost : costCount.keySet()) {
				StackPane diceAndCount = new StackPane();
				ImageView diceImage = new ImageView(
						ImageLoader.getInstance().getImage("dice/" + cost.toString().toLowerCase() + ".png"));
				diceImage.setFitWidth(30);
				diceImage.setPreserveRatio(true);
				Text count = new Text("" + costCount.get(cost));
				count.setFont(Font.font(16));
				count.setFill(Color.WHITE);
				count.setStroke(Color.BLACK);
				count.setStrokeWidth(0.3);
				diceAndCount.getChildren().addAll(diceImage, count);
				costContainer.getChildren().add(diceAndCount);
			}
			
			imageAndCost.getChildren().addAll(cardImg, costContainer);
			
			cardPane.getChildren().add(imageAndCost);
			
			cardPane.setOnMouseEntered(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					cardPane.getChildren().get(0).setOpacity(0.75);
					VBox buttons = new VBox();
					buttons.setSpacing(10);
					buttons.setAlignment(Pos.CENTER);
					Button activate = new Button("activate");
					activate.setStyle("-fx-background-color:#b0aaa0");
					activate.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent arg0) {
							if (card.getCost().size() == 0) {
								GameInstance.getInstance().playActionCard(card);
							} else if (GameInstance.getInstance().getPlayer().getPlayerDice().size() >= card.getCost().size()){
								Stage stage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
								Scene scene = new Scene(new UtilityScene(card));
								stage.setScene(scene);
								stage.show();
							}
							
						}
					});
					Button tune = new Button("tune");
					tune.setStyle("-fx-background-color:#b0aaa0");
					tune.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent arg0) {
							cardPane.setOnMouseExited(null);
							tune.setStyle("-fx-background-color:#9e9990");
							
							for (Dice dice : BattleController.getDiceTray().getPlayerDice()) {
								dice.setOnMouseClicked(new EventHandler<MouseEvent>() {

									@Override
									public void handle(MouseEvent arg0) {
										GameInstance.getInstance().tuneElement(card, dice.getDiceType());
										BattleController.getDiceTray().updatePlayerDice(GameInstance.getInstance().getPlayer().getPlayerDice());
										BattleController.getPlayerHand().update();
									}
									
								});
							}
						}
					
					});
					
					buttons.getChildren().addAll(activate, tune);
					cardPane.getChildren().add(buttons);
				}
			
			});
			cardPane.setOnMouseExited(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					cardPane.getChildren().get(0).setOpacity(1);
					cardPane.getChildren().remove(1);
				}
			
			});			
			
			this.getChildren().add(cardPane);
		}
	}
	
}
