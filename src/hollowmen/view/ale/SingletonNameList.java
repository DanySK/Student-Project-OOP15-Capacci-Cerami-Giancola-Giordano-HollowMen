package hollowmen.view.ale;

import java.util.LinkedList;
import java.util.List;

/**
 * The {@code SingletonNameList} class uses the design pattern Singleton
 * in order to create just one instance of this Class. It's thread-safe.
 * 
 * @author Alessia
 *
 */
public class SingletonNameList {
	private static final SingletonNameList singleton=new SingletonNameList();
	private List<String> nameList;
	
	//Private constructor avoid the instance of objects from external classes.
	private SingletonNameList(){
		nameList=new LinkedList<>();
		nameList.add("bat1");
		nameList.add("batGray");
		nameList.add("batPurple");
		nameList.add("blueSlimeBoss");
		nameList.add("puppet1");
		nameList.add("puppetRed");
		nameList.add("puppetWhite");
		nameList.add("slimeBlue");
		nameList.add("slime1");
		nameList.add("slimePurple");
		nameList.add("slimeRed");
		nameList.add("violetSlimeBoss");
		nameList.add("ability1");
		nameList.add("ability2");
		nameList.add("ability3");
		nameList.add("consumable");
		nameList.add("inventory");
		nameList.add("skillTree");
		nameList.add("door");
		nameList.add("treasureChest");
		nameList.add("game");
		nameList.add("hero");
		nameList.add("blueSlime");
		nameList.add("coinBag");
		nameList.add("inventoryLobby");
		nameList.add("skillTreeLobby");
	}
	
	/**
	 * The method {@code SingletonNameList} returns the instance of this Singleton.
	 * 
	 * @return
	 */
	public static SingletonNameList getSingletonNameList(){
		return singleton;
	}
	/**
	 * The method {@code getNameList} gets the name of all the images contained 
	 * in the {@link SingletonNameList} 
	 * 
	 * @return
	 */
	public List<String> getNameList(){
		return this.nameList;
	}
}
