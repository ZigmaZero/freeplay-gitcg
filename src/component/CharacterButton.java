package component;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import logic.card.CharacterCard;

public class CharacterButton extends Button {
	private CharacterCard character;

	public CharacterButton(CharacterCard character) {
		super();
		this.character = character;

		this.setStyle("-fx-background-color: grey");
		this.setPrefSize(75, 75);
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				Parent viewCharacterPane = ((CharacterButton) arg0.getSource()).getParent().getParent();
				((CharacterViewPane) viewCharacterPane).setActiveButton(CharacterButton.this);
			}
		});
	}

	public CharacterCard getCharacter() {
		return character;
	}

	public void setCharacter(CharacterCard character) {
		this.character = character;
	}

}
