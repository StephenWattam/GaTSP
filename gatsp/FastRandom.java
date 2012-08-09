package gatsp;
/** A caching version of Java's Random.  This class pre-calculates doubles and then uses this list to return calls to
 * nextDouble() faster.  I realise it's slower overall, but it presents a more fluid interation process for the UI (in
 * profiling Random.nextDouble proved to be a major overhead).
 * @author Stephen Wattam
 */
public class FastRandom extends java.util.Random{

    /** Holds a cache of doubles. */ 
    private double[] double_cache;
    /** Points at the next double to return */
    private int counter = 0; //unfortunately Java's array system uses ints.
    /** The length of the double cache.  Storing this here is faster than calling double_cache.length */
    private int max_length;

    /** Creates a new FastRandom source with 20000000 doubles stored.  New doubles will be generated when these expire. */
    public FastRandom(){
	this( 20000000 );//Integer.MAX_VALUE );
    }

    /** Creates a new FastRandom source with 20000000 doubles that conform to a given seed. */
    public FastRandom(long seed){
	this(  20000000 );//Integer.MAX_VALUE, seed );
    }

    /** Creates a new FastRandom with a random seed and a given number of doubles in its cache.
     * @param cache_size The number of doubles to return before having to rebuild the cache.
     */
    public FastRandom(int cache_size){
	super();
	this.max_length = cache_size;
	double_cache = new double[max_length];
	precompute_cache();
    }

    /** Creates a new FastRandom with the given seed.
     * @param cache_size The number of doubles to cache.
     * @param seed The seed to pass to Java.util.Random
     */
    public FastRandom(int cache_size, long seed){
	super(seed);
	this.max_length = cache_size;
	double_cache = new double[max_length];
	precompute_cache();
    }

    /** Precomputes the cache values, and hence allows for another round of n cache hits. */
    public void precompute_cache(){
	//System.out.println("Precompute!");

	for(int i=0; i<max_length; i++)
	    double_cache[i] = super.nextDouble();
	counter = 0;
    }

    /** Returns the next double.
     * @see java.util.Random#nextDouble()
     */
    public double nextDouble(){
	//return the next double
	if(counter < max_length)
	    return double_cache[ counter++ ];

	//re-seed and re-send
	//System.out.println("Exceeded cache length!");
	precompute_cache();
	return nextDouble();
    }
}
