package gatsp;
/** A fitness function that simply sums the distances between adjacent city visits, resulting in the length of the path.
 * @author Stephen Wattam
 */ 
public class PathLengthFitnessFunction implements FitnessFunction{
 
    /** The landscape to use for evaluating */  
    private Landscape l; 

    /** The longest path possible on said landscape.
     * This ensures we prioritise low scores, since in this framework high values mean good agents
     * I know, the neater way is probably to invert the scoring system
     */ 
    private double worst_case_path;

    /** Creates a new PathLengthFitnessFunction */
    public PathLengthFitnessFunction(Landscape l, int landscape_width, int landscape_height){
	this.l = l;
	this.worst_case_path = Math.sqrt( (landscape_width * landscape_width) + (landscape_height * landscape_height) ) * (double)l.size();
	//System.out.println("Worst case on given landscape: " + worst_case_path);
    }

    /** Evaluates a chromosone.
     * @see FitnessFunction#evaluate(Chromosone)
     */
    public double evaluate(Chromosone c){
	double path_length;

	//first and last join up
	path_length = l.getDistance( c.genes[0], c.genes[c.genes.length - 1] );

	//then do all in between
	for(int i=1; i<c.genes.length; i++)
	    path_length += l.getDistance( c.genes[i-1], c.genes[i] );
	


	return worst_case_path - path_length;
    }
}
