package hollowmen.view.ale;

import javax.swing.JLabel;
/** 
 * The abstract class is used to draw bars with variable width.
 * 
 * @author Alessia
 */
public abstract class InternalBar extends JLabel{
    
    private static final long serialVersionUID = -3814942016648716113L;
    protected double proportion;
    protected double width;
    
    public InternalBar(){}
    
    /**
     * The {@code proportion} method used to calculate the percentage of the bar filling.
     * 
     * @param value
     * @param maxValue
     */
    public void proportion(int value,int maxValue){
        this.proportion=value*100/maxValue;
        this.width=this.getWidth()/100*this.proportion;
        this.removeAll();
        barFilling();
    }
    
    protected abstract void barFilling();
}