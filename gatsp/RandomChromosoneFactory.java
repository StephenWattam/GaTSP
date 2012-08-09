package gatsp;
import java.util.Random;
import java.util.Vector;
/** Generates random chromosones when provided with a pre-built landscape.
 * @author Stephen Wattam
 */
public class RandomChromosoneFactory implements ChromosoneFactory{
    /** Stores the random source for this object */ 
    Random r;
    /** The landscape for which to generate solutions */
    Landscape l;
   
    /** Creates a new factory with a given seed.  This can be useful for re-running situations
     * @param seed The seed with which to initialise the source of entropy
     */ 
    public RandomChromosoneFactory(long seed, Landscape l){
	r = new Random(seed);
	this.l = l;
    }

    /** Creates a RandomChromosoneFactory with a random seed. */
    public RandomChromosoneFactory(Landscape l){
	r = new Random();
	this.l = l;
    }

    /** Creates a new RandomChromosoneFactory using the given source of entropy.
     * @param r The source of random numbers to use
     */ 
    public RandomChromosoneFactory(Random r, Landscape l){
	this.r = r;
	this.l = l;
    }

    /** Returns a new chromosone with random ordering of city visits.  The path produced is always hamiltonian.
     * @return A brand, spanking-new chromosone
     */
    public Chromosone newChromosone(){
	int[] result = new int[ l.size() ];

	//enumerate all city indices in a mutable structure
	Vector<Integer> options = new Vector<Integer>( result.length );
	for(int i=0; i<l.size(); i++)
	    options.add(new Integer(i));    //hideous, but Vectors don't accept 'primative types'

	for(int i=0; i<result.length; i++)
	    result[i] = options.remove( r.nextInt( options.size() ));
	
	return new Chromosone(result);
    }
}
