package hollowmen.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import hollowmen.enumerators.ClassType;
import hollowmen.enumerators.Difficulty;
import hollowmen.enumerators.InputCommand;
import hollowmen.enumerators.InputMenu;
import hollowmen.model.facade.DrawableRoomEntity;
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
			List<InformationDealer> shop=new LinkedList<>();
			for(InformationDealer item:model.getInventory()){
				if(item.getState().equals("UNEQUIPPED")){
					shop.add(item);
				}
			}
			shop.addAll(model.getShop());
			this.view.drawMenu(InputMenu.SHOP, Optional.of(shop));
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
				boolean left=false;
				boolean right=false;
				for(InputCommand command:this.inputCommandList){
					switch(command){
					case LEFT:{
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
					}case JUMP:{
						if(!left){
							left=true;
							this.model.moveHero("left");
						}
						break;
					}case ATTACK:{
						if(!left){
							left=true;
							this.model.moveHero("left");
						}
						break;
					}case INTERACT:{
						if(!left){
							left=true;
							this.model.moveHero("left");
						}
						break;
					}default:{
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
		final long skipTick=20000000;//model update frequency (62 update per sec)
		//more accurate than millisec in my opinion
		//didn't find a real answer on the Internet
		final int convert=1000000;
		final int skipTickMillisec=(int)(skipTick/convert);//max frame skipped between each model update
		List<DrawableRoomEntity> drawable;
		this.gameRunning=true;
		long sleep;
		this.model.goTo(1);
		this.inputMenuList.clear();
		long tick=System.nanoTime();
		try{
		while(this.gameRunning){
			try{
				this.model.update(skipTickMillisec);
			}catch(GameOverException e){
				this.gameRunning=false;
				System.out.println("GameOver!");
				break;
			}
			drawable=this.model.getDrawableRoomEntity();
			this.view.drawGame(drawable);
			if(gameInputManager()){
				this.gameRunning=false;
				break;
			}
			tick+=skipTick;
			sleep=(tick-System.nanoTime())/convert;
			if(sleep>0){
				java.lang.Thread.sleep(sleep);
			}
			
		}
		}catch(Exception e){
			System.out.println("il gameLoop si inchioda");
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	public void addInput(InputCommand input) {
		System.out.println(input);
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
