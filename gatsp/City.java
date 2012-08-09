package gatsp;
/** Represents a city to the TSP.
 * This is a single point on a 2D euclidean surface. 
 * @author Stephen Wattam
 */ 
public class City extends java.awt.Point{
   
   /**  Creates a new city at the given co-ordinates. 
    * @param x The x coordinate
    * @param y The y coordinate
    */ 
    public City(int x, int y){
	super(x, y);
    }

}
