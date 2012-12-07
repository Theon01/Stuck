package ma.aui.scmtool.model;

import org.eclipse.jdt.core.dom.ASTNode;



public abstract class Scope
{
	protected String codeString;
	protected ASTNode node;

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

	public ASTNode getNode() {
		return node;
	}

	public void setNode(ASTNode node) {
		this.node = node;
	}
	
	
	
}
