import javax.swing.*; 
import javax.swing.JPanel;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.Vector;
import java.awt.font.*;
/** Accepts an arbitrary component and shows it in a resizable frame.  This is just a simple class I've used to hack up
 * a UI.
 * @author Stephen Wattam
 */
public class ArbitraryComponentFrame extends JFrame{
    
    /** Holds the component */
    private Component c;

    /** Creates and shows a new frame.
     * @param p_width The initial width of the frame
     * @param p_height The initial height of the frame
     * @param p_title The title of the window
     * @param c The component to use
     */
    public ArbitraryComponentFrame(int p_width, int p_height, String p_title, java.awt.Component c){
	super(p_title);
	setSize(new Dimension(p_width,p_height));

	setComponent(c);

	repaint();
	setVisible(true);
    }

    /** Returns the component that is currently being used 
     * @return The component that is being drawn currently.
     */
    private Component getComponent(){
	return this.c;
    }

    /** Changes the component that's being drawn.
     * @param c The component to use
     */
    private void setComponent(Component c){
	this.c = c;

	c.setSize(this.getWidth(),this.getHeight());
	this.add(c);
    }

    /** Force a repaint of the window and hence the component within.*/
    public void repaint(){
	c.repaint();
    }
}
