package hollowmen.view.ale;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import hollowmen.controller.ViewObserver;
import hollowmen.enumerators.InputCommand;
import hollowmen.enumerators.InputMenu;
import hollowmen.enumerators.Values;
import hollowmen.model.Point2D;
import hollowmen.utilities.Pair;
/**
 * The {@code Game} class implements {@link GameInterface}. It is the game panel and give some functionality.
 * 
 * @author Alessia
 *
 */
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
	private Map<String,JLabel> storage;
	
	
	public Game(int x, int y, ViewObserver observer){//INCOMPLETO... da aggiungere i vari label
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
        btnAbility1=new ScreenButton(this.observer, InputCommand.ABILITY1, this.storage);
        btnAbility1.setBounds(0, 0, 0, 0);
        btnAbility2=new ScreenButton(this.observer, InputCommand.ABILITY2, this.storage);
        btnAbility2.setBounds(0, 0, 0, 0);
        btnAbility3=new ScreenButton(this.observer, InputCommand.ABILITY3 ,this.storage);
        btnAbility3.setBounds(0, 0, 0, 0);
        btnConsumable=new ScreenButton(this.observer, InputCommand.ABILITY3 ,this.storage);
        btnConsumable.setBounds(0, 0, 0, 0);
        btnSkillTree=new ScreenButton(this.observer, InputMenu.SKILL_TREE ,this.storage);
        btnSkillTree.setBounds(0, 0, 0, 0);
        btnInventory=new ScreenButton(this.observer,InputMenu.INVENTORY ,this.storage);
        btnInventory.setBounds(0, 0, 0, 0);
	}
	
	public void draw(List<Pair<String, Point2D>> componentList){	
		removeAll(); /*At first I remove all the components from the screen 
					 then I'll add the component(addComponent),that are all the static components
					 (lifeBar, expBar) and the dynamicComponents too (which are the various mob)*/
		if(Values.TIMER.getValue() <= 60){ //It's a simple control to change the color of the timer text.
			this.timerValue=new ValueManager("Timer: ", Color.RED);
			this.timerValue.setBounds(350, 0, 150, 80);
		}
		addComponent();//static components
		addDynamicComponent(componentList);
	}
	
	public void setStorage(Map<String,ImageIcon> storage){
		this.storage=new HashMap<String,JLabel>();
		for(Map.Entry<String,ImageIcon> elem: storage.entrySet()){
			this.storage.put(elem.getKey(),new JLabel(elem.getValue()));
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
		levelValue.updateValue(Values.LEVEL.getValue());
		goldValue.updateValue(Values.GOLD.getValue());
		floorValue.updateValue(Values.FLOOR.getValue());
		timerValue.updateValue(Values.TIMER.getValue());
		
	}
	
	private void addDynamicComponent(List<Pair<String, Point2D>> componentList){
		int c=0;
		JLabel labTmp;
		for(Pair<String,Point2D> elem: componentList){
			while(elem.getX()!=this.storage.get(c).getX()){
				c++;
			}
			labTmp=this.storage.get(c).getY();
			labTmp.setBounds((int)elem.getY().getX(), (int)elem.getY().getY(), 
							labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
			panelGame.add(labTmp);
		}
	}
	
	public void keyPressed(KeyEvent e){
		
		int key=e.getKeyCode();
		inputChooser.chooser(key);//calls the method of the InputChooser class
		
	}			
}
