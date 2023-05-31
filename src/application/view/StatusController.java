package application.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import component.CharacterViewPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.game.GameInstance;

public class StatusController implements Initializable{
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private AnchorPane statusPane;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		statusPane.getChildren().add(0, new CharacterViewPane(GameInstance.getInstance().getPlayer().getPlayerDeck().getCharacterCards()));
	}
	
	public void switchToBattleScene(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Battle.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
