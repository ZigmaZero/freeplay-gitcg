package application.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import component.DiceTray;
import component.FieldContainer;
import component.HandZone;
import component.SummonZone;
import component.TalentContainer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import logic.game.Deck;
import logic.game.GameInstance;

public class BattleController implements Initializable{
	
		
	private static FieldContainer field;
	private static HandZone playerHand;
	private static DiceTray diceTray;
	private static TalentContainer talents;
	private static SummonZone summons;
	public static AudioClip backgroundMusic = new AudioClip(ClassLoader.getSystemResource("background_music.mp3").toString());;
	
	@FXML
	private Label cardInDeck;
	@FXML
	private Pane deck;
	@FXML
	private AnchorPane battleScene;
		
	private Stage stage;
	private Scene scene;
	private Parent root;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if (!backgroundMusic.isPlaying()) {
			backgroundMusic.setCycleCount(AudioClip.INDEFINITE);
			backgroundMusic.play(0.05);
		}
		
		
		diceTray = new DiceTray();
		battleScene.getChildren().add(diceTray);
		
		playerHand = new HandZone();
		battleScene.getChildren().add(0, playerHand);
		
		talents = new TalentContainer(GameInstance.getInstance().getPlayer().getActiveCharacter());
		AnchorPane.setRightAnchor(talents, 10.0);
		AnchorPane.setBottomAnchor(talents, 10.0);
		battleScene.getChildren().add(talents);
		
		field = new FieldContainer();
		battleScene.getChildren().add(0, field);
		
		summons = new SummonZone();
		battleScene.getChildren().add(summons);
		
	}
	
	public void showDeckAmount(MouseEvent event) {
		Deck playerDeck = GameInstance.getInstance().getPlayer().getPlayerDeck();
		cardInDeck.setText(Integer.toString(playerDeck.getActionCards().size())); // set to current deck size
		deck.setOpacity(0.75);
	}
	
	public void resetDeckDisplay(MouseEvent event) {
		cardInDeck.setText("");
		deck.setOpacity(1);
	}
	
	public static FieldContainer getField() {
		return field;
	}

	public static void setField(FieldContainer field) {
		BattleController.field = field;
	}
	
	public static DiceTray getDiceTray() {
		return diceTray;
	}

	public static void setDiceTray(DiceTray diceTray) {
		BattleController.diceTray = diceTray;
	}

	public static HandZone getPlayerHand() {
		return playerHand;
	}

	public static void setPlayerHand(HandZone playerHand) {
		BattleController.playerHand = playerHand;
	}

	public static TalentContainer getTalents() {
		return talents;
	}

	public static void setTalents(TalentContainer talents) {
		BattleController.talents = talents;
	}
	
	public static SummonZone getSummons() {
		return summons;
	}

	public static void setSummons(SummonZone summons) {
		BattleController.summons = summons;
	}

	public void endCurrentRound(MouseEvent event) {
		GameInstance.getInstance().declareEndRound();
	}
	
	public void checkCurrentPlayerStatus(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Status.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
