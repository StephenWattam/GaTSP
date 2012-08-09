package gatsp;
import java.text.*;
/** This class orchestrates iterations of scoring, sorting, etc.
 * @author Stephen Wattam
 */
public class GeneticAdministrator{
    
    private Landscape		land;
    private Population		pop;
    private FitnessFunction	fitness;
    private Mutator		mutator;
    private CrossoverMethod	crossover;
    private Selector		selection;

    //number of times to cross over
    private int number_of_couples;
    private int number_of_offspring;
    private int futility_threshold;

    public GeneticAdministrator(Landscape land, 
	    Population pop, 
	    FitnessFunction fitness, 
	    Mutator mutator, 
	    CrossoverMethod crossover, 
	    Selector selection,
	    int number_of_couples,
	    int number_of_offspring,
	    int futility_threshold){
	
	this.land = land;
	this.pop = pop;
	this.fitness = fitness;
	this.mutator = mutator;
	this.crossover = crossover;
	this.selection = selection;
	this.number_of_couples = number_of_couples;
	this.number_of_offspring = number_of_offspring;
	this.futility_threshold = futility_threshold;

	LevelBasedLog.outln(LevelBasedLog.Levels.DEBUG, "Administrator '" + this + "' created.");
    }

    public boolean iterate(){
	
	pop.rebuildScores_fast(fitness);
        pop.sort();
	

	Chromosone[] parents;
	Chromosone[] offspring = new Chromosone[ number_of_couples ];
	
	int offspring_accepted = 0;
	int offspring_accepted_now = 0;
	int futility_counter = 0;
	int mixer_threshold = futility_threshold;

	while(offspring_accepted < number_of_offspring){
	    futility_counter ++;
	    if(futility_counter > (mixer_threshold * .80)){
		parents = selection.getParents(pop.size(), pop);
		for(Chromosone c: parents)
		    mutator.mutate( c );
		pop.rebuildScores( fitness ); 
	    }

	    if(futility_counter > futility_threshold)
		return false;	
	    

	    for(int i=0; i<number_of_couples; i++){
		parents	    = selection.getParents(2, pop);
		offspring[i]    = crossover.performCrossover( parents[0], parents[1] );
		mutator.mutate( offspring[i] );
	    }

	    //System.out.println("Stuffs: " + offspring.length);
	    offspring_accepted_now = pop.accept( offspring );
	    if(offspring_accepted_now > 0){
		futility_counter = 0;
		offspring_accepted += offspring_accepted_now;
	    }


	    pop.rebuildScores(fitness);
	}
	return true;
    }

    public void printStats(){
	double[] stats = pop.getStats();
	DecimalFormat printFormat = new DecimalFormat("#0.000");
	System.out.println( 
		printFormat.format(stats[2]) + 
		"\t  " + printFormat.format(stats[1]) + 
		"\t  " + printFormat.format(stats[0]));
    }
}
