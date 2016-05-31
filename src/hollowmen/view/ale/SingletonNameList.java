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
	
	private static List<String> nameList;
	
	//Private constructor avoid the instance of objects from external classes.
	private SingletonNameList(){
		nameList=new LinkedList<>();
		nameList.add("batBrown");
		nameList.add("batGray");
		nameList.add("batPurple");
		nameList.add("blueSlimeBoss");
		nameList.add("puppetBlack");
		nameList.add("puppetRed");
		nameList.add("puppetWhite");
		nameList.add("slimeBlue");
		nameList.add("slimeGreen");
		nameList.add("slimePurple");
		nameList.add("slimeRed");
		nameList.add("violetSlimeBoss");
		nameList.add("ability1");
		nameList.add("ability2");
		nameList.add("ability3");
		nameList.add("consumable");
		nameList.add("inventory");
		nameList.add("skillTree");
	}
	
	/**
	 * The method {@code getNameList} gets the name of all the images contained 
	 * in the {@link SingletonNameList} 
	 * 
	 * @return
	 */
	public static List<String> getNameList(){
		new SingletonNameList();
		return nameList;
	}
}
