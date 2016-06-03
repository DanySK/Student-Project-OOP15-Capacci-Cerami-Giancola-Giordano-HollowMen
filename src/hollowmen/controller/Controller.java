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
	private Model model;
	private boolean difficultyPicked=false;
	private boolean classPicked=false;
	private boolean gameRunning=false;
	private boolean itemSelected=false;
	
	public Controller(){
	}
	
	public void setup(){
		this.view=new ViewImpl(800,600,this);
		this.view.takeFile(LoaderClass.load());
		this.view.drawMenu(InputMenu.MAIN, Optional.empty());
		this.model=new ModelImpl();
		this.model.setup();
		menuInputLoop();
	}
	
	private void menuChoice(){
		switch(this.inputMenuList.getFirst()){
		case MAIN:{
			this.view.drawMenu(InputMenu.MAIN, Optional.empty());
			this.inputMenuList.clear();
			break;
		}case CLASS:{
			this.view.drawMenu(InputMenu.CLASS, Optional.empty());
			this.inputMenuList.clear();
			break;
		}case DIFFICULTY:{
			this.view.drawMenu(InputMenu.DIFFICULTY, Optional.empty());
			this.inputMenuList.clear();
			break;
		}case HELP:{
			this.view.drawMenu(InputMenu.HELP, Optional.empty());
			this.inputMenuList.clear();
			break;
		}case PAUSE:{
			this.view.drawMenu(InputMenu.PAUSE, Optional.empty());
			this.inputMenuList.clear();
			break;
		}case INVENTORY:{
			this.view.drawMenu(InputMenu.INVENTORY, Optional.of(model.getInventory()));
			this.inputMenuList.clear();
			itemInputLoop();
			break;
		}case SKILL_TREE:{
			this.view.drawMenu(InputMenu.SKILL_TREE, Optional.empty());
			this.inputMenuList.clear();
			break;
		}case POKEDEX:{
			this.view.drawMenu(InputMenu.POKEDEX, Optional.of(model.getPokedex()));
			this.inputMenuList.clear();
			break;
		}case SHOP:{
			this.view.drawMenu(InputMenu.SHOP, Optional.of(model.getShop()));
			this.inputMenuList.clear();
			itemInputLoop();
			break;
		}case LOBBY:{
			this.view.drawLobby();
			this.inputMenuList.clear();
			break;
		}case START:{
			this.inputMenuList.clear();
			gameLoop();
			break;
		}case ACHIEVEMENTS:{
			this.inputMenuList.clear();
			break;
		}case RESUME:{
			this.inputMenuList.clear();
			if(!this.gameRunning){
				this.view.drawMenu(InputMenu.MAIN,Optional.empty());
			}else{
				gameLoop();
			}
			break;
		}case BACK:{
			this.view.drawMenu(InputMenu.MAIN, Optional.empty());
			this.inputMenuList.clear();
			break;
		}
		}
	}
	
	private void menuInputLoop(){
		boolean loop=true;
		while(loop){
			try{
				if(this.difficultyPicked==true){
				    this.difficultyPicked=false;
					this.view.drawMenu(InputMenu.CLASS, Optional.empty());
				}
				if(this.classPicked==true){
					this.classPicked=false;
					this.view.drawLobby();
				}
				if(this.inputMenuList.isEmpty()){
					java.lang.Thread.sleep(100);
				}else{
					System.out.println(this.inputMenuList.getFirst());
					menuChoice();
					
				}
			}catch(Exception e){
				System.out.println("menuInputManager");
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	
	private void itemInputLoop(){
		boolean loop=true;
		while(loop){
			try{
				if(this.itemSelected==false && this.inputMenuList.isEmpty()){
					java.lang.Thread.sleep(100);
				}else{
					if(!this.inputMenuList.isEmpty()){
					    System.out.println(this.inputMenuList.getFirst());
						menuChoice();
						menuInputLoop();
					}else{
						switch(this.mapInputCommand.getX()){
						case EQUIP:{
							this.model.itemEquip(this.mapInputCommand.getY());
							this.mapInputCommand=null;
							break;
						}case BUY:{
							this.model.itemBuy(this.mapInputCommand.getY());
							this.mapInputCommand=null;
							break;
						}case UNEQUIP:{
							this.model.itemUnequip(this.mapInputCommand.getY());
							this.mapInputCommand=null;
							break;
						}case SELL:{
							this.model.itemSell(this.mapInputCommand.getY());
							this.mapInputCommand=null;
							break;
						}default:{
							this.mapInputCommand=null;
							break;
						}
						}
					}
				}
			}catch(Exception e){
				System.out.println("itemInputManager");
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	
	private boolean gameInputManager(){
		try{
			if(!this.inputMenuList.isEmpty()){
				menuChoice();
				return true;
			}else{
				boolean ability1=false;
				boolean ability2=false;
				boolean ability3=false;
				boolean consumable=false;
				boolean attack=false;
				boolean jump=false;
				boolean left=false;
				boolean right=false;
				boolean interact=false;
				for(InputCommand command:this.inputCommandList){
					switch(command){
					case ABILITY1:{
						if(!ability1){
							ability1=true;
							this.model.heroAction("ability1");
						}
						break;
					}case ABILITY2:{
						if(!ability2){
							ability2=true;
							this.model.heroAction("ability2");
						}
						break;
					}case ABILITY3:{
						if(!ability3){
							ability3=true;
							this.model.heroAction("ability3");
						}
						break;
					}case CONSUMABLE:{
						if(!consumable){
							consumable=true;
							this.model.heroAction("consumable");
						}
						break;
					}case ATTACK:{
						if(!attack){
							attack=true;
							this.model.heroAction("attack");
						}
						break;
					}case JUMP:{
						if(!jump){
							jump=true;
							this.model.heroAction("jump");
						}
						break;
					}case LEFT:{
						if(!left){
							left=true;
							this.model.moveHero("left");
						}
						break;
					}case RIGHT:{
						if(!right){
							right=true;
							this.model.moveHero("right");
						}
						break;
					}case INTERACT:{
						if(!interact){
							interact=true;
							this.model.heroAction("interact");
						}
						break;
					}
					default:{
						break;
					}
					}
				}
			}
		}catch(Exception e){
			System.out.println("gameInputManager");
			e.printStackTrace();
			System.exit(0);
		}
		return false;
	}
	
	private void gameLoop(){
		
		//nanosec used
		final int skipTick=16000000;//model update frequency (62 update per sec)
		//more accurate than millisec in my opinion
		//didn't find a real answer on the Internet
		final int maxFrameSkip=15;//max frame skipped between each model update
		long tick=System.nanoTime();
		int loops;
		this.gameRunning=true;
		
		this.model.goTo(1);
		
		try{
		while(this.gameRunning){
			java.lang.Thread.sleep(25);
			loops=0;
			while(System.nanoTime()>tick&&loops<maxFrameSkip&&this.gameRunning){
				System.out.println("giro");
				try{
					this.model.update(skipTick/1000000);
				}catch(GameOverException e){
					this.gameRunning=false;
					e.printStackTrace();
					System.out.println("GameOver!");
				}catch(NullPointerException e){
					e.printStackTrace();
					System.exit(0);
				}
				tick+=skipTick;
				loops++;
				if(gameInputManager()){
					this.gameRunning=false;
				}
			}
			try{
				this.view.drawGame(this.model.getDrawableRoomEntity());
			}catch(Exception e){
				e.printStackTrace();
				System.exit(0);
			}
			
		}
		}catch(Exception e){
			System.out.println("il gameLoop non va");
			e.printStackTrace();
			System.exit(0);
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
		this.difficultyPicked=true;
		this.model.setDifficulty(difficulty);
	}
	
}
