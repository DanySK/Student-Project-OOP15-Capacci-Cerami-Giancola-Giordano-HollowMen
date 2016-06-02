package hollowmen.view.ale;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import hollowmen.controller.ViewObserver;
import hollowmen.enumerators.InputMenu;
import hollowmen.view.SingletonFrame;

/**
 * The class {@code Lobby} is used to build a sequence of JButton which can be clicked by the player.
 * 
 * @author Alessia
 *
 */
public class Lobby extends JLabel{

    private static final long serialVersionUID = -986610577506284L;
    private Font fontA=new Font("Chiller",Font.PLAIN, 25);
    private Font fontB=new Font("Chiller",Font.PLAIN, 40);
    private static final int DIMXPLAY=200;
    private static final int DIMXL=800;
    private static final int DIMYL=800;
    private static final int DIMX=140;
    private static final int DIMY=80;
    private static final int LOCX=155;
    private static final int LOCX2=415;
    private static final int LOCXPLAY=550;
    private static final int LOCXBACK=10;
    private static final int LOCY=180;
    private static final int LOCY2=450;
    private static final int LOCYSP=670;
    
    private Border border=BorderFactory.createRaisedBevelBorder();//To set a border to the buttons.
    private JLabel lab;
    private JButton inventory;
    private JButton skillTree;
    private JButton shop;
    private JButton pokedex;
    private JButton startGame;
    private JButton back;
    /**
     * The constructor create all the buttons I need to display on Lobby.
     * 
     * @param observer
     * @param storage
     */
    public Lobby(ViewObserver observer, Map<String,ImageIcon> storage ){
        this.lab=new JLabel();
        this.lab.setLayout(null);        
        this.lab.setOpaque(true);
        
        this.setBackground(Color.BLACK);
        this.setBounds(0, 0, DIMXL, DIMYL);
        this.add(lab);
        		
        this.inventory=new JButton();
        this.inventory.setLayout(null);
        this.inventory.setOpaque(true);
        this.inventory.setBackground(Color.darkGray);
        this.inventory.setText("INVENTORY");
        this.inventory.setFont(fontA);
        this.inventory.setForeground(Color.WHITE);
        this.inventory.setBounds(LOCX, LOCY, DIMX, DIMY);
        this.inventory.setBorder(border);
        this.inventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.INVENTORY);
                SingletonFrame frame=SingletonFrame.getInstance();
        		frame.getContentPane().removeAll();
        		frame.update(frame.getGraphics());
            }       
        });
        rollOverButton(this.inventory, Color.DARK_GRAY, Color.GRAY);
        this.add(this.inventory);
        
        this.skillTree=new JButton();
        this.skillTree.setLayout(null);
        this.skillTree.setOpaque(true);
        this.skillTree.setBackground(Color.darkGray);
        this.skillTree.setText("SKILLTREE");
        this.skillTree.setFont(fontA);
        this.skillTree.setForeground(Color.WHITE);
        this.skillTree.setBounds(LOCX2, LOCY, DIMX, DIMY);
        this.skillTree.setBorder(border);
        this.skillTree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.SKILL_TREE);
                SingletonFrame frame=SingletonFrame.getInstance();
        		frame.getContentPane().removeAll();
        		frame.update(frame.getGraphics());
            }       
        });        
        this.add(this.skillTree);
        this.skillTree.setEnabled(false);
        
        this.shop=new JButton();
        this.shop.setLayout(null);
        this.shop.setOpaque(true);
        this.shop.setBackground(Color.darkGray);
        this.shop.setText("SHOP");
        this.shop.setFont(fontA);
        this.shop.setForeground(Color.WHITE);
        this.shop.setBounds(LOCX, LOCY2, DIMX, DIMY);
        this.shop.setBorder(border);
        this.shop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.SHOP);
                SingletonFrame frame=SingletonFrame.getInstance();
        		frame.getContentPane().removeAll();
        		frame.update(frame.getGraphics());
            }       
        });
        rollOverButton(this.shop, Color.DARK_GRAY, Color.GRAY);
        this.add(this.shop);
        
        this.pokedex=new JButton();
        this.pokedex.setLayout(null);
        this.pokedex.setOpaque(true);
        this.pokedex.setBackground(Color.darkGray);
        this.pokedex.setText("POKEDEX");
        this.pokedex.setFont(fontA);
        this.pokedex.setForeground(Color.WHITE);
        this.pokedex.setBounds(LOCX2, LOCY2, DIMX, DIMY);
        this.pokedex.setBorder(border);
        this.pokedex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.POKEDEX);
                SingletonFrame frame=SingletonFrame.getInstance();
        		frame.getContentPane().removeAll();
        		frame.update(frame.getGraphics());
            }       
        });
        rollOverButton(this.pokedex, Color.DARK_GRAY, Color.GRAY);
        this.add(this.pokedex);
        
        this.startGame=new JButton();
        this.startGame.setLayout(null);
        this.startGame.setOpaque(true);
        this.startGame.setBackground(new Color(3,192,60));
        this.startGame.setText("START");
        this.startGame.setFont(fontB);
        this.startGame.setForeground(Color.BLACK);
        this.startGame.setBounds(LOCXPLAY, LOCYSP, DIMXPLAY, DIMY);
        this.startGame.setBorder(border);
        this.startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.START);
            }       
        });
        rollOverButton(this.startGame, new Color(3,192,60), Color.GREEN);
        this.add(this.startGame);
        
        this.back=new JButton();
        this.back.setLayout(null);
        this.back.setOpaque(true);
        this.back.setBackground(new Color(150,0,24));
        this.back.setText("BACK");
        this.back.setFont(fontB);
        this.back.setForeground(Color.WHITE);
        this.back.setBounds(LOCXBACK, LOCYSP, DIMX, DIMY);
        this.back.setBorder(border);
        this.back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.BACK);
            }       
        });
        rollOverButton(this.back, new Color(150,0,24), Color.RED);
        this.add(this.back);
        
        for(Map.Entry<String,ImageIcon> elem: storage.entrySet()){

            if(elem.getKey().equals("castleBG")){//Background Image
                this.setIcon(elem.getValue());
                break;
            }
            if(elem.getKey().equals(InputMenu.INVENTORY.getString())){
                this.inventory.setIcon(elem.getValue());
                this.setBorder(border);
                break;
            }
            else if(elem.getKey().equals(InputMenu.SKILL_TREE.getString())){
                this.skillTree.setIcon(elem.getValue());
                this.setBorder(border);
                break;
            }
            else if(elem.getKey().equals(InputMenu.SHOP.getString())){
                //this.shop.setIcon(elem.getValue());
                this.setBorder(border);
                break;
            }
            else if(elem.getKey().equals(InputMenu.POKEDEX.getString())){
                //this.pokedex.setIcon(elem.getValue());
                this.setBorder(border);
                break;
            }
        }
    }
    
    /**
     * The method {@code rollOverButton} is used to set color when mouse is over
     * @param button
     */
    private void rollOverButton(JButton button, Color colorOUT, Color colorIN){
    	 button.getModel().addChangeListener(new ChangeListener() {
 			
 			@Override
 			public void stateChanged(ChangeEvent e) {
 				ButtonModel model=(ButtonModel) e.getSource();
 				if(model.isRollover()){
 					button.setBackground(colorIN);
 				}
 				else{
 					button.setBackground(colorOUT);
 				}
 			}
 		});
    }
    
}
