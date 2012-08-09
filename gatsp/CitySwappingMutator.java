package gatsp;
import java.util.Random;
/**Swaps two cities in the visit order of a chromosone.
 * @author Stephen Wattam
 */
public class CitySwappingMutator implements Mutator{

    /** Stores the random source for this object */ 
    Random r;
   
    /** Creates a new factory with a given seed.  This can be useful for re-running situations
     * @param seed The seed with which to initialise the source of entropy
     */ 
    public CitySwappingMutator(long seed){
	r = new Random(seed);
    }

    /** Creates a CitySwappingMutator with a random seed. */
    public CitySwappingMutator(){
	r = new Random();
    }

    /** Creates a new CitySwappingMutator using the given source of entropy.
     * @param r The source of random numbers to use
     */ 
    public CitySwappingMutator(Random r){
	this.r = r;
    }
    
    /** Mutate a given Chromosone.
     * @see Mutator#mutate(Chromosone)
     */
    public Chromosone mutate(Chromosone a){
	//select two random points
	int g1 = r.nextInt( a.genes.length );
	int g2 = r.nextInt( a.genes.length );

	//swap them
	int temp = a.genes[g1];
	a.genes[g1] = a.genes[g2];	
	a.genes[g2] = temp;

	return a;
    }
}
