package hollowmen.controller;

import java.util.LinkedList;
import java.util.Optional;
import hollowmen.enumerators.ClassType;
import hollowmen.enumerators.Difficulty;
import hollowmen.enumerators.InputCommand;
import hollowmen.enumerators.InputMenu;
import hollowmen.model.facade.InformationDealer;
import hollowmen.model.facade.Model;
import hollowmen.model.facade.ModelImpl;
import hollowmen.model.utils.GameOverException;
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
	private ViewObserver obs;
	private Model model;
	private int counterBack=0;
	private InputMenu[] menuBack=new InputMenu[10];
	private boolean classPicked=false;
	
	public Controller(){
	}
	
	public void setup(){
		view=new ViewImpl(800,600,this);
		System.out.println(this);
		view.takeFile(LoaderClass.load());
		view.drawMenu(InputMenu.MAIN, Optional.empty());
		model=new ModelImpl();
		model.setup();
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
			view.drawMenu(InputMenu.INVENTORY, Optional.of(model.getInventory()));
			inputMenuList.clear();
			itemInputManager();
			break;
		}case SKILL_TREE:{
			view.drawMenu(InputMenu.SKILL_TREE, Optional.empty());
			inputMenuList.clear();
			break;
		}case POKEDEX:{
			view.drawMenu(InputMenu.POKEDEX, Optional.of(model.getPokedex()));
			inputMenuList.clear();
			break;
		}case SHOP:{
			view.drawMenu(InputMenu.SHOP, Optional.of(model.getShop()));
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
		}case RESUME:{
			gameLoop();
			break;
		}case BACK:{
			backDec();
			if(this.menuBack[0].getType()=="complex"){
				switch(this.menuBack[0]){
				case INVENTORY:{
					view.drawMenu(InputMenu.INVENTORY, Optional.of(model.getInventory()));
					inputMenuList.clear();
					itemInputManager();
					break;
				}case SHOP:{
					view.drawMenu(InputMenu.SHOP, Optional.of(model.getShop()));
					inputMenuList.clear();
					itemInputManager();
					break;
				}case SKILL_TREE:{
					//view.drawMenu(this.menuBack[0],Optional.of(model.getInv))
					break;
				}case POKEDEX:{
					view.drawMenu(InputMenu.POKEDEX, Optional.of(model.getPokedex()));
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
				if(this.classPicked==true){
					loop=false;
				}
				if(this.inputMenuList.isEmpty()){
					java.lang.Thread.sleep(100);
				}else{
					menuChoice();
				}
			}catch(Exception e){
				System.exit(0);
			}
		}
		view.drawLobby();
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
						model.itemEquip(mapInputCommand.getY(),"equip");
						mapInputCommand=null;
						break;
					}case BUY:{
						model.itemBuy(mapInputCommand.getY(),"buy");
						mapInputCommand=null;
						break;
					}case UNEQUIP:{
						model.itemUnequip(mapInputCommand.getY(),"unequip");
						mapInputCommand=null;
						break;
					}case SELL:{
						model.itemSell(mapInputCommand.getY(),"sell");
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
					model.heroAction("back");
				}
				for(InputCommand command:inputCommandList){
					switch(command){
					case ABILITY1:{
						model.heroAction("ability1");
						break;
					}case ABILITY2:{
						model.heroAction("ability2");
						break;
					}case ABILITY3:{
						model.heroAction("ability3");
						break;
					}case CONSUMABLE:{
						model.heroAction("consumable");
						break;
					}case ATTACK:{
						model.heroAction("attack");
						break;
					}case JUMP:{
						model.heroAction("jump");
						break;
					}case LEFT:{
						model.moveHero("left");
						break;
					}case RIGHT:{
						model.moveHero("right");
						break;
					}case INTERACT:{
						model.heroAction("interact");
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
		final int skipTick=16000;//model update frequency (62 update per sec)
		//more accurate than millisec in my opinion
		//didn't find a real answer on the Internet
		final int maxFrameSkip=15;//max frame skipped between each model update
		long tick=System.nanoTime();
		int loops;
		while(gameRunning){
			loops=0;
			while(System.nanoTime()>tick&&loops<maxFrameSkip&&gameRunning){
				try{
					model.update(16);
				}catch(GameOverException e){
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
		//actually there is only one class
		this.classPicked=true;
	}
	
	public void addInput(Difficulty difficulty){
		this.model.setDifficulty(difficulty);
	}
	
}
