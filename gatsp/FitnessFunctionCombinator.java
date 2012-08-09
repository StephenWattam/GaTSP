package gatsp;


public class FitnessFunctionCombinator implements FitnessFunction{
   
    FitnessFunction[] functions;

    public FitnessFunctionCombinator(FitnessFunction ... functions){
	this.functions = functions;
    }

    
    public double evaluate(Chromosone c){
	double total = 0;
	for(FitnessFunction ff: this.functions)
	    total += (ff.evaluate(c) * 1/functions.length);

	return total;
    }

}
