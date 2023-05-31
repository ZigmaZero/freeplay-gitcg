package logic.card;

import java.util.ArrayList;

import img.ImageLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import logic.game.PlayArea;
import utils.EffectMethod;
import utils.RenderObject;

public class SummonCard implements RenderObject {

	private String name;
	private int id;
	private String cardImgFilePath;
	private ArrayList<EffectMethod> effects = new ArrayList<EffectMethod>();
	private int usages;
	private boolean isUsageBased;
	private PlayArea owner;
	
	public PlayArea getOwner() {
		return owner;
	}

	public SummonCard(PlayArea owner)
	{
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardImgFilePath() {
		return cardImgFilePath;
	}

	
	public void setCardImgFilePath(String cardImgFilePath) {
		this.cardImgFilePath = cardImgFilePath;
	}

	public ArrayList<EffectMethod> getEffects() {
		return effects;
	}

	public void setEffects(ArrayList<EffectMethod> effects) {
		this.effects = effects;
	}

	public int getUsages() {
		return usages;
	}

	public void setUsages(int usages) {
		this.usages = usages;
	}

	public boolean isUsageBased() {
		return isUsageBased;
	}

	public void setUsageBased(boolean isUsageBased) {
		this.isUsageBased = isUsageBased;
	}

	@Override
	public Node render() {
		return new ImageView(ImageLoader.getInstance().getImage(cardImgFilePath));
	}

}
