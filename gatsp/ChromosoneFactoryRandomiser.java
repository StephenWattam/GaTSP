package gatsp;
import java.util.Random;

public class ChromosoneFactoryRandomiser implements ChromosoneFactory{

    Random r;
    ChromosoneFactory[] factories;

    /** Creates a new factory with a given seed.  This can be useful for re-running situations
     * @param seed The seed with which to initialise the source of entropy
     */ 
    public ChromosoneFactoryRandomiser(long seed, ChromosoneFactory ... factories){
	r = new Random(seed);
	this.factories = factories;
    }

    /** Creates a ChromosoneFactoryRandomiser with a random seed. */
    public ChromosoneFactoryRandomiser(ChromosoneFactory ... factories){
	r = new Random();
	this.factories = factories;
    }

    /** Creates a new ChromosoneFactoryRandomiser using the given source of entropy.
     * @param r The source of random numbers to use
     */ 
    public ChromosoneFactoryRandomiser(Random r, ChromosoneFactory ... factories){
	this.r = r;
	this.factories = factories;
    }

    
    public Chromosone newChromosone(){
	return factories[ r.nextInt(factories.length) ].newChromosone();
    }

}
