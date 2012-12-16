package ma.aui.scmtool.model;

import java.util.Collection;

public class Class extends Scope 
{
	Collection<Method> methods;
	
	private Metric totalOfOperators;
	private Metric maximumOfOperators;
	
	private Metric totalOfMaximumOperators;
	private Metric maximumOfMaximumOperators;
	
	private Metric totalOfLevels;
	private Metric maximumOfLevels;
	
	private Metric totalOfMaximumLevels;
	private Metric maximumOfMaximumLevels;
	
	private Metric totalDataFlow;
	private Metric maximumDataFlow;
	
	private Metric totalOfMaximumDataFlow;
	private Metric maximumOfMaximumDataFlow;
	
	private Metric totalDataUsage;
	private Metric maximulDataUsage;
	
	private Metric totalOfMaximumDataUsage;
	private Metric maximumOfMaximumDataUsage;
	
	private Metric totalOfMethodCalls;
	private Metric maximumOfMethodCalls;
	
	private Metric inOutDegree;
	private Metric numberOfPublicMembers;
	
	public Metric getTotalOfOperators() 
	{
		return totalOfOperators;
	}

	public Metric getMaximumOfOperators() 
	{
		return maximumOfOperators;
	}

	public Metric getTotalOfMaximumOperators() 
	{
		return totalOfMaximumOperators;
	}

	public Metric getMaximumOfMaximumOperators()
	{
		return maximumOfMaximumOperators;
	}

	public Metric getTotalOfLevels()
	{
		return totalOfLevels;
	}

	public Metric getMaximumOfLevels()
	{
		return maximumOfLevels;
	}

	public Metric getTotalOfMaximumLevels()
	{
		return totalOfMaximumLevels;
	}

	public Metric getMaximumOfMaximumLevels() 
	{
		return maximumOfMaximumLevels;
	}

	public Metric getTotalDataFlow()
	{
		return totalDataFlow;
	}

	public Metric getMaximumDataFlow()
	{
		return maximumDataFlow;
	}

	public Metric getTotalOfMaximumDataFlow()
	{
		return totalOfMaximumDataFlow;
	}

	public Metric getMaximumOfMaximumDataFlow()
	{
		return maximumOfMaximumDataFlow;
	}

	public Metric getTotalDataUsage()
	{
		return totalDataUsage;
	}

	public Metric getMaximulDataUsage()
	{
		return maximulDataUsage;
	}

	public Metric getTotalOfMaximumDataUsage() 
	{
		return totalOfMaximumDataUsage;
	}

	public Metric getMaximumOfMaximumDataUsage()
	{
		return maximumOfMaximumDataUsage;
	}

	public Metric getTotalOfMethodCalls() 
	{
		return totalOfMethodCalls;
	}

	public Metric getMaximumOfMethodCalls()
	{
		return maximumOfMethodCalls;
	}

	public Metric getInOutDegree()
	{
		return inOutDegree;
	}

	public Metric getNumberOfPublicMembers()
	{
		return numberOfPublicMembers;
	}

	public void setTotalOfOperators(Metric totalOfOperators)
	{
		this.totalOfOperators = totalOfOperators;
	}

	public void setMaximumOfOperators(Metric maximumOfOperators)
	{
		this.maximumOfOperators = maximumOfOperators;
	}

	public void setTotalOfMaximumOperators(Metric totalOfMaximumOperators)
	{
		this.totalOfMaximumOperators = totalOfMaximumOperators;
	}

	public void setMaximumOfMaximumOperators(Metric maximumOfMaximumOperators)
	{
		this.maximumOfMaximumOperators = maximumOfMaximumOperators;
	}

	public void setTotalOfLevels(Metric totalOfLevels)
	{
		this.totalOfLevels = totalOfLevels;
	}

	public void setMaximumOfLevels(Metric maximumOfLevels)
	{
		this.maximumOfLevels = maximumOfLevels;
	}

	public void setTotalOfMaximumLevels(Metric totalOfMaximumLevels)
	{
		this.totalOfMaximumLevels = totalOfMaximumLevels;
	}

	public void setMaximumOfMaximumLevels(Metric maximumOfMaximumLevels)
	{
		this.maximumOfMaximumLevels = maximumOfMaximumLevels;
	}

	public void setTotalDataFlow(Metric totalDataFlow)
	{
		this.totalDataFlow = totalDataFlow;
	}

	public void setMaximumDataFlow(Metric maximumDataFlow)
	{
		this.maximumDataFlow = maximumDataFlow;
	}

	public void setTotalOfMaximumDataFlow(Metric totalOfMaximumDataFlow) 
	{
		this.totalOfMaximumDataFlow = totalOfMaximumDataFlow;
	}

	public void setMaximumOfMaximumDataFlow(Metric maximumOfMaximumDataFlow)
	{
		this.maximumOfMaximumDataFlow = maximumOfMaximumDataFlow;
	}

	public void setTotalDataUsage(Metric totalDataUsage)
	{
		this.totalDataUsage = totalDataUsage;
	}

	public void setMaximulDataUsage(Metric maximulDataUsage)
	{
		this.maximulDataUsage = maximulDataUsage;
	}

	public void setTotalOfMaximumDataUsage(Metric totalOfMaximumDataUsage)
	{
		this.totalOfMaximumDataUsage = totalOfMaximumDataUsage;
	}

	public void setMaximumOfMaximumDataUsage(Metric maximumOfMaximumDataUsage)
	{
		this.maximumOfMaximumDataUsage = maximumOfMaximumDataUsage;
	}

	public void setTotalOfMethodCalls(Metric totalOfMethodCalls)
	{
		this.totalOfMethodCalls = totalOfMethodCalls;
	}

	public void setMaximumOfMethodCalls(Metric maximumOfMethodCalls)
	{
		this.maximumOfMethodCalls = maximumOfMethodCalls;
	}

	public void setInOutDegree(Metric inOutDegree)
	{
		this.inOutDegree = inOutDegree;
	}

	public void setNumberOfPublicMembers(Metric numberOfPublicMembers)
	{
		this.numberOfPublicMembers = numberOfPublicMembers;
	}

	
	
	public Class(String codeString)
	{
		super(codeString);
		
	}

	public Collection<Method> getMethods()
	{
		return methods;
	}

	public void setMethods(Collection<Method> methods)
	{
		this.methods = methods;
	}
}
