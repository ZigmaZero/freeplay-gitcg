package application.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.game.GameInstance;

public class MainController{
	private static Stage stage;
	private static Scene scene;
	private static Parent root;

	@FXML
	private AnchorPane rootPane;

	public void exitApp(MouseEvent event) {
		stage = (Stage) rootPane.getScene().getWindow();
		stage.close();
	}

	public void switchToBattleScene(MouseEvent event) throws IOException {
		GameInstance.getInstance();
	
		GameInstance.getInstance().initGame();
		GameInstance.getInstance().beginRollPhase();
		GameInstance.getInstance().rollDiceForRound();
		GameInstance.getInstance().beginActionPhase();
		
		root = FXMLLoader.load(getClass().getResource("Battle.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		MainController.stage = stage;
	}

}
