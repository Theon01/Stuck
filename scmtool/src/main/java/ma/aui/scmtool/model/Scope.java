package ma.aui.scmtool.model;

public abstract class Scope
{
	protected String codeString;

	public Scope(String codeString)
	{
		super();
		this.codeString = codeString;
	}

	public String getCodeString()
	{
		return codeString;
	}

	public void setCodeString(String codeString)
	{
		this.codeString = codeString;
	}
	
}
