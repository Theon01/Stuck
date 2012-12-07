package ma.aui.scmtool.model;

import java.util.Collection;

public class Method extends Scope 
{
	
    private Collection<Statement> statements;
	
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

	public Method(String codeString, Collection<Statement> statements) {
		super(codeString);
		this.statements = statements;
	}

}
