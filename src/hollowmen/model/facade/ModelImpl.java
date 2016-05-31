package hollowmen.model.facade;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import hollowmen.enumerators.ActorState;
import hollowmen.enumerators.Difficulty;
import hollowmen.model.Enemy;
import hollowmen.model.Item;
import hollowmen.model.Modifier;
import hollowmen.model.Hero;
import hollowmen.model.Parameter;
import hollowmen.model.dungeon.DungeonSingleton;
import hollowmen.model.dungeon.Initializer;
import hollowmen.model.utils.GameOverException;
import hollowmen.utilities.Pair;

/**
 * 
 * @author Giordo
 *
 */
public class ModelImpl implements Model{
	
	DungeonSingleton dungeon=DungeonSingleton.getInstance();
	Hero hero=dungeon.getHero();
	
	public void setup(){
		new Initializer();
	}
	
	public void update(long deltaTime) throws GameOverException {
		this.dungeon.update(deltaTime);
	}
	
	public void moveHero(String move){
		this.hero.move(move);
	}
	
	public void heroAction(String action) {
		this.hero.performAction(action);
	}

	public List<DrawableRoomEntity> getDrawableRoomEntity() {
		List<DrawableRoomEntity> drawable=new LinkedList<>();
		for(Enemy re: this.dungeon.getCurrentRoom().getEnemies()){
			ActorState sta=ActorState.valueOf(re.getState().toUpperCase());
			
			drawable.add(new DrawableRoomEntityImpl(
					re.getInfo().getName(),
					new Point2DImpl((int)(re.getBody().getLocalCenter().x-re.getLength()/2),
							(int)(re.getBody().getLocalCenter().y+re.getHeight()/2)),
					re.isFacingRight(),
					sta));
		}
		return drawable;
	}

	public List<InformationDealer> getPokedex() {
		List<InformationDealer> info=new LinkedList<>();
		Map<String,Double> param=new HashMap<>();
		for(Enemy en: this.dungeon.getPokedex().getEnemyMet()){
			
			for(Map.Entry<String, Parameter> map:en.getParameters().entrySet()){
				param.put(map.getKey(),(Double)map.getValue().getValue());
			}
			info.add(new InformationDealerImpl(en.getInfo().getName(),
					en.getInfo().getDescription().orElse(""),
					param,
					en.getState(),
					1,
					""));
			param.clear();
		}
		return info;
	}
	
	public List<InformationDealer> getInventory() {
		List<InformationDealer> info=new LinkedList<>();
		Map<String,Double> param=new HashMap<>();
		for(Pair<Item,Integer> it: this.hero.getInventory().getAllItem()){
			for(Modifier m:it.getX().getModifiers().values()){
				param.put(m.getParameter().getInfo().getName(),m.getParameter().getValue());
			}
			info.add(new InformationDealerImpl(it.getX().getInfo().getName(),
					it.getX().getInfo().getDescription().orElse(""),
					param,
					it.getX().getState().name(),
					it.getY(),
					it.getX().getHeroClassEquippable()));
			param.clear();
		}
		return info;
	}
	
	public List<InformationDealer> getShop() {
		List<InformationDealer> info=new LinkedList<>();
		Map<String,Double> param=new HashMap<>();
		for(Item it: this.dungeon.getShop().getShopItem()){
			for(Modifier m:it.getModifiers().values()){
				param.put(m.getParameter().getInfo().getName(),m.getParameter().getValue());
			}
			info.add(new InformationDealerImpl(it.getInfo().getName(),
					it.getInfo().getDescription().orElse(""),
					param,
					it.getState().name(),
					1,
					it.getHeroClassEquippable()));
			param.clear();
		}
		return info;
	}

	public void itemEquip(InformationDealer item, String action) {
		try{
			for(Pair<Item,Integer> it: this.hero.getInventory().getAllItem()){
				if(it.getX().getInfo().getName()==item.getName()){
					this.hero.equipItem(it.getX());
				}
			}
		}catch(Exception e){}
	}
	
	public void itemUnequip(InformationDealer item, String action) {
		try{
			for(Pair<Item,Integer> it: this.hero.getInventory().getAllItem()){
				if(it.getX().getInfo().getName()==item.getName()){
					this.hero.unequipItem(it.getX());
				}
			}
		}catch(Exception e){}
	}
	
	public void itemBuy(InformationDealer item, String action) {
		try{
			for(Pair<Item,Integer> it: this.hero.getInventory().getAllItem()){
				if(it.getX().getInfo().getName()==item.getName()){
					this.hero.buyItem(it.getX());
				}
			}
		}catch(Exception e){}
	}
	
	public void itemSell(InformationDealer item, String action){
		try{
			for(Pair<Item,Integer> it: this.hero.getInventory().getAllItem()){
				if(it.getX().getInfo().getName()==item.getName()){
					this.hero.sellItem(it.getX());
				}
			}
		}catch(Exception e){}
	}
	
	public void setDifficulty(Difficulty difficulty){
		this.dungeon.setDifficulty(difficulty);
	}
	
}
