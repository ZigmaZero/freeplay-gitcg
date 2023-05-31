package logic.card;

import java.util.ArrayList;

import img.ImageLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import logic.game.Deck;
import logic.game.PlayArea;
import utils.EffectHandler;
import utils.EffectMethod;
import utils.RenderObject;
import utils.enums.CardCategoryType;
import utils.enums.CardSubCategoryType;
import utils.enums.CostType;

public class ActionCard implements RenderObject {

	//name, cost, effect
	private String name;
	private ArrayList<CostType> cost = new ArrayList<CostType>();
	private EffectMethod effect;
	//card categories
	private int id;
	private CardCategoryType cardCategory;
	private CardSubCategoryType cardSubCategory;
	//render parameters
	private String cardImgFilePath;
	private String description;
	
	private PlayArea owner;
	
	public boolean canBeAdded(Deck deck)
	{
		return true;
	}
	
	public CardCategoryType getCardCategory() {
		return cardCategory;
	}

	public void setCardCategory(CardCategoryType cardCategory) {
		this.cardCategory = cardCategory;
	}

	public CardSubCategoryType getCardSubCategory() {
		return cardSubCategory;
	}

	public void setCardSubCategory(CardSubCategoryType cardSubCategory) {
		this.cardSubCategory = cardSubCategory;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public ArrayList<CostType> getCost() {
		return cost;
	}

	public void setCost(ArrayList<CostType> cost) {
		this.cost = cost;
	}
	
	public String getCardImgFilePath() {
		return cardImgFilePath;
	}

	public void setCardImgFilePath(String cardImgFilePath) {
		this.cardImgFilePath = cardImgFilePath;
	}
	
	public PlayArea getOwner() {
		return owner;
	}

	public void setOwner(PlayArea owner) {
		this.owner = owner;
		this.getEffect().setOwner(owner);
	}

	public EffectMethod getEffect() {
		return effect;
	}

	public void setEffect(EffectMethod effect) {
		this.effect = effect;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString()
	{
		return this.getName();
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof ActionCard)
			return ((ActionCard) o).getId() == this.getId();
		return false;
	}

	@Override
	public Node render() {
		return new ImageView(ImageLoader.getInstance().getImage(cardImgFilePath));
	}
	
	public int activate() {
		//on activate(), queue the card's effect.
		if(effect != null)
			EffectHandler.queueEffect(effect);
		return 0;
	}

}