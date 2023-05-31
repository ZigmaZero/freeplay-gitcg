package component;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import logic.card.SummonCard;
import logic.game.GameInstance;

public class SummonZone extends FlowPane {
	private ArrayList<SummonCard> summons;
	
	public SummonZone() {
		this.setLayoutX(725);
		this.setLayoutY(250);
		this.setHgap(5);
		
		update();
	}
	
	public void update() {
		this.getChildren().clear();
		summons = GameInstance.getInstance().getPlayer().getSummonZone();
		for (SummonCard summon : summons) {
			ImageView summonImage = (ImageView) summon.render();
			summonImage.setFitWidth(30);
			summonImage.setPreserveRatio(true);
			this.getChildren().add(summonImage);
		}
	}

	public ArrayList<SummonCard> getSummons() {
		return summons;
	}

	public void setSummons(ArrayList<SummonCard> summons) {
		this.summons = summons;
	}
}
