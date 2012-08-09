import java.util.Vector;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;

import java.awt.Image;

/** A panel that roughly represents a scatter diagram: Points are rendered on a 2D, euclidean plane.  No axes or other
 * details are rendered.
 * @author Stephen Wattam
 */
public class ScatterPanel extends java.awt.Canvas{
    
    /** Holds all points to pain */
    private Vector<Point> points = new Vector<Point>();

    /** The size of the dots */
    private final static int SYMBOL_SIZE = 4;

    /** used to store the max x value to scale the output. */
    private double xmax;
    /** used to store the max y value to scale the output. */
    private double ymax;

    /** Create a new scatter panel. */
    public ScatterPanel(){	
	repaint();
	this.setVisible(true);
	//super.createBufferStrategy(2); // must be after we are visible!
    }

    public Image createImage(int w, int h){
	return super.createImage(w, h);
    }
    
    /** Add a point to the internal vector of points 
     *	@param p The point to add
     */
    public void addPoint(Point p){
	points.add(p);
	updateDimensions(p);
	this.repaint();
    }

    /** rebuilds x and y max, the internal dimension counters.
     * This is slow, but necessary.
     */
    private void rebuildInteralDimensionCache(){
	xmax = 0;
	ymax = 0;
	for(int i=0; i<points.size(); i++)
	    updateDimensions(points.get(i));
    }

    /** Maintain internal counter on dimensions
     * @param c The city to check against xmax, ymax
     */ 
    private void updateDimensions(Point p){
	if(p.x > xmax)
	    xmax = p.x;
	if(p.y > ymax)
	    ymax = p.y;
    }
    
    /** Remove a point from the internal vector of Points.
     * @param index The index to remove from.
     */ 
    public void removePoint(int index){
	//destroying dimensions? if so rebuild them
	if( points.get(index).x == xmax || points.get(index).y == ymax)
	    rebuildInteralDimensionCache();
	
	points.remove(index);
    }

    /** Remove a point from the internal vector of Points.
     * @param p The point object to remove.
     */
    public void removePoint(Point p){
	points.remove(p);
	
	//destroying dimensions? if so rebuild them
	if( p.x == xmax || p.y == ymax)
	    rebuildInteralDimensionCache();
    }

    /** Adjusts an X value to render, turning it from an absolute value to one normalised by th epanel size.
     * @param x The value to adjust
     * @return The adjusted value
     */
    protected double adjustX(double x){
	return (x / (xmax + SYMBOL_SIZE)) * this.getWidth();
    }
    
    /** Adjusts an Y value to render, turning it from an absolute value to one normalised by the panel size.
     * @param y The value to adjust
     * @return The adjusted value
     */
    protected double adjustY(double y){
	return (y / (ymax + SYMBOL_SIZE)) * this.getHeight();
    }
	
    /**Internal paint method.... */
    public void paint(Graphics g){
	g.setColor(new Color( 200, 200, 200));
	g.fillRect(0,0, this.getSize().width, this.getSize().height);
	g.setColor(Color.BLACK);


	for(Point p: this.points){
	    //System.out.println("Output:" + p.x + ":" + p.y +  "\t at: " + (int)adjustX(p.x) + ":" + (int)adjustY(p.y));
	    g.fillRect( (int)adjustX(p.x) - (SYMBOL_SIZE / 2) , (int)adjustY(p.y) - (SYMBOL_SIZE / 2), SYMBOL_SIZE, SYMBOL_SIZE);
	}
    }

}
