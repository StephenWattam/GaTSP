package gatsp;
import java.util.Random;
/** Produces random cities using the source of randomness given.  Random cities may have coordinates from anywhere
 * within the range of valid values.
 * @author Stephen Wattam
 */ 
public class RandomCityFactory implements CityFactory{
  
    /** Stores the random source for this object */ 
    Random r;
   
    /** Creates a new factory with a given seed.  This can be useful for re-running situations
     * @param seed The seed with which to initialise the source of entropy
     */ 
    public RandomCityFactory(long seed){
	r = new Random(seed);
    }

    /** Creates a new RandomCityFactory with a random seed. */
    public RandomCityFactory(){
	r = new Random();
    }

    /** Creates a new RandomCityFactory using the given source of entropy.
     * @param r The source of random numbers to use
     */ 
    public RandomCityFactory(Random r){
	this.r = r;
    }

    /** Implementation of CityFactory.newCity.
     * @see CityFactory#newCity
     */
    public City newCity(){
	return new City(r.nextInt(), r.nextInt());
    }	
}
