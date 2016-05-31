package hollowmen.controller;

import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

import hollowmen.enumerators.ClassType;
import hollowmen.enumerators.InputCommand;
import hollowmen.enumerators.InputMenu;
import hollowmen.model.Information;
import hollowmen.model.Item;
import hollowmen.model.Modifier;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.dungeon.ModifierImpl;
import hollowmen.model.Item.ItemState;
import hollowmen.model.facade.InformationDealer;
import hollowmen.model.facade.Model;
import hollowmen.model.item.ItemBuilder;
import hollowmen.model.item.ItemImpl;
import hollowmen.utilities.Pair;
import hollowmen.view.View;
import hollowmen.view.ViewImpl;

/**
 * 
 * @author Giordo
 *
 */
public class Controller implements ViewObserver {
	
	private LinkedList<InputMenu> inputMenuList=new LinkedList<>();
	private LinkedList<InputCommand> inputCommandList=new LinkedList<>();
	private Pair<InputCommand,InformationDealer> mapInputCommand=null;
	private View view;
	private Model model;
	private int counterBack=0;
	private InputMenu[] menuBack=new InputMenu[10];
	
	public Controller(){
		
		
		view=new ViewImpl(800,600,this);
		
		view.takeFile(LoaderClass.load());
		view.drawMenu(InputMenu.MAIN, Optional.empty());
		
		//model=new ModelImpl();
		
		menuInputManager();
		
	}
	
	private void backInc(InputMenu m){
		this.counterBack++;
		for(int k=this.counterBack-1; k>0; k--){
			if(k!=9){
				menuBack[k]=menuBack[k-1];
			}
		}
		this.menuBack[0]=m;
	}
	
	private void backDec(){
		for(int k=0;k<this.counterBack-1; k++){
			menuBack[k]=menuBack[k+1];
		}
		this.counterBack--;
	}
	
	private void menuChoice(){
		backInc(inputMenuList.getFirst());
		switch(inputMenuList.getFirst()){
		case MAIN:{
			view.drawMenu(InputMenu.MAIN, Optional.empty());
			inputMenuList.clear();
			break;
		}case CLASS:{
			view.drawMenu(InputMenu.CLASS, Optional.empty());
			inputMenuList.clear();
			break;
		}case DIFFICULTY:{
			view.drawMenu(InputMenu.DIFFICULTY, Optional.empty());
			inputMenuList.clear();
			break;
		}case HELP:{
			view.drawMenu(InputMenu.HELP, Optional.empty());
			inputMenuList.clear();
			break;
		}case PAUSE:{
			view.drawMenu(InputMenu.PAUSE, Optional.empty());
			inputMenuList.clear();
			break;
		}case INVENTORY:{
			view.drawMenu(InputMenu.INVENTORY, Optional.of(model.getInformationDealer("inventory")));
			inputMenuList.clear();
			itemInputManager();
			break;
		}case SKILL_TREE:{
			view.drawMenu(InputMenu.SKILL_TREE, Optional.empty());
			inputMenuList.clear();
			break;
		}case POKEDEX:{
			view.drawMenu(InputMenu.POKEDEX, Optional.of(model.getInformationDealer("pokedex")));
			inputMenuList.clear();
			break;
		}case SHOP:{
			view.drawMenu(InputMenu.SHOP, Optional.of(model.getInformationDealer("shop")));
			inputMenuList.clear();
			itemInputManager();
			break;
		}case LOBBY:{
			view.drawLobby();
			inputMenuList.clear();
			break;
		}case START:{
			inputMenuList.clear();
			gameLoop();
			break;
		}case ACHIEVEMENTS:{
			inputMenuList.clear();
			break;
		}case BACK:{
			backDec();
			if(this.menuBack[0].getType()=="complex"){
				switch(this.menuBack[0]){
				case INVENTORY:{
					view.drawMenu(InputMenu.INVENTORY, Optional.of(model.getInformationDealer("inventory")));
					inputMenuList.clear();
					itemInputManager();
					break;
				}case SHOP:{
					view.drawMenu(InputMenu.SHOP, Optional.of(model.getInformationDealer("shop")));
					inputMenuList.clear();
					itemInputManager();
					break;
				}case SKILL_TREE:{
					//view.drawMenu(this.menuBack[0],Optional.of(model.getInv))
					break;
				}case POKEDEX:{
					view.drawMenu(InputMenu.POKEDEX, Optional.of(model.getInformationDealer("pokedex")));
					inputMenuList.clear();
					break;
				}default:{
					break;
				}
				}
			}else{
				view.drawMenu(this.menuBack[0], Optional.empty());
			}
			inputMenuList.clear();
			break;
		}
		}
	}
	
	private void menuInputManager(){
		boolean loop=true;
		while(loop){
			try{
				if(inputMenuList.isEmpty()){
					java.lang.Thread.sleep(100);
				}else{
					menuChoice();
				}
			}catch(Exception e){
				System.exit(0);
			}
		}
	}
	
	private void itemInputManager(){
		boolean loop=true;
		while(loop){
			try{
				if(mapInputCommand==null&&inputMenuList.isEmpty()){
					java.lang.Thread.sleep(100);
				}else{
					if(!inputMenuList.isEmpty()){
						menuChoice();
					}
					switch(mapInputCommand.getX()){
					case EQUIP:{
						model.itemAction(mapInputCommand.getY(),"equip");
						mapInputCommand=null;
						break;
					}case BUY:{
						model.itemAction(mapInputCommand.getY(),"buy");
						mapInputCommand=null;
						break;
					}case UNEQUIP:{
						model.itemAction(mapInputCommand.getY(),"unequip");
						mapInputCommand=null;
						break;
					}case SELL:{
						model.itemAction(mapInputCommand.getY(),"sell");
						mapInputCommand=null;
						break;
					}default:{
						mapInputCommand=null;
						break;
					}
					}
				}
			}catch(Exception e){
				System.exit(0);
			}
		}
	}
	
	private boolean gameInputManager(){
		try{
			if(!inputMenuList.isEmpty()&& inputMenuList.getFirst()!=InputMenu.BACK){
				menuChoice();
			}else{
				if(inputMenuList.getFirst()==InputMenu.BACK){
					model.hero("back");
				}
				for(InputCommand command:inputCommandList){
					switch(command){
					case ABILITY1:{
						model.hero("ability1");
						break;
					}case ABILITY2:{
						model.hero("ability2");
						break;
					}case ABILITY3:{
						model.hero("ability3");
						break;
					}case CONSUMABLE:{
						model.hero("consumable");
						break;
					}case ATTACK:{
						model.hero("attack");
						break;
					}case JUMP:{
						model.hero("jump");
						break;
					}case LEFT:{
						model.hero("left");
						break;
					}case RIGHT:{
						model.hero("right");
						break;
					}case INTERACT:{
						model.hero("interact");
						break;
					}
					default:{
						break;
					}
					}
				}
			}
		}catch(Exception e){
			System.exit(0);
		}
		return false;
	}
	
	private void gameLoop(){
		boolean gameRunning=true;
		//nanosec used
		final int skipTick=15000;//model update frequency (66 update per sec)
		//more accurate than millisec in my opinion
		//didn't find a real answer on the Internet
		final int maxFrameSkip=15;//max frame skipped between each model update
		long tick=System.nanoTime();
		int loops;
		while(gameRunning){
			loops=0;
			while(System.nanoTime()>tick&&loops<maxFrameSkip&&gameRunning){
				try{
					model.update();
				}catch(Exception e){
					gameRunning=false;
				}
				tick+=skipTick;
				loops++;
				if(gameInputManager()){
					gameRunning=false;
				}
			}
			view.drawGame(model.getDrawableRoomEntity());
			
		}
	}
	
	public void addInput(InputCommand input) {
		this.inputCommandList.add(input);
	}

	public void addInput(InputMenu menu) {
		this.inputMenuList.add(menu);
	}

	public void addInput(InputCommand input, InformationDealer item) {
		this.mapInputCommand=new Pair<>(input,item);
	}

	public void addInput(ClassType classType) {
		//model.setclass();
	}
	
	public static void main(String[] args){
		new Controller();
	}
}
