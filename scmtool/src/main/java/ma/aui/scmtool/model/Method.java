package ma.aui.scmtool.model;

import java.util.Collection;
import java.util.Vector;

public class Method extends Scope 
{
	
    private Collection<Statement> statements = new Vector<Statement>();
	private Metric totalOfLevels;
	private Metric maximumOfLevels;
	
	private Metric totalOfOperators;
	private Metric maximumOfOperators;
	
	private Metric totalDataFlow;
	private Metric maximumDataFlow;
	
	private Metric totalDataUsage;
	private Metric maximumDataUsage;

	public Metric getTotalOfLevels() {
		return totalOfLevels;
	}

	public Metric getMaximumOfLevels() {
		return maximumOfLevels;
	}

	public Metric getTotalOfOperators() {
		return totalOfOperators;
	}

	public Metric getMaximumOfOperators() {
		return maximumOfOperators;
	}

	public Metric getTotalDataFlow() {
		return totalDataFlow;
	}

	public Metric getMaximumDataFlow() {
		return maximumDataFlow;
	}

	public Metric getTotalDataUsage() {
		return totalDataUsage;
	}

	public Metric getMaximumDataUsage() {
		return maximumDataUsage;
	}

	public void setTotalOfLevels(Metric totalOfLevels) {
		this.totalOfLevels = totalOfLevels;
	}

	public void setMaximumOfLevels(Metric maximumOfLevels) {
		this.maximumOfLevels = maximumOfLevels;
	}

	public void setTotalOfOperators(Metric totalOfOperators) {
		this.totalOfOperators = totalOfOperators;
	}

	public void setMaximumOfOperators(Metric maximumOfOperators) {
		this.maximumOfOperators = maximumOfOperators;
	}

	public void setTotalDataFlow(Metric totalDataFlow) {
		this.totalDataFlow = totalDataFlow;
	}

	public void setMaximumDataFlow(Metric maximumDataFlow) {
		this.maximumDataFlow = maximumDataFlow;
	}

	public void setTotalDataUsage(Metric totalDataUsage) {
		this.totalDataUsage = totalDataUsage;
	}

	public void setMaximumDataUsage(Metric maximumDataUsage) {
		this.maximumDataUsage = maximumDataUsage;
	}

	

	public Collection<Statement> getStatements() {
		return statements;
	}

	public void setStatements(Collection<Statement> statements) {
		this.statements = statements;
	}
	
	public Method(String codeString)
	{
		super(codeString);
		
		totalOfLevels =  new IntegerMetric("Total of Levels", new Integer(0));
		maximumOfLevels = new IntegerMetric("Maximum of Levels", new Integer(0));
		
		totalOfOperators = new IntegerMetric("Total of Operators", new Integer(0));
		maximumOfOperators = new IntegerMetric("Maximum of Operators", new Integer(0));
		
		totalDataFlow = new IntegerMetric("Total of Data Flow", new Integer(0));
		maximumDataFlow =  new IntegerMetric("Maximum of Data Flow", new Integer(0));
		
		totalDataUsage = new IntegerMetric("Total of Data Usage", new Integer(0));
        maximumDataUsage = new IntegerMetric("Maximum of Data Usage", new Integer(0));
	}

}
