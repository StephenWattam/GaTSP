import java.awt.Point;
import java.text.*;
import gatsp.*;

public class Test{

    public static final int LANDSCAPE_WIDTH  = 640;
    public static final int LANDSCAPE_HEIGHT = 480;

    public static final int LANDSCAPE_SEED = 10;
    //not used below
    public static final int SELECTOR_SEED  = 1;
    public static final int CHROM_FACTORY_SEED  = 1;
    public static final int MUTATOR_SEED  = 1;
    public static final int CROSSOVER_SEED  = 1;

    public static final int FUTILITY_COUNT = 40;


    //public static final int = ;
    public static final int NUMBER_OF_CHROMOSONES = 50;
    public static final int NUMBER_OF_CITIES = 70;
    public static final int NUMBER_OF_PAIRS = 10;
    public static final int NUMBER_OF_OFFSPRING = 8;


    //used for formatting output only.  This is Java's hideous way of doing things
    private static DecimalFormat printFormat = new DecimalFormat("#0.000");
    
    
    public static void main(String[] args){
	LevelBasedLog.level = LevelBasedLog.Levels.STD;
	//FastRandom frand = new FastRandom( Integer.MAX_VALUE );

	CityFactory cf = new BoundedRandomCityFactory(LANDSCAPE_SEED/* new FastRandom()*/ );
        try{ ((BoundedRandomCityFactory)cf).setBounds(LANDSCAPE_WIDTH, LANDSCAPE_HEIGHT); }catch(Exception e){}

	Landscape l = new Landscape(NUMBER_OF_CITIES, cf);
	
	Selector rs = new RankSelector( /*new FastRandom() */);


	//This fitness function must know the size of the landscape, to calculate a worst case.
	FitnessFunction ff1 = new PathLengthFitnessFunction(l, LANDSCAPE_WIDTH, LANDSCAPE_HEIGHT);
	FitnessFunction ff2 = new CircleFitnessFunction(l, LANDSCAPE_WIDTH, LANDSCAPE_HEIGHT);
	//FitnessFunction ff = new FitnessFunctionCombinator( ff1, ff2 );
	FitnessFunction ff = new LineIntersectionPenaliser(l, ff1 );

	/* This is where I built too many ChromosoneFactories and thus thought I'd make them fight for the right to,
	 * well, generate chromosones.  
	 *
	 * Some of these work in cunning ways, others not so cunning.  They can be formed into a monte carlo system by
	 * using the crossover method that simply generates new offspring, though I realise this is somewhat against the
	 * spirit of writing a GA, it does work better than lots of GAs ;-)
	 *
	 * For now this is just used to create some variation in the starting population, though it's a very elaborate
	 * method for doing so.
	 */

	//Random path
	ChromosoneFactory csf1 = new RandomChromosoneFactory(/*new FastRandom(),*/ l);

	//nearest neighbor - awesome paths
	ChromosoneFactory csf2 = new NNChromosoneFactory(/*new FastRandom(),*/ l);
	
	//Attempts to make a circular path using polar coordinates
	//this maximises the area contained within
	//in the near future I aim to make this into a fitness function: the ideal route should tend towards pi
	ChromosoneFactory csf3 = new CircleChromosoneFactory(/*new FastRandom(),*/ l);
	
	//This one's been superceded by the one below, which essentially does the same thing
	//ChromosoneFactory csf = new SineChromosoneFactory([>new FastRandom(),<] l);
	
	//Creates 'shells' of routes around the net, similar to how electrons work.  I was going to use periodic
	//functions and divisors, but made the same effect using polar coordinates, so here's the efficient version
	ChromosoneFactory csf4 = new RandomSineChromosoneFactory(/*new FastRandom(),*/ l, 100);

	//battle!
	ChromosoneFactory csf = new ChromosoneFactoryRandomiser( csf1, csf2, csf3, csf4 );









	CrossoverMethod cm1 = new EdgeRecombinationCrossoverMethod(l);
	CrossoverMethod cm2 = new RandomSinglePointCrossoverMethod();
	CrossoverMethod cm3 = new SinglePointCrossoverMethod();
	CrossoverMethod cm4 = new FactoryBasedCrossoverMethod( csf );
	//beware, this retains references and breaks shit
	//CrossoverMethod cm5 = new RandomParentCrossoverMethod();
	
	CrossoverMethod cm = new RandomCrossoverMethod( cm1, cm2, cm3, cm4 );



	//Population p = new ArrayBasedPopulation(NUMBER_OF_CHROMOSONES, csf);
	//Population p = new UniqueArrayBasedPopulation(NUMBER_OF_CHROMOSONES, csf);
	Population p = new MeanThresholdingArrayBasedPopulation(NUMBER_OF_CHROMOSONES, csf4, ff);
	//Population p = new RankAcceptanceArrayBasedPopulation(NUMBER_OF_CHROMOSONES, csf);
	//Population p = new MeanThresholdingRankAcceptanceArrayBasedPopulation(NUMBER_OF_CHROMOSONES, csf, ff);

	GeneticAdministrator ga = new GeneticAdministrator(
					l,
					p,
					ff,
					new RandomMultiplePassMutator( /*new FastRandom(),*/ 0.01, 10, new CitySwappingMutator(/*new FastRandom()*/) ),
					cm,
					rs,
					NUMBER_OF_PAIRS,
					NUMBER_OF_OFFSPRING,
					FUTILITY_COUNT
					);

	
	//irrelevant shit
	TSPPanel panel = new TSPPanel(l);
	ArbitraryComponentFrame frame = new ArbitraryComponentFrame(400, 300, "TSP", panel);
    
	//iterate-print loop
	double last = 0;
	int i=0;
	ga.iterate();
	while(ga.iterate()){
	    
	    
	    if( (i % 1) == 0 ){
		double[] stats = p.getStats();
		
                /*
		 *for(int j=0; j<p.size(); j++)
		 *    System.out.print("," + ff.evaluate( p.getChromosone(j) ) );
		 *System.out.println();
                 */


		System.out.println(i + ": " + 
				"\t  " + printFormat.format( ff1.evaluate(p.getChromosone(NUMBER_OF_CHROMOSONES - 1))) + 
				//"\t  " + printFormat.format(stats[1]) + 
				"\t  " + printFormat.format( ff1.evaluate(p.getChromosone(0))));

		//System.out.println(i + ": " + 
				//"\t  " + printFormat.format(stats[2]) + 
				//"\t  " + printFormat.format(stats[1]) + 
				//"\t  " + printFormat.format(stats[0]));


		if(last != stats[2]){
		    panel.drawRoute(	p.getChromosone(NUMBER_OF_CHROMOSONES - 1)
					//,
					//p.getChromosone(NUMBER_OF_CHROMOSONES - 10),
					//p.getChromosone(NUMBER_OF_CHROMOSONES - 20),
					//p.getChromosone(NUMBER_OF_CHROMOSONES - 30),
					//p.getChromosone(NUMBER_OF_CHROMOSONES - 40),
					//p.getChromosone(NUMBER_OF_CHROMOSONES - 56),
					//p.getChromosone(NUMBER_OF_CHROMOSONES - 60),
					//p.getChromosone(1) //NUMBER_OF_CHROMOSONES - 80)
				);

		    //the thing got worse, which means it's not even a good monte carlo
		    //probably got the params wrong if this fires
		    if( last > stats[2] ){
			System.out.println("------- megacocks --------");
			//try{ Thread.sleep( Long.MAX_VALUE); }catch(Exception e){ }
		    }

		    last = stats[2];
		}
	    }
	    i++;
	}

	System.out.println("------------- done ------------");
    
    }	
    
/*
 *    public static void main(String[] args){
 *        LevelBasedLog.level = LevelBasedLog.Levels.STD;
 *        
 *                
 *        BoundedRandomCityFactory cf = new BoundedRandomCityFactory();
 *        try{ cf.setBounds(100, 100); }catch(Exception e){}
 *        
 *
 *        //25 points
 *        Landscape l = new Landscape(25, cf);
 *
 *        Selector rs = new RankSelector();
 *        RandomChromosoneFactory rcf = new RandomChromosoneFactory(l);
 *        PathLengthFitnessFunction ff = new PathLengthFitnessFunction(l);
 *
 *
 *
 *        Population p = new ArrayBasedPopulation(100, rcf);
 *        p.rebuildScores(ff);
 *
 *        p.sort();
 *        printPopulation(p);
 *
 *        rs.getParents(100, p);
 *
 *
 *
 *
 *
 *        //System.out.println(l.getDistance( new City(0,0), new City(3,4)));
 *
 *        //for(int i=0; i<100; i++)
 *            //LevelBasedLog.outln(LevelBasedLog.Levels.STD, "Chromosone score: " + ff.evaluate(rcf.newChromosone()) );
 *        
 *
 *        //ScatterPanel s = new ScatterPanel();
 *        //ArbitraryComponentFrame a = new ArbitraryComponentFrame(400, 400, "Scatter plot", s);
 *
 *
 *        //BoundedRandomCityFactory cf = new BoundedRandomCityFactory();
 *        //try{ cf.setBounds(100, 100); }catch(Exception e){}
 *        //for(int i=0; i<100; i++){
 *            //s.addPoint( cf.newCity()); 
 *        //}	    
 *
 *    }
 *
 *
 *    public static void printPopulation(Population p){
 *        System.out.println( "\n\nPopulation '" + p + "'.\n");
 *        //int[] min_max = p.getMinMax();
 *        //System.out.println("Min: " + min_max[0] + ", max = " + min_max[1]);
 *        //System.out.println("Average: " + p.getStats()[1]);
 *        
 *        for(int i=0; i<p.size(); i++)
 *            System.out.println(": " + p.getChromosone(i).score);
 *        
 *    }
 */
}
