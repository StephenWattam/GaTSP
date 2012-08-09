package gatsp;
import java.util.Vector;
import java.util.Random;
/** Generates random chromosones when provided with a pre-built landscape.
 * @author Stephen Wattam
 */
public class NNChromosoneFactory implements ChromosoneFactory{
    /** Stores the random source for this object */ 
    Random r;
    /** The landscape for which to generate solutions */
    Landscape l;
   
    /** Creates a new factory with a given seed.  This can be useful for re-running situations
     * @param seed The seed with which to initialise the source of entropy
     */ 
    public NNChromosoneFactory(long seed, Landscape l){
	r = new Random(seed);
	this.l = l;
    }

    /** Creates a NNChromosoneFactory with a random seed. */
    public NNChromosoneFactory(Landscape l){
	r = new Random();
	this.l = l;
    }

    /** Creates a new NNChromosoneFactory using the given source of entropy.
     * @param r The source of random numbers to use
     */ 
    public NNChromosoneFactory(Random r, Landscape l){
	this.r = r;
	this.l = l;
    }

    /** Returns the index number in options that is the closest to the given city. */
    private int getNearestNeighbor(int city, Vector<Integer> options){
	int nearest = 0;
	double min_distance = l.getDistance(city, options.get(nearest).intValue());
		
	for(int i=0; i<options.size(); i++){
	    
	    if( l.getDistance(city, options.get(i).intValue()) < min_distance){
		min_distance = l.getDistance(city, options.get(i).intValue());
		nearest = i;
		//System.out.println("new min: " + min_distance);
	    }
	}
	//System.out.println("NN = " + min_distance + ", id=" + nearest + "/" + options.size());
	return nearest;
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


	//select a random beginning thingy
	result[0] = options.remove( r.nextInt( options.size() - 1));
	
	for(int i=1; i<result.length - 1; i++)
	    result[i] = options.remove(getNearestNeighbor(result[i-1], options));
	//fill in last one
	result[result.length - 1] = options.remove(0);

	return new Chromosone(result);
    }
}
