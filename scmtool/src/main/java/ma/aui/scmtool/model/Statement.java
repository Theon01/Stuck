package ma.aui.scmtool.model;

public class Statement extends Scope
{	
	private Metric numberOfOperators;
	
	private Metric numberOfLevels;
	
	private Metric dataFlow;
	
	private Metric dataUsage;

	public Statement(String codeString) {
		super(codeString);
	}

	public Metric getNumberOfOperators() {
		return numberOfOperators;
	}

	public Metric getNumberOfLevels() {
		return numberOfLevels;
	}

	public Metric getDataFlow() {
		return dataFlow;
	}

	public Metric getDataUsage() {
		return dataUsage;
	}

	public void setNumberOfOperators(Metric numberOfOperators) {
		this.numberOfOperators = numberOfOperators;
	}

	public void setNumberOfLevels(Metric numberOfLevels) {
		this.numberOfLevels = numberOfLevels;
	}

	public void setDataFlow(Metric dataFlow) {
		this.dataFlow = dataFlow;
	}

	public void setDataUsage(Metric dataUsage) {
		this.dataUsage = dataUsage;
	}

}
