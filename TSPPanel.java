import gatsp.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
/** A panel that represents cities and a single route.
 * @author Stephen Wattam
 */
public class TSPPanel extends ScatterPanel{
   

    /** The landscape to draw on the panel */
    private Landscape l;

    /** The chromosone to plot */
    private Chromosone[] c = null;

    private Image buffer;
	

    /** Creates a new TSPPanel with the given landscape. 
     * @param l The landscape to plot
     */
    public TSPPanel( Landscape l ){
	this.l = l;
	
	City[] cities = l.getCities();
	for(City c: cities)
	    addPoint(c);

	buffer = super.createImage(this.getWidth(), this.getHeight());

    }

    /** Plot a new Chromosone.
     * @param c The chromosone to plot.
     */
    public void drawRoute( Chromosone ... c){
	this.c = c;
	repaint();
    }

    private void drawChromosone(Graphics2D g, Chromosone c, Color col){
	g.setColor(col);

	//no chromosone has been set yet?
	if(c == null)
	    return;

	//create new poly
	Polygon poly = new Polygon();


	//add points from the cities to the polygon
	City[] cities = l.getCities();
	for(int i=0; i<c.genes.length; i++)
	    poly.addPoint( (int)adjustX(cities[ c.genes[i] ].x), (int)adjustY(cities[ c.genes[i] ].y) );
	
	//draw the thing
	g.drawPolygon( poly );
    }

    
    /**Internal paint method.... */
    public void paint(Graphics g){
	//javax.swing.RepaintManager.currentManager(null).setDoubleBufferingEnabled(true);

	buffer = super.createImage(this.getWidth(), this.getHeight());
	Graphics g2 = buffer.getGraphics();	
	super.paint(g2);

	try{
	for(int i=0; i<c.length; i++){
	    drawChromosone((Graphics2D)g2, c[i], new Color(
							0, 
							0,
							55,
							50 + (i * (178 / c.length) )));
	}
	
	g.drawImage( buffer, 0, 0, (java.awt.image.ImageObserver)null);
	}catch(Exception e){
	    System.err.println("Drawing error.  This happens occasionally, fret not.");
	}
    }
}
