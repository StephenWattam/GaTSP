
package gatsp;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class PluralGeneticAdministrator{

    //private Landscape		land;
    //private Population		pop;
    //private FitnessFunction	fitness;
    //private Mutator		mutator;
    //private CrossoverMethod	crossover;
    //private Selector		selection;

    ////number of times to cross over
    //private int number_of_couples;
    //private int number_of_offspring;
    //private int futility_threshold;



    private GeneticAdministrator[] admins;
    private boolean[] active;


    public PluralGeneticAdministrator(Landscape land, 
	    Population pop, 
	    FitnessFunction fitness, 
	    Mutator mutator, 
	    CrossoverMethod crossover, 
	    Selector selection,
	    int number_of_couples,
	    int number_of_offspring,
	    int futility_threshold,
	    int number){


	//clone these!	
	//this.land = land;
	//this.pop = pop;
	//this.fitness = fitness;
	//this.mutator = mutator;
	//this.crossover = crossover;
	//this.selection = selection;
	//this.number_of_couples = number_of_couples;
	//this.number_of_offspring = number_of_offspring;
	//this.futility_threshold = futility_threshold;

	admins = new GeneticAdministrator[number];
	for(int i=0; i<admins.length; i++){
	    admins[i] = new GeneticAdministrator(	
		    ((Landscape)uglyDeepCopy(land)), 
		    ((Population)	uglyDeepCopy		(pop)), 
		    ((FitnessFunction)uglyDeepCopy		(fitness)), 
		    ((Mutator)uglyDeepCopy		(mutator)), 
		    ((CrossoverMethod)		uglyDeepCopy		(crossover)), 
		    ((Selector)	uglyDeepCopy		(selection)),
		    number_of_couples,
		    number_of_offspring,
		    futility_threshold);
	}

    }

    private static Object uglyDeepCopy(Object objin){
	Object o = null;
	try {
	    // Write the object out to a byte array
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ObjectOutputStream out = new ObjectOutputStream(bos);
	    out.writeObject(objin);
	    out.flush();
	    out.close();

	    // Make an input stream from the byte array and read
	    // a copy of the object back in.
	    ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
	    o = in.readObject();
	}catch(IOException e) {
	    e.printStackTrace();
	}catch(ClassNotFoundException cnfe) {
	    cnfe.printStackTrace();
	}
	return o;
    }

    public boolean iterate(){
	boolean result = false;
	for(int i=0; i<admins.length; i++){
	    if(admins[i].iterate()){
		System.out.print("[ " + i + " ] ");
		admins[i].printStats();
		result = true;
	    }
	}
	return result;
    }

}
