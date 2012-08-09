package gatsp;
import java.util.Vector;
/** Generates random chromosones when provided with a pre-built landscape.
 * @author Stephen Wattam
 */
public class SineChromosoneFactory implements ChromosoneFactory{
    /** The landscape for which to generate solutions */
    Landscape l;

    int shells;

    PolarCity[] pcities;

    /** Creates a SineChromosoneFactory with a random seed. */
    public SineChromosoneFactory(Landscape l, int shells){
	this.l = l;
	this.shells = shells;
	build_polar_coordinates();
    }


    public void build_polar_coordinates(){
	City[] cities = l.getCities();
	pcities = new PolarCity[cities.length];

	//determine centre of cities
	double xmean = 0;
	double ymean = 0;
	for(City c: cities){
	    xmean += c.x;
	    ymean += c.y;
	}
	xmean /= cities.length;
	ymean /= cities.length;


	//polarly determine thingies
	for(int i=0; i<l.size(); i++)
	    pcities[i] = new PolarCity( cities[i].x - xmean, cities[i].y - ymean, i);
    }


    /** Returns a new chromosone with random ordering of city visits.  The path produced is always hamiltonian.
     * @return A brand, spanking-new chromosone
     */
    public Chromosone newChromosone(){
	int[] result = new int[ l.size() ];
	//System.out.println("`\n\n");

	java.util.Arrays.sort(this.pcities);

	//hold subloops in arrays
	int[][] arrays = new int[shells][result.length];
	int[] array_counts = new int[shells];

	//determine best split point
	double rstep = 0;
	for(PolarCity pc: pcities){
	    if( pc.r > rstep)
		rstep = pc.r;
	}
	//System.out.println("max: " + rstep);
	rstep /= (shells - 1);

	//fit them into categories
	int index = 0;
	for(int i=0; i<pcities.length; i++){
	    //System.out.println("theta: " + pcities[i].theta + ", r=" + pcities[i].r + "   thingy=" +  Math.sin(pcities[i].theta / 2));
	    //mod to find out shell
	    //System.out.println("steps: " + shells + "  pcities.r = " + pcities[i].r + "   rstep = " + rstep);
	    index = Math.abs((int)(pcities[i].r / rstep));
	    arrays[index][array_counts[index]++] = pcities[i].original_index;
	}

	//recombobulate into a single final path
	int counter = 0;
	for(int i=0; i<array_counts.length; i++){
	    System.arraycopy(arrays[i], 0, result, counter, array_counts[i]);
	    counter += array_counts[i];
	}

	return new Chromosone(result);
    }




    public class PolarCity implements Comparable{
	public double r;
	public double theta;
	public int original_index;

	private double pythag(double x, double y){
	    return Math.sqrt( Math.pow(x,2) + Math.pow(y,2));
	}

	public PolarCity(double x, double y, int original_index){
	    this.original_index = original_index;
	    this.r = pythag(x, y);

	    if( r == 0 )
		theta = 1.0;
	    else
		theta = Math.atan2( y, x );
	}

	public int compareTo(Object o){
	    PolarCity c = (PolarCity)o;
	    //adjust for precision
	    return (int)((this.theta * 1000000) - (c.theta * 1000000));
	}
    }   




}


