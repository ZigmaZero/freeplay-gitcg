package component;

import java.util.HashMap;
import java.util.Map;

import application.view.UtilityScene;
import img.ImageLoader;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.card.CharacterCard;
import logic.game.GameInstance;
import logic.game.PlayArea;
import utils.TalentMethod;
import utils.enums.CostType;
import utils.enums.TalentType;

public class TalentContainer extends HBox {
	
	public TalentContainer(CharacterCard character) {
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);

		update(character);

	}

	public void update(CharacterCard character) {
		this.getChildren().clear();

		for (int i = 0; i < character.getTalents().size(); i++) {
			TalentMethod talent = character.getTalents().get(i);
			VBox talentButtonContainer = new VBox();
			talentButtonContainer.setAlignment(Pos.CENTER);
			talentButtonContainer.setSpacing(5);
			
			StackPane talentButton = new StackPane();
			Circle circle = new Circle(20, Color.web("#8b5630"));
			Text talentType = new Text("" + (i+1));
			talentType.setFont(Font.font(20));
			talentType.setFill(Color.WHITE);
			talentButton.getChildren().addAll(circle, talentType);
			
			talentButton.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event arg0) {
					PlayArea player = GameInstance.getInstance().getPlayer();
					if (talent.getCost().size() == 0) {
						GameInstance.getInstance().useTalent(talent);
					} else if (talent.getTalentType().equals(TalentType.ELEMENTAL_BURST) && player.getActiveCharacter().getCurrentEnergy() != player.getActiveCharacter().getMaxEnergy()) { 
						Alert a = new Alert(AlertType.WARNING, "Your Active Character Do Not Have Enough Energy", ButtonType.OK);
						a.show();
					} else if (player.getPlayerDice().size() >= talent.getCost()
							.size()) {
						Stage stage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
						Scene scene = new Scene(new UtilityScene(character, talent));
						stage.setScene(scene);
						stage.show();
					}

				}

			});
			HBox costContainer = new HBox();
			costContainer.setAlignment(Pos.CENTER);
			Map<CostType, Integer> costCount = new HashMap<>();
			for (CostType cost : talent.getCost()) {
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

			if (talent.getTalentType().equals(TalentType.ELEMENTAL_BURST)) {
				StackPane energyAndCount = new StackPane();
				ImageView diceImage = new ImageView(ImageLoader.getInstance().getImage("dice/energy.png"));
				diceImage.setFitWidth(30);
				diceImage.setPreserveRatio(true);
				Text count = new Text("" + character.getMaxEnergy());
				count.setFont(Font.font(16));
				count.setFill(Color.WHITE);
				count.setStroke(Color.BLACK);
				count.setStrokeWidth(0.3);
				energyAndCount.getChildren().addAll(diceImage, count);
				costContainer.getChildren().add(energyAndCount);
			}

			talentButtonContainer.getChildren().addAll(talentButton, costContainer);
			this.getChildren().add(talentButtonContainer);
		}
	}

}
