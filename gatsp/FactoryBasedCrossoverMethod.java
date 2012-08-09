package gatsp;
public class FactoryBasedCrossoverMethod implements CrossoverMethod{

    private ChromosoneFactory cf;

    public FactoryBasedCrossoverMethod(ChromosoneFactory cf){
	this.cf = cf;
    }

    public Chromosone performCrossover(Chromosone p1, Chromosone p2){
	return cf.newChromosone();
    }

}
