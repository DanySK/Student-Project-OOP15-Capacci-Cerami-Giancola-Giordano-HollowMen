package hollowmen.view.ale;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import hollowmen.controller.ViewObserver;
import hollowmen.enumerators.InputCommand;
import hollowmen.enumerators.InputMenu;
import hollowmen.enumerators.Values;
import hollowmen.model.facade.DrawableRoomEntity;
import hollowmen.sound.ale.CreateAudio;

/**
 * The {@code Game} class implements {@link GameInterface}. It is the game panel and give some functionality.
 * 
 * @author Alessia
 *
 */
public class Game extends JPanel implements GameInterface{
        
        private static final long serialVersionUID = -5081282343965245780L;
        private static final int GAP=200;
        private static final int ALIGNMENTX=680;
        private static final int ALIGNMENTY=20;
        private static final int POSITIONX=300;
        private static final int POSITIONY=100;
        private static final int ALIGNMENT=10;
        private static final int DIMX=10;
        private static final int DIMY=10;
        private int x;
        private int y;
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
        	this.x=x;
        	this.y=y;
            this.observer=observer;
            inputChooser=new InputChooser(this.observer);
            this.setLayout(null);//It's important 'cause if it isn't it doesn't show anything
            this.setBounds(0,0,x,y+GAP);
            new CreateAudio();
            addKeyListener(new KeyInput(this));
        }

        private void initialSetup(int x, int y){ 
            this.panelGame=new JLabel();
            this.panelGame.setLayout(null);
            this.panelGame.setBounds(0, GAP/2-20, x, y);
            this.levelValue=new ValueManager("Level: ", Color.WHITE);
            this.levelValue.setBounds(ALIGNMENT, ALIGNMENT, 150, 40);
            this.goldValue=new ValueManager("Gold: ", Color.YELLOW);
            this.goldValue.setBounds(0, ALIGNMENT*5, 150, 40);
            this.floorValue=new ValueManager("Floor: ", Color.WHITE);
            this.floorValue.setBounds(700, ALIGNMENT*70, 150, 40);
            this.timerValue=new ValueManager("Timer", Color.WHITE);
            this.timerValue.setBounds(350, 0, 150, 80);
            this.bars=new Bar();
            this.bars.setLayout(null);
            this.bars.setBounds(ALIGNMENTX, ALIGNMENTY, POSITIONX, POSITIONY);//misure statiche al momento
            //bars.setBounds(x/7*5, y/38, this.getWidth()/3, this.getHeight());//?????????????????
            this.btnAbility1=new ScreenButton(this.observer, InputCommand.ABILITY1, this.storageGame);
            this.btnAbility1.setBounds(8, 740, 90, 70);
            this.btnAbility2=new ScreenButton(this.observer, InputCommand.ABILITY2, this.storageGame);
            this.btnAbility2.setBounds(98, 740, 90, 70);
            this.btnAbility3=new ScreenButton(this.observer, InputCommand.ABILITY3 ,this.storageGame);
            this.btnAbility3.setBounds(188, 740, 90, 70);
            this.btnConsumable=new ScreenButton(this.observer, InputCommand.CONSUMABLE ,this.storageGame);
            this.btnConsumable.setBounds(278, 740, 90, 70);
            this.btnSkillTree=new ScreenButton(this.observer, InputMenu.SKILL_TREE ,this.storageGame);
            this.btnSkillTree.setBounds(458, 740, 90, 70);
            this.btnInventory=new ScreenButton(this.observer,InputMenu.INVENTORY ,this.storageGame);
            this.btnInventory.setBounds(548, 740, 90, 70);
        }
        
        public void draw(List<DrawableRoomEntity> componentList){       
            removeAll(); /*At first I remove all the components from the screen 
                                         then I'll add the component(addComponent),that are all the static components
                                         (lifeBar, expBar) and the dynamicComponents too (which are the various mob)*/
                
            if(Values.TIMER.getValue() <= 60){ //It's a simple control to change the color of the timer text.
                this.timerValue=new ValueManager("Timer: ", Color.RED);
                this.timerValue.setSize(350, 0);
                this.timerValue.setLocation(150, 80);
            }
            addComponent();//add all static components
            addDynamicComponent(componentList);
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
                initialSetup(this.x,this.y);
        }
        
        private void addComponent(){
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
            this.add(this.levelValue);
            this.add(this.goldValue);
            this.add(this.floorValue);
            this.add(this.timerValue);
                
        }
        
        private void addDynamicComponent(List<DrawableRoomEntity> componentList){
            JLabel labTmp;
                for(DrawableRoomEntity elem: componentList){
                    if(elem.isFacingRight()){
                        for(Map.Entry<String,JLabel> element: storageFlipped.entrySet()){
                            if(elem.getName()=="hero"){
                                switch(elem.getState()){
                                    case ATTACKING: {
                                        labTmp=storageFlipped.get("hero");
                                        labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                                         labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                                        panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                                        break;}
                                    case JUMPING: {
                                        labTmp=storageFlipped.get("hero");
                                        labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                                         labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                                        panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                                        break;}
                                    case STANDING: {
                                        labTmp=storageFlipped.get("hero");
                                        labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                                        labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                                        panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                                        break;}
                                    case MOVING: {
                                        labTmp=storageFlipped.get("hero");
                                        labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                                        labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                                        panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                                        break;}
                               }
                               break;
                           }
                           if(elem.getName()==element.getKey()){
                               labTmp=element.getValue();
                               labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                               labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                               this.panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                               break;//When the right image is found, the for cycle will end.
                           }
                       }
                    }
                    else{
                        for(Map.Entry<String,JLabel> element: storageGame.entrySet()){
                            if(elem.getName()=="hero"){
                                switch(elem.getState()){
                                case ATTACKING: {
                                    labTmp=storageGame.get("heroAttacking");
                                    labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                                     labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                                    panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                                    break;}
                                case JUMPING: {
                                    labTmp=storageGame.get("heroJumping");
                                    labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                                     labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                                    panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                                    break;}
                                case STANDING: {
                                    labTmp=storageGame.get("hero");
                                    labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                                    labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                                    panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                                    break;}
                                case MOVING: {
                                    labTmp=storageGame.get("heroMoving");
                                    labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                                    labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                                    panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                                    break;}
                                }
                                break;
                            }
                            
                            if(elem.getName()==element.getKey()){
                                labTmp=element.getValue();
                                labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                                labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                                this.panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                                break;//When the right image is found, the for cycle will end.
                            }
                        }
                   }
             }
        }
        
        public void keyManager(KeyEvent e){
            
                int key=e.getKeyCode();
                this.inputChooser.chooser(key);//calls the method of the InputChooser class
                
        }                       
}