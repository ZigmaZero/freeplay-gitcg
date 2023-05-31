package component;

import img.ImageLoader;
import javafx.scene.image.ImageView;
import utils.enums.DiceType;

public class Dice extends ImageView{
	private DiceType diceType;
	private boolean selected;
	
	public Dice(DiceType diceType) {
		super(ImageLoader.getInstance().getImage("dice/"+diceType.toString().toLowerCase()+".png"));
		this.setFitWidth(30);
		this.setPreserveRatio(true);
		setDiceType(diceType);		
	}

	public DiceType getDiceType() {
		return diceType;
	}

	public void setDiceType(DiceType diceType) {
		this.diceType = diceType;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
