package hollowmen.view.ale;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import hollowmen.controller.ViewObserver;
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
	private ViewObserver observer;
	private JLabel panelGame;
	private Bar bars;
	private LinkedList <Pair<String,JLabel>> storage;
	
	
	public Game(int x,int y){//INCOMPLETO... da aggiungere i vari label
		
		this.setLayout(null);//It's important 'cause if it isn't it doesn't show anything
		this.setBounds(0,0,x,y+GAP);
		panelGame=new JLabel();
                panelGame.setLayout(null);
                panelGame.setBounds(0, GAP/2, x, y);
                this.add(panelGame);
                bars=new Bar();
                bars.setLayout(null);
                bars.setBounds(x/7*5, y/38, this.getWidth(), this.getHeight());//?????????????????
		addKeyListener(new KeyInput(this));
	}
	
	public void setObserver(ViewObserver observer){
		this.observer=observer;
	}
	
	public void draw(List<Pair<String, Point2D>> componentList){	
		removeAll(); /*At first I remove all the components from the screen 
					 then I'll add the component(addComponent),that are all the static components
					 (lifeBar, expBar) and the dynamicComponents too (which are the various mob)*/
		addComponent();//static components
		addDynamicComponent(componentList);
	}
	
	public void setStorage(List<Pair<String,ImageIcon>> storage){
		this.storage=new LinkedList<Pair<String,JLabel>>();
		for(Pair<String,ImageIcon> elem: storage){
			this.storage.add(new Pair<String,JLabel>(elem.getX(),new JLabel(elem.getY())));
		}
	}
	
	private void addComponent(){//TODO
	    	this.add(bars);
				
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
	
	//Switch-case improve performance. It's a flexible instruction used to control.
	public void keyPressed(KeyEvent e){
		
		int key=e.getKeyCode();
		
		switch (key){
		
			case KeyEvent.VK_W:{ this.observer.addInput("Up");
								break;}
			case KeyEvent.VK_A:{ this.observer.addInput("Left");
								break;}
			case KeyEvent.VK_S:{ this.observer.addInput("Down");
								break;}
			case KeyEvent.VK_D:{ this.observer.addInput("Right");
								break;}
			case KeyEvent.VK_E:{ this.observer.addInput("Equip");
								break;}
			case KeyEvent.VK_F:{ this.observer.addInput("Skill Tree");
								break;}
			case KeyEvent.VK_Q:{ this.observer.addInput("Interact");
								break;}
			case KeyEvent.VK_SPACE:{ this.observer.addInput("Attack Base");
								break;}
			case KeyEvent.VK_B:{ this.observer.addInput("Pokedex");
								break;}
			case KeyEvent.VK_V:{ this.observer.addInput("Achievement");
								break;}
			case KeyEvent.VK_H:{ this.observer.addInput("Help");
								break;}
			case KeyEvent.VK_P:{ this.observer.addInput("Ability1");
								break;}
			case KeyEvent.VK_O:{ this.observer.addInput("Ability2");
								break;}
			case KeyEvent.VK_I:{ this.observer.addInput("Ability3");
								break;}
			case KeyEvent.VK_ESCAPE:{ this.observer.addInput("Pause");
								break;}
			case KeyEvent.VK_0:{ this.observer.addInput("Consumable");
								break;}
		}
	}			
}
