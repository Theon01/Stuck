package ma.aui.scmtool.model;

public class IntegerMetric extends Metric {
	
	
	Integer value;
	
	public IntegerMetric(String name, Integer value) {
		super(name);
		this.value = value;
	}


	public IntegerMetric(String name) 
	{
		super(name);
	}


	public Integer getValue() {
		return value;
	}


	public void setValue(Integer value) {
		this.value = value;
	}
	
	

}
