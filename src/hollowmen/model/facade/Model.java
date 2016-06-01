package hollowmen.model.facade;

import java.util.List;

import hollowmen.enumerators.Difficulty;
import hollowmen.model.utils.GameOverException;

/**
 * 
 * @author Giordo
 *
 */
public interface Model {
	public void setup();
	public void goTo(int floor);
	public void update(long deltaTime)throws GameOverException;
	public void moveHero(String move);
	public void heroAction(String action);
	public List<DrawableRoomEntity> getDrawableRoomEntity();
	public List<InformationDealer> getPokedex();
	public List<InformationDealer> getInventory();
	public List<InformationDealer> getShop();
	public void itemEquip(InformationDealer item,String action);
	public void itemUnequip(InformationDealer item,String action);
	public void itemBuy(InformationDealer item,String action);
	public void itemSell(InformationDealer item,String action);
	public void setDifficulty(Difficulty difficulty);
}
