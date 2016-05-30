package hollowmen.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * {@code LoaderClass} is a Singleton used to load images
 * 
 * @author Giordo
 */
public class LoaderClass {
	
	private LoaderClass(){}
	
	private static List<String> nameList(){
		LinkedList<String> nameList=new LinkedList<>();
		/*
		 * Aggiungere percorso per tutte le immagini. Se rimane tempo va cambiato
		 */
		nameList.add("res/images/title/title.png");
		nameList.add("res/images/title/skillTree.png");
		nameList.add("res/images/title/shop.png");
		nameList.add("res/images/title/pauseMenu.png");
		nameList.add("res/images/title/inventory");
		nameList.add("res/images/title/helpSheet2");
		nameList.add("res/images/title/helpSheet1");
		nameList.add("res/images/title/help");
		nameList.add("res/images/title/difficulty.png");
		nameList.add("res/images/title/credits.png");
		nameList.add("res/images/title/bestiary.png");
		nameList.add("res/images/title/achievement.png");
		nameList.add("res/images/mobs/slimeRed.gif");
		nameList.add("res/images/mobs/slimePurple.gif");
		nameList.add("res/images/mobs/slimeGreen.gif");
		nameList.add("res/images/mobs/slimeBlue.gif");
		nameList.add("res/images/mobs/puppetWhite");
		nameList.add("res/images/mobs/puppetRed");
		nameList.add("res/images/mobs/puppetBlack");
		nameList.add("res/images/mobs/batPurple.gif");
		nameList.add("res/images/mobs/batGray.gif");
		nameList.add("res/images/mobs/batBrown.gif");
		nameList.add("res/images/gameButtons/skillTree.png");
		nameList.add("res/images/gameButtons/inventory.png");
		nameList.add("res/images/gameButtons/consumable.png");
		nameList.add("res/images/gameButtons/ability3");
		nameList.add("res/images/gameButtons/ability2");
		nameList.add("res/images/gameButtons/ability1");
		nameList.add("res/images/buttons/RArrowOver.png");
		nameList.add("res/images/buttons/RArrowOverNA.png");
		nameList.add("res/images/buttons/RArrow.png");
		nameList.add("res/images/buttons/pButtonOver.png");
		nameList.add("res/images/buttons/pButtonNA.png");
		nameList.add("res/images/buttons/pButton.png");
		nameList.add("res/images/buttons/nodeUnlocked.png");
		nameList.add("res/images/buttons/nodeOver.png");
		nameList.add("res/images/buttons/nodeNA.png");
		nameList.add("res/images/buttons/nodeAvailable.png");
		nameList.add("res/images/buttons/LArrowOver.png");
		nameList.add("res/images/buttons/LArrowNA.png");
		nameList.add("res/images/buttons/LArrow.png");
		nameList.add("res/images/backgrounds/bodyTemplate.png");
		nameList.add("res/images/backgrounds/castle.png");
		nameList.add("res/images/backgrounds/pergamena.png");
		nameList.add("res/images/dialog.jpg");
		return nameList;
	}
	
	/**
	 * method used to load images
	 * @return {@code List<Pair<String, byte[]>>} the list of pair of
	 * name and image (as {@code byte[]})
	 */
	public static Map<String,byte[]> load(){
		Map<String,byte[]> imageMap=new HashMap<>();
		BufferedInputStream bf;
		List<String> nameList=nameList();
		try{
			for(String elem:nameList){
				bf=new BufferedInputStream(new FileInputStream (new File(elem)));
				List<Byte> list2 = new LinkedList<>();
				int c;
				while((c=bf.read())!=-1){
					list2.add((byte)c);
				}
				Byte a[]=list2.toArray(new Byte[0]);
				byte b[]=new byte[a.length];
				for(int i=0;i<a.length;i++){
					b[i]=a[i].byteValue();
				}
				imageMap.put(elem, b);
			}
		}catch(Exception e){
			System.exit(0);
		}
		return imageMap;
	}
}
