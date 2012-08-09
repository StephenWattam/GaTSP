package gatsp;
/** A CityFactory implementor is designed to populate a Landscape with cities. 
 * @author Stephen Wattam
 */ 
public interface CityFactory{

    /** Returns a City object 
     * @return A new City object 
     */ 
    public City newCity();
}
