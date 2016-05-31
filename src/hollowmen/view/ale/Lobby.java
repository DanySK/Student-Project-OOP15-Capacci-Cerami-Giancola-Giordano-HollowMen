package hollowmen.view.ale;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import hollowmen.controller.ViewObserver;
import hollowmen.enumerators.InputMenu;

/**
 * The class {@code Lobby} build a sequence of JButton which can be clicked by the player.
 * 
 * @author NarcAle
 *
 */
public class Lobby extends JLabel{

    private static final long serialVersionUID = -986610577506284L;
    private Font fontA=new Font("Chiller",Font.PLAIN, 20);
    private Font fontB=new Font("Chiller",Font.PLAIN, 20);
    private static final int DIMX=100;
    private static final int DIMY=100;
    private static final int LOCX=8;
    private static final int LOCX2=170;
    private static final int LOCX3=320;
    private static final int LOCX4=470;
    private static final int LOCX5=400;
    private static final int LOCX6=10;
    private static final int LOCY=30;
    private static final int LOCY2=800;
    
    private Border border=BorderFactory.createRaisedBevelBorder();//To set a border to the buttons.
    JButton inventory;
    JButton skillTree;
    JButton shop;
    JButton pokedex;
    JButton startGame;
    JButton back;
    
    /**
     * the constructor create all the buttons I need to display on Lobby.
     * @param observer
     * @param storage
     */
    public Lobby(ViewObserver observer, Map<String,ImageIcon> storage ){
        this.setBackground(Color.BLACK);//Al momento c'è un semplice background nero.
        this.setSize(LOCY2,LOCY2);//Lobby dimension
        this.setLocation(0, 0);//Location of the panel.It refers to the frame dimension
        
        this.inventory=new JButton();
        this.inventory.setLayout(null);
        this.inventory.setSize(DIMX,DIMY);
        this.inventory.setLocation(LOCX, LOCY);
        this.inventory.setBorder(border);
        this.inventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.INVENTORY);
            }       
        });
        
        this.skillTree=new JButton();
        this.skillTree.setLayout(null);
        this.skillTree.setSize(DIMX,DIMY);
        this.skillTree.setLocation(LOCX2, LOCY);
        this.skillTree.setBorder(border);
        this.skillTree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.SKILL_TREE);
            }       
        });        
        
        this.shop=new JButton();
        this.shop.setLayout(null);
        this.shop.setText("SHOP");
        this.shop.setFont(fontB);
        this.shop.setSize(DIMX,DIMY);
        this.shop.setLocation(LOCX3, LOCY);
        this.shop.setBorder(border);
        this.shop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.SHOP);
            }       
        });
        
        this.pokedex=new JButton();
        this.pokedex.setLayout(null);
        this.pokedex.setText("POKEDEX");
        this.pokedex.setFont(fontB);
        this.pokedex.setSize(DIMX,DIMY);
        this.pokedex.setLocation(LOCX4, LOCY);
        this.pokedex.setBorder(border);
        this.pokedex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.POKEDEX);
            }       
        });
        
        this.startGame=new JButton();
        this.startGame.setLayout(null);
        this.startGame.setText("START");
        this.startGame.setFont(fontA);
        this.startGame.setSize(DIMX,DIMY);
        this.startGame.setLocation(LOCX5,LOCY2);
        this.startGame.setBorder(border);
        this.startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.START);
            }       
        });
        
        this.back=new JButton();
        this.back.setLayout(null);
        this.back.setText("back");
        this.back.setSize(DIMX,DIMY);
        this.back.setLocation(LOCX6,LOCY2);
        this.back.setBorder(border);
        this.back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.BACK);
            }       
        });
        
        for(Map.Entry<String,ImageIcon> elem: storage.entrySet()){
        	
        	if(elem.getKey()=="castleBG"){//Background Image
        		this.setIcon(elem.getValue());
        		break;
        	}
            if(elem.getKey()==InputMenu.INVENTORY.getString()){
                this.inventory.setIcon(elem.getValue());
                this.setBorder(border);
                break;
            }
            else if(elem.getKey()==InputMenu.SKILL_TREE.getString()){
                this.skillTree.setIcon(elem.getValue());
                this.setBorder(border);
                break;
            }
            else if(elem.getKey()==InputMenu.SHOP.getString()){
                //this.shop.setIcon(elem.getValue());
                this.setBorder(border);
                break;
            }
            else if(elem.getKey()==InputMenu.POKEDEX.getString()){
                //this.pokedex.setIcon(elem.getValue());
                this.setBorder(border);
                break;
            }
        }
        
        
    }
}
