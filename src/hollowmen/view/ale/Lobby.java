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
 * The class {@code Lobby} is used to build a sequence of JButton which can be clicked by the player.
 * 
 * @author NarcAle
 *
 */
public class Lobby extends JLabel{

    private static final long serialVersionUID = -986610577506284L;
    private Font fontB=new Font("Chiller",Font.PLAIN, 25);
    private static final int DIMXL=800;
    private static final int DIMYL=800;
    private static final int DIMX=150;
    private static final int DIMY=100;
    private static final int LOCX=55;
    private static final int LOCX2=285;
    private static final int LOCX3=515;
    private static final int LOCX4=745;
    private static final int LOCXPLAY=400;
    private static final int LOCXBACK=10;
    private static final int LOCY=400;
    private static final int LOCYSP=800;
    
    private Border border=BorderFactory.createRaisedBevelBorder();//To set a border to the buttons.
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
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setBounds(0, 0, DIMXL, DIMYL);
        
        this.inventory=new JButton();
        this.inventory.setLayout(null);
        this.inventory.setOpaque(true);
        this.inventory.setBackground(Color.darkGray);
        this.inventory.setBounds(LOCX, LOCY, DIMX, DIMY);
        this.inventory.setBorder(border);
        this.inventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.INVENTORY);
            }       
        });
        this.add(this.inventory);
        
        this.skillTree=new JButton();
        this.skillTree.setLayout(null);
        this.skillTree.setOpaque(true);
        this.skillTree.setBackground(Color.darkGray);
        this.skillTree.setBounds(LOCX2, LOCY, DIMX, DIMY);
        this.skillTree.setBorder(border);
        this.skillTree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.SKILL_TREE);
            }       
        });        
        this.add(this.skillTree);
        
        this.shop=new JButton();
        this.shop.setLayout(null);
        this.shop.setOpaque(true);
        this.shop.setBackground(Color.darkGray);
        this.shop.setText("SHOP");
        this.shop.setFont(fontB);
        this.shop.setForeground(Color.WHITE);
        this.shop.setBounds(LOCX3, LOCY, DIMX, DIMY);
        this.shop.setBorder(border);
        this.shop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.SHOP);
            }       
        });
        this.add(this.shop);
        
        this.pokedex=new JButton();
        this.pokedex.setLayout(null);
        this.pokedex.setOpaque(true);
        this.pokedex.setBackground(Color.darkGray);
        this.pokedex.setText("POKEDEX");
        this.pokedex.setFont(fontB);
        this.pokedex.setForeground(Color.WHITE);
        this.pokedex.setBounds(LOCX4, LOCY, DIMX, DIMY);
        this.pokedex.setBorder(border);
        this.pokedex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.POKEDEX);
            }       
        });
        this.add(this.pokedex);
        
        this.startGame=new JButton();
        this.startGame.setLayout(null);
        this.startGame.setOpaque(true);
        this.startGame.setBackground(Color.darkGray);
        this.startGame.setText("START");
        this.startGame.setFont(fontB);
        this.startGame.setForeground(Color.WHITE);
        this.startGame.setBounds(LOCXPLAY, LOCYSP, DIMX, DIMY);
        this.startGame.setBorder(border);
        this.startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.START);
            }       
        });
        this.add(this.startGame);
        
        this.back=new JButton();
        this.back.setLayout(null);
        this.back.setOpaque(true);
        this.back.setBackground(Color.darkGray);
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
        this.add(this.back);
        
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
