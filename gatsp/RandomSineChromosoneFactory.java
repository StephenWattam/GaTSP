package gatsp;
import java.util.Random;

public class RandomSineChromosoneFactory extends SineChromosoneFactory implements ChromosoneFactory{

    int maxRandom;
    Random r;
	

    /** Creates a new factory with a given seed.  This can be useful for re-running situations
     * @param seed The seed with which to initialise the source of entropy
     */ 
    public RandomSineChromosoneFactory(long seed, Landscape l, int limit){
	super(l, 1);
	r = new Random(seed);
	this.maxRandom = limit;
    }

    /** Creates a RandomSineChromosoneFactory with a random seed. */
    public RandomSineChromosoneFactory(Landscape l, int limit){
	super(l, 1);
	r = new Random();
	this.maxRandom = limit;
    }

    /** Creates a new RandomSineChromosoneFactory using the given source of entropy.
     * @param r The source of random numbers to use
     */ 
    public RandomSineChromosoneFactory(Random r, Landscape l, int limit){
	super(l, 1);
	this.r = r;
	this.maxRandom = limit;
    }
    
    
    
    public Chromosone newChromosone(){
	this.shells = r.nextInt(maxRandom - 1) + 1;
	return super.newChromosone();
    }
}
