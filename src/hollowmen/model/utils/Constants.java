package hollowmen.model.utils;

import java.awt.Rectangle;


public class Constants {

	public static final Rectangle WORLD_SIZE = new Rectangle(800, 600);
	
	//Counting also the zero so 0-1-2 = 3 child rooms;
	public static final int CHILDROOMQUANTITY = 2;
	
	public static final int SKILLPOINTS_ONLEVELUP = 1;
	
	public static final int STATPOINTS_ONLEVELUP = 2;
	
	public static final int SKILLPOINTS_FORUPGRADE = 3;
	
	public static final Rectangle DOOR_SIZE = new Rectangle(50, 100);

	public static final Rectangle HERO_SIZE = new Rectangle(80, 100);
	
	
	//TRESURE CHEST
	
	public static final Rectangle TREASURE_SIZE = new Rectangle(60, 80);
	
	public static final int TREASURE_FLATFLOOR = 100;
	
	public static final int TREASURE_FLATROOM = 10;
	
	public static final double TREASURE_ITEMCHANCE = 0.5;
}
