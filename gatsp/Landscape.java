package gatsp;
/** Holds a number of cities.  The distances between them are assumed to be euclidean and are calculated upon request.
 * @author Stephen Wattam
 */ 
public class Landscape{

    /** holds all city objects */
    private City[] cities;

    /** Hold running counts on dimensions of Landscape.  This may come in handy for certain stuffs. */
    private int xmax;
    /** Hold running counts on dimensions of Landscape.  This may come in handy for certain stuffs. */
    private int ymax;


    private double[][] distance_cache;

    /** Creates a new Landscape with a given number of cities on it.  Cities are provided by the CityFactory implementor
     * specified.
     * @param num_cities The number of cities to add.
     * @param city_src The place from which to get cities
     */ 
    public Landscape(int num_cities, CityFactory city_src){
	cities = new City[num_cities];
	for(int i=0; i<num_cities; i++)
	    cities[i] = city_src.newCity();
	updateDimensions(cities);
    }

    /** Creates a new Landscape from a pre-existing list of Cities.
     * @param cities The list of cities to use.
     */ 
    public Landscape(City[] cities){
	this.cities = cities;
	updateDimensions(cities);
    }

    /** Returns the number of cities on the landscape.
     * @return The number of cities on the landscape.
     */ 
    public int size(){
	return cities.length;
    }

    /** Maintain internal counter on dimensions 
     * @param c A list of cities to check.
     */ 
    private void updateDimensions(City[] c){
	for(City current: c)
	    updateDimensions(current);
    }

    /** Returns the x-coordinate of the topmost city, 
     * @return The maximum x-coordinate currently held.
     */
    public int getWidth(){
	return xmax;
    }

    /** Returns the y-coordinate of the topmost city, 
     * @return The maximum y-coordinate currently held.
     */
    public int getHeight(){
	return ymax;
    }

    /** Maintain internal counter on dimensions
     * @param c The city to check against xmax, ymax
     */ 
    private void updateDimensions(City c){
	if(c.x > xmax)
	    xmax = c.x;
	if(c.y > ymax)
	    ymax = c.y;
    }

    /** Returns the distance between two cities
     * @param a The index of one city
     * @param b The index of the second
     * @return The distance between a and b
     */ 
    public double getDistance(int a, int b){
	if(a == b)
	    return 0;
	return getDistance(cities[a], cities[b]);
    }

    /** Returns the distance between two cities.
     * @param a One city
     * @param b Another city
     * @return The distance between a and b
     */ 
    public double getDistance(City a, City b){
	//use pythag.
	// TODO: write caching engine to avoid having to do this calcuation a lot.
	return Math.sqrt( Math.pow((b.x - a.x), 2) + Math.pow((b.y - a.y), 2) );
    }

    /** Returns the internal list of cities.  This is designed to be used for observation, any modification of values
     * will result in incorrect city details (this class caches city locations)
     * @return All cities held currently.
     */
    public City[] getCities(){
	return this.cities;
    }
}
