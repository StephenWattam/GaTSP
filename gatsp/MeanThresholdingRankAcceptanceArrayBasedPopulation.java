package gatsp;
import java.util.Random;
public class MeanThresholdingRankAcceptanceArrayBasedPopulation extends RankAcceptanceArrayBasedPopulation{

    FitnessFunction ff;

    /** Creates a new factory with a given seed.  This can be useful for re-running situations
     * @param seed The seed with which to initialise the source of entropy
     */ 
    public MeanThresholdingRankAcceptanceArrayBasedPopulation(long seed, int num_entries, ChromosoneFactory cf, FitnessFunction ff){
	super(seed, num_entries, cf);
	this.ff = ff;
    }

    /** Creates a MeanThresholdingRankAcceptanceArrayBasedPopulation with a random seed. */
    public MeanThresholdingRankAcceptanceArrayBasedPopulation(int num_entries, ChromosoneFactory cf, FitnessFunction ff){
	super(num_entries, cf);
	this.ff = ff;
    }

    /** Creates a new MeanThresholdingRankAcceptanceArrayBasedPopulation using the given source of entropy.
     * @param r The source of random numbers to use
     */ 
    public MeanThresholdingRankAcceptanceArrayBasedPopulation(Random r, int num_entries, ChromosoneFactory cf, FitnessFunction ff){
	super(r, num_entries, cf);
	this.ff = ff;
    }


    /** Creates a new factory with a given seed.  This can be useful for re-running situations
     * @param seed The seed with which to initialise the source of entropy
     */ 
    public MeanThresholdingRankAcceptanceArrayBasedPopulation(long seed, Chromosone[] csones, FitnessFunction ff){
	super(seed, csones);
	this.ff = ff;
    }

    /** Creates a MeanThresholdingRankAcceptanceArrayBasedPopulation with a random seed. */
    public MeanThresholdingRankAcceptanceArrayBasedPopulation(Chromosone[] csones, FitnessFunction ff){
	super(csones);
	this.ff = ff;
    }

    /** Creates a new MeanThresholdingRankAcceptanceArrayBasedPopulation using the given source of entropy.
     * @param r The source of random numbers to use
     */ 
    public MeanThresholdingRankAcceptanceArrayBasedPopulation(Random r, Chromosone[] csones, FitnessFunction ff){
	super(r, csones);
	this.ff = ff;
    }



    public int accept(Chromosone[] c){
	double score;
	double mean = (super.getStats()[1]);// + super.getStats()[0]) / 2;

	
	Chromosone[] accept_list = new Chromosone[c.length];
	int count = 0;

	for(int i=0; i<c.length; i++){
	    score = ff.evaluate(c[i]);
	    //System.out.println("Score=" + score + "  mean=" + mean);
	    if(score > mean)
		accept_list[count++] = c[i];
	}
	
	Chromosone[] short_list = new Chromosone[count];
	System.arraycopy(accept_list, 0, short_list, 0, count);
	return super.accept(short_list);
    }

    
}
