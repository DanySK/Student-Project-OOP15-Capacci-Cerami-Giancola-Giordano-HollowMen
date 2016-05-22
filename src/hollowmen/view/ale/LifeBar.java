package hollowmen.view.ale;

import java.awt.Color;
import javax.swing.JLabel;
/**
 * The LifeBar class ,called to each frame, will show to the player the internal of the lifeBar
 * 
 * @author Alessia
 *
 */
public class LifeBar extends InternalBar{
    
    private static final long serialVersionUID = -5182672357552953075L;
    private JLabel life;
    
    public LifeBar(){
        
        this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(Color.RED);
        
        this.life=new JLabel();
        this.life.setOpaque(true);
        this.life.setBackground(Color.GREEN);
    }
    
    protected void barFilling(){
        this.life.setBounds(0,0,(int)width,this.getHeight());
        this.add(this.life);
    }
}
