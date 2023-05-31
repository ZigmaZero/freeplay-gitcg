package component;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.card.CharacterCard;
import utils.TalentMethod;

public class CharacterViewPane extends VBox {
	private ArrayList<CharacterButton> charButton;
	private CharacterButton activeButton;
	private HBox characterInfo;
	private HBox buttonContainer;
	
	public CharacterViewPane(ArrayList<CharacterCard> characters) {
		charButton = new ArrayList<>();
		
		this.setSpacing(60);
		this.setLayoutX(17);
		this.setLayoutY(17);
		
		characterInfo = new HBox();
		characterInfo.setAlignment(Pos.CENTER);
		characterInfo.setPrefSize(990, 400);
		characterInfo.setSpacing(40);
				
		buttonContainer = new HBox();
		buttonContainer.setOpacity(1);
		buttonContainer.setPrefSize(700, 75);
		buttonContainer.setSpacing(45);
		buttonContainer.setAlignment(Pos.CENTER);
		
		for(CharacterCard character : characters) {
			addButton(character);
		}
		
		this.getChildren().addAll(characterInfo, buttonContainer);
		
	}
	
	private void addButton(CharacterCard character) {
		CharacterButton newButton = new CharacterButton(character);
		charButton.add(newButton);
		if (charButton.size() == 1) {
			setActiveButton(newButton);
		}
		buttonContainer.getChildren().add(newButton);
	}

	public ArrayList<CharacterButton> getCharButton() {
		return charButton;
	}

	public void setCharButton(ArrayList<CharacterButton> charButton) {
		this.charButton = charButton;
	}

	public CharacterButton getActiveButton() {
		return activeButton;
	}

	public void setActiveButton(CharacterButton activeButton) {
		
		if (getActiveButton() != null) {
			getActiveButton().setEffect(null);
		}
				
		this.activeButton = activeButton;
		getActiveButton().setEffect(new DropShadow(15, Color.YELLOW));
		
		updateCharacterInfo(activeButton.getCharacter());

	}
	
	private void updateCharacterInfo(CharacterCard character) {
		if (characterInfo.getChildren().size() != 0) {
			characterInfo.getChildren().clear();
		}
		
		String cardImgFilePath = character.getCardImgFilePath();
		ImageView charImage = new ImageView(new Image(ClassLoader.getSystemResource(cardImgFilePath).toString(), 184 , 276, true, true, false));	
		
		VBox infoContainer = new VBox();
		infoContainer.setAlignment(Pos.CENTER);
		
		HBox basicInfo = new HBox();
		basicInfo.setAlignment(Pos.BASELINE_CENTER);
		basicInfo.setSpacing(10);
		
		Label name = new Label(character.getCharacterName());
		name.setFont(Font.font(36));
		name.setTextFill(Color.web("#edc65f"));
		Label hp = new Label("Max HP: " + character.getMaxHp());
		hp.setFont(Font.font(24));
		hp.setTextFill(Color.web("#d47a79"));
		Label energy = new Label("Max Energy: " + character.getMaxEnergy());
		energy.setFont(Font.font(24));
		energy.setTextFill(Color.web("#365f6e"));
		basicInfo.getChildren().addAll(name, hp, energy);
		
		VBox talentContainer = new VBox();
		talentContainer.setAlignment(Pos.CENTER_LEFT);
		talentContainer.setSpacing(5);
		for (TalentMethod talent : character.getTalents()) {
			Label talentLabel = new Label(talent.getDescription());
			talentLabel.setFont(Font.font(18));
			talentLabel.setTextFill(Color.WHITE);
			talentContainer.getChildren().add(talentLabel);
		}
		
		infoContainer.getChildren().addAll(basicInfo, talentContainer);
		
		characterInfo.getChildren().add(charImage);
		characterInfo.getChildren().add(infoContainer);
		
	}

}
