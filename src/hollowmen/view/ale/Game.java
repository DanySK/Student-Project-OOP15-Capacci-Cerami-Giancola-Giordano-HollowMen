package hollowmen.view.ale;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import hollowmen.controller.ViewObserver;
import hollowmen.enumerators.InputCommand;
import hollowmen.enumerators.InputMenu;
import hollowmen.enumerators.Values;
import hollowmen.model.facade.Point2D;

/**
 * The {@code Game} class implements {@link GameInterface}. It is the game panel and give some functionality.
 * 
 * @author Alessia
 *
 */
//I use the same class for Lobby too.
public class Game extends JPanel implements GameInterface{
	
	private static final long serialVersionUID = -5081282343965245780L;
	private static final int GAP=200;
    private static final int ALIGNMENTX=480;
    private static final int ALIGNMENTY=20;
    private static final int POSITIONX=300;
    private static final int POSITIONY=100;
    private static final int ALIGNMENT=10;
	private ViewObserver observer;
	private JLabel panelGame;
	private Bar bars;
	private ScreenButton btnAbility1;
	private ScreenButton btnAbility2;
	private ScreenButton btnAbility3;
	private ScreenButton btnConsumable;
	private ScreenButton btnSkillTree;
	private ScreenButton btnInventory;
	private InputChooser inputChooser;
	private ValueManager levelValue;
	private ValueManager goldValue;
	private ValueManager floorValue;
	private ValueManager timerValue;
	private Map<String,JLabel> storageGame; //Storage containing all the image I need
	private Map<String,JLabel> storageFlipped;//Storage containing all the flipped image I need 
	
	
	public Game(int x, int y, ViewObserver observer){
	    this.observer=observer;
	    inputChooser=new InputChooser(this.observer);
	    this.setLayout(null);//It's important 'cause if it isn't it doesn't show anything
	    this.setBounds(0,0,x,y+GAP);
	    initialSetup(x,y);
	    addKeyListener(new KeyInput(this));
    	}

	private void initialSetup(int x, int y){
	    panelGame=new JLabel();
	    panelGame.setLayout(null);
	    panelGame.setBounds(0, GAP/2, x, y);
	    levelValue=new ValueManager("Level: ", Color.WHITE);
	    levelValue.setBounds(0, ALIGNMENT, 150, 40);
	    goldValue=new ValueManager("Gold: ", Color.YELLOW);
	    goldValue.setBounds(0, ALIGNMENT*5, 150, 40);
	    floorValue=new ValueManager("Floor: ", Color.WHITE);
	    floorValue.setBounds(700, ALIGNMENT*40, 150, 40);
	    timerValue=new ValueManager("Timer", Color.WHITE);
	    timerValue.setBounds(350, 0, 150, 80);
        bars=new Bar();
        bars.setLayout(null);
        bars.setBounds(ALIGNMENTX, ALIGNMENTY, POSITIONX, POSITIONY);//misure statiche al momento
        //bars.setBounds(x/7*5, y/38, this.getWidth()/3, this.getHeight());//?????????????????
        btnAbility1=new ScreenButton(this.observer, InputCommand.ABILITY1, this.storageGame);
        btnAbility1.setBounds(0, 0, 0, 0);
        btnAbility2=new ScreenButton(this.observer, InputCommand.ABILITY2, this.storageGame);
        btnAbility2.setBounds(0, 0, 0, 0);
        btnAbility3=new ScreenButton(this.observer, InputCommand.ABILITY3 ,this.storageGame);
        btnAbility3.setBounds(0, 0, 0, 0);
        btnConsumable=new ScreenButton(this.observer, InputCommand.CONSUMABLE ,this.storageGame);
        btnConsumable.setBounds(0, 0, 0, 0);
        btnSkillTree=new ScreenButton(this.observer, InputMenu.SKILL_TREE ,this.storageGame);
        btnSkillTree.setBounds(0, 0, 0, 0);
        btnInventory=new ScreenButton(this.observer,InputMenu.INVENTORY ,this.storageGame);
        btnInventory.setBounds(0, 0, 0, 0);
	}
	
	public void draw(Map<String, Point2D> componentMap){	
		removeAll(); /*At first I remove all the components from the screen 
					 then I'll add the component(addComponent),that are all the static components
					 (lifeBar, expBar) and the dynamicComponents too (which are the various mob)*/
		
		if(Values.TIMER.getValue() <= 60){ //It's a simple control to change the color of the timer text.
			this.timerValue=new ValueManager("Timer: ", Color.RED);
			this.timerValue.setSize(350, 0);
			this.timerValue.setLocation(150, 80);
		}
		addComponent();//static components
		addDynamicComponent(componentMap);
	}
	
	public void setStorage(Map<String,ImageIcon> storage){
		this.storageGame=new HashMap<String,JLabel>();
		this.storageFlipped=new HashMap<String,JLabel>();
		for(Map.Entry<String,ImageIcon> elem: storage.entrySet()){
			for(String name: SingletonNameList.getNameList() ){
				if(elem.getKey()==name){
					storageGame.put(name, new JLabel(elem.getValue()));
					if(name!= "ability1" || name!= "ability2" || name!= "ability3"||
						name!= "invetory"|| name!= "consumable" || name!= "skillTree"){
						storageFlipped.put(name, new JLabel(new FlipImage(elem.getValue().getImage())));
					}
				}
			}
		}
	}
	
	private void addComponent(){//TODO
		this.add(panelGame);
		this.add(bars);
		this.add(btnAbility1);
		this.add(btnAbility2);
		this.add(btnAbility3);
		this.add(btnConsumable);
		this.add(btnSkillTree);
		this.add(btnInventory);
		this.levelValue.updateValue(Values.LEVEL.getValue());
		this.goldValue.updateValue(Values.GOLD.getValue());
		this.floorValue.updateValue(Values.FLOOR.getValue());
		this.timerValue.updateValue(Values.TIMER.getValue());
		
	}
	
	private void addDynamicComponent(Map<String, Point2D> componentMap){//List<DrawableRE>
		JLabel labTmp;
		for(Map.Entry<String,Point2D> elem: componentMap.entrySet()){
			for(Map.Entry<String,JLabel> element: storage.entrySet()){
				if(elem.getKey()==element.getKey()){
					labTmp=element.getValue();
					labTmp.setBounds((int)elem.getValue().getX(), (int)elem.getValue().getY(), 
									labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
					panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
					break;//When the right image is found, the for cycle will end.
				}
			}
		}
	}
	
	public void keyManager(KeyEvent e){
	    
		int key=e.getKeyCode();
		inputChooser.chooser(key);//calls the method of the InputChooser class
		
	}			
}
