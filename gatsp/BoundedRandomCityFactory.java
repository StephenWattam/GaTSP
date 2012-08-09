package gatsp;
import java.util.Random;
/** Creates random cities within given bounds.  This is a restriction of the RandomCityFactory.  By default it produces
 * cities between 0,0 and 100,100
 * @author Stephen Wattam
 */ 
public class BoundedRandomCityFactory extends RandomCityFactory implements CityFactory{

    /** Holds the maximum possible X coordinate a city may take. */
    private int xmax = 100;
    /** Holds the maximum possible Y coordinate a city may take. */
    private int ymax = 100;

    /** Creates a new BoundedRandomCityFactory with a given seed.
     *	@see RandomCityFactory
     */	
    public BoundedRandomCityFactory(long seed){
	super(seed);
    }

    /** Creates a new BoundedRandomCityFactory with a random seed. 
     *	@see RandomCityFactory
     */
    public BoundedRandomCityFactory(){
	super();
    }

    /** Creates a new BoundedRandomCityFactory with the given source of entropy. 
     *	@see RandomCityFactory
     */
    public BoundedRandomCityFactory(Random r){
	super(r);
    }

    /** Sets the bounds of the random function used in creating cities.  Since Dimensions hold doubles <strong>there may be loss
     * of precision.</strong>
     * @param d The dimensions to use.  Negative values result in an exception.
     * @throws Exception when values below zero are given.  Zero is valid, as all cities may lie along an axis.
     */ 
    public void setBounds(java.awt.Dimension d) throws Exception{
	setBounds((int)d.getWidth(), (int)d.getHeight());
    }
    
    /** Sets the bounds of the random function used in creating cities.
     * @param x The maximum x-coordinate given to cities
     * @param y The maximum y-coodinate given to cities
     * @throws Exception when values below zero are given.  Zero is valid, as all cities may lie along an axis.
     */ 
    public void setBounds(int x, int y) throws Exception{
	if(x < 0 || y < 0)
	    throw new Exception("Bounds must not be below 0");
	
	this.xmax = x;
	this.ymax = y;
    }

    /** Returns the current bounds of the generation algorithm, in the form of a Dimension object.
     * @return The current bounds of the object
     */ 
    public java.awt.Dimension getBounds(){
	return new java.awt.Dimension(xmax, ymax);
    }

    /** Returns the maximum x-coordinate that a city may bear.
     * @return The largest possible x-coordinate a city may have.
     */ 
    public int getWidth(){
	return xmax;
    }

    /** Returns the maximum y-coodinate a city may be returned with.
     * @return The maximum y-coordinate a city may bear.
     */ 
    public int getHeight(){
	return ymax;
    }

    /** Implementation of CityFactory.newCity.
     * @see CityFactory#newCity
     */
    public City newCity(){
	City c = new City(r.nextInt(xmax), r.nextInt(ymax));
	return c;
    }	
    

}
