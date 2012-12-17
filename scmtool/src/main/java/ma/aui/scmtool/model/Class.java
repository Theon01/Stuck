package ma.aui.scmtool.model;

import java.util.Collection;

public class Class extends Scope 
{
	Collection<Method> methods;
	
	private Metric totalOfOperators;
	private Metric maximumOfOperators;
	
	private Metric totalOfMaximumOperators;
	private Metric maximumOfTotalOperators;
	
	private Metric totalOfLevels;
	private Metric maximumOfLevels;
	
	private Metric totalOfMaximumLevels;
	private Metric maximumOfTotalLevels;
	
	private Metric totalDataFlow;
	private Metric maximumDataFlow;
	
	private Metric totalOfMaximumDataFlow;
	private Metric maximumOfTotalDataFlow;
	
	private Metric totalDataUsage;
	private Metric maximulDataUsage;
	
	private Metric totalOfMaximumDataUsage;
	private Metric maximumOfTotalDataUsage;
	
	private Metric totalOfMethodCalls;
	private Metric maximumOfMethodCalls;
	
	private Metric inOutDegree;
	private Metric numberOfPublicMembers;
	
		public Class(String codeString)
	{
		super(codeString);
		
		totalOfOperators = new IntegerMetric("Total of Operators", new Integer(0));
		maximumOfOperators = new IntegerMetric("Maximum of Operators", new Integer(0));
		
		totalOfMaximumOperators = new IntegerMetric("Total of Maximum of Operators", new Integer(0));
		maximumOfTotalOperators = new IntegerMetric("Maximum of Maximum of Operators", new Integer(0));
		
		totalOfLevels = new IntegerMetric("Total of Levels", new Integer(0));
		maximumOfLevels = new IntegerMetric("Maximum of Levels", new Integer(0));
		
		totalOfMaximumLevels = new IntegerMetric("Total of Maximum of Levels", new Integer(0));
		maximumOfTotalLevels = new IntegerMetric("Maximum of Maximum of Levels", new Integer(0));
		
		totalDataFlow = new IntegerMetric("Total of Data Flow", new Integer(0));
		maximumDataFlow = new IntegerMetric("Maximum of Data Flow", new Integer(0));
		
		totalOfMaximumDataFlow = new IntegerMetric("Total of Maximum of Data Flow", new Integer(0));
		maximumOfTotalDataFlow = new IntegerMetric("Maximum of Maximum of Data Flow", new Integer(0));
		
		totalDataUsage = new IntegerMetric("Total of Data Usage", new Integer(0));
		maximulDataUsage = new IntegerMetric("Maximum of Data Usage", new Integer(0));
		
		totalOfMaximumDataUsage = new IntegerMetric("Total of Maximum of Data Usage", new Integer(0));
		maximumOfTotalDataUsage = new IntegerMetric("Maximum of Maximum of Data Usage", new Integer(0));
		
		totalOfMethodCalls = new IntegerMetric("Total of Method Calls", new Integer(0));
		maximumOfMethodCalls = new IntegerMetric("Maximum of Method Calls", new Integer(0));
		
		inOutDegree = new IntegerMetric("In/Out Degree", new Integer(0));
		numberOfPublicMembers = new IntegerMetric("Number of Public Members", new Integer(0));
	}

	public Collection<Method> getMethods()
	{
		return methods;
	}

	public Metric getTotalOfOperators() {
		return totalOfOperators;
	}

	public Metric getMaximumOfOperators() {
		return maximumOfOperators;
	}

	public Metric getTotalOfMaximumOperators() {
		return totalOfMaximumOperators;
	}

	public Metric getMaximumOfTotalOperators() {
		return maximumOfTotalOperators;
	}

	public Metric getTotalOfLevels() {
		return totalOfLevels;
	}

	public Metric getMaximumOfLevels() {
		return maximumOfLevels;
	}

	public Metric getTotalOfMaximumLevels() {
		return totalOfMaximumLevels;
	}

	public Metric getMaximumOfTotalLevels() {
		return maximumOfTotalLevels;
	}

	public Metric getTotalDataFlow() {
		return totalDataFlow;
	}

	public Metric getMaximumDataFlow() {
		return maximumDataFlow;
	}

	public Metric getTotalOfMaximumDataFlow() {
		return totalOfMaximumDataFlow;
	}

	public Metric getMaximumOfTotalDataFlow() {
		return maximumOfTotalDataFlow;
	}

	public Metric getTotalDataUsage() {
		return totalDataUsage;
	}

	public Metric getMaximulDataUsage() {
		return maximulDataUsage;
	}

	public Metric getTotalOfMaximumDataUsage() {
		return totalOfMaximumDataUsage;
	}

	public Metric getMaximumOfTotalDataUsage() {
		return maximumOfTotalDataUsage;
	}

	public Metric getTotalOfMethodCalls() {
		return totalOfMethodCalls;
	}

	public Metric getMaximumOfMethodCalls() {
		return maximumOfMethodCalls;
	}

	public Metric getInOutDegree() {
		return inOutDegree;
	}

	public Metric getNumberOfPublicMembers() {
		return numberOfPublicMembers;
	}

	public void setMethods(Collection<Method> methods) {
		this.methods = methods;
	}

	public void setTotalOfOperators(Metric totalOfOperators) {
		this.totalOfOperators = totalOfOperators;
	}

	public void setMaximumOfOperators(Metric maximumOfOperators) {
		this.maximumOfOperators = maximumOfOperators;
	}

	public void setTotalOfMaximumOperators(Metric totalOfMaximumOperators) {
		this.totalOfMaximumOperators = totalOfMaximumOperators;
	}

	public void setMaximumOfTotalOperators(Metric maximumOfTotalOperators) {
		this.maximumOfTotalOperators = maximumOfTotalOperators;
	}

	public void setTotalOfLevels(Metric totalOfLevels) {
		this.totalOfLevels = totalOfLevels;
	}

	public void setMaximumOfLevels(Metric maximumOfLevels) {
		this.maximumOfLevels = maximumOfLevels;
	}

	public void setTotalOfMaximumLevels(Metric totalOfMaximumLevels) {
		this.totalOfMaximumLevels = totalOfMaximumLevels;
	}

	public void setMaximumOfTotalLevels(Metric maximumOfTotalLevels) {
		this.maximumOfTotalLevels = maximumOfTotalLevels;
	}

	public void setTotalDataFlow(Metric totalDataFlow) {
		this.totalDataFlow = totalDataFlow;
	}

	public void setMaximumDataFlow(Metric maximumDataFlow) {
		this.maximumDataFlow = maximumDataFlow;
	}

	public void setTotalOfMaximumDataFlow(Metric totalOfMaximumDataFlow) {
		this.totalOfMaximumDataFlow = totalOfMaximumDataFlow;
	}

	public void setMaximumOfTotalDataFlow(Metric maximumOfTotalDataFlow) {
		this.maximumOfTotalDataFlow = maximumOfTotalDataFlow;
	}

	public void setTotalDataUsage(Metric totalDataUsage) {
		this.totalDataUsage = totalDataUsage;
	}

	public void setMaximulDataUsage(Metric maximulDataUsage) {
		this.maximulDataUsage = maximulDataUsage;
	}

	public void setTotalOfMaximumDataUsage(Metric totalOfMaximumDataUsage) {
		this.totalOfMaximumDataUsage = totalOfMaximumDataUsage;
	}

	public void setMaximumOfTotalDataUsage(Metric maximumOfTotalDataUsage) {
		this.maximumOfTotalDataUsage = maximumOfTotalDataUsage;
	}

	public void setTotalOfMethodCalls(Metric totalOfMethodCalls) {
		this.totalOfMethodCalls = totalOfMethodCalls;
	}

	public void setMaximumOfMethodCalls(Metric maximumOfMethodCalls) {
		this.maximumOfMethodCalls = maximumOfMethodCalls;
	}

	public void setInOutDegree(Metric inOutDegree) {
		this.inOutDegree = inOutDegree;
	}

	public void setNumberOfPublicMembers(Metric numberOfPublicMembers) {
		this.numberOfPublicMembers = numberOfPublicMembers;
	}
}
