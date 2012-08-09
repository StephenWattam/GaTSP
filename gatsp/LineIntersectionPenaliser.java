package gatsp;

import java.awt.geom.Line2D;

public class LineIntersectionPenaliser implements FitnessFunction{
    
    FitnessFunction ff;
    Landscape l;

    public LineIntersectionPenaliser(Landscape l, FitnessFunction ff){
	this.ff = ff;
	this.l = l;
    }

    private Line2D.Double[] getLines(Chromosone c){
	Line2D.Double[] lines = new Line2D.Double[c.genes.length];
	City[] ccache = l.getCities();

	lines[0] = new Line2D.Double(ccache[c.genes[c.genes.length-1]].x,   ccache[c.genes[c.genes.length-1]].y,
				     ccache[c.genes[0]].x,		    ccache[c.genes[1]].y);
	int count = 0;
	for(int i=1; i<c.genes.length; i++){
	    lines[i] = new Line2D.Double(ccache[c.genes[i-1]].x, ccache[c.genes[i-1]].y,
					 ccache[c.genes[i]].x,	 ccache[c.genes[i]].y);
	}
	return lines; 
    }

    //this is n^2, ugh.
    public double evaluate(Chromosone c){
	Line2D.Double[] lines = getLines(c);
	long maxlines = (long)Math.pow(lines.length, 2);

	long penalties = 0;

	for(int i=0; i<lines.length; i++){
	    for(int j=0; j<lines.length; j++){
		//System.out.println("i = " + i +", j=" + j);
		if(i != j && lines[i].intersectsLine(lines[j]))
		    penalties++;
	    }
	}
	
	
	//System.out.println(penalties + " penalties for line crossings - multiplier = " + (1.0 - ((double)penalties / (double)maxlines)));
	return (1.0 - ((double)penalties / (double)maxlines)) * ff.evaluate(c);
    }
}
