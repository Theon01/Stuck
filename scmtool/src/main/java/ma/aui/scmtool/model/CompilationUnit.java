package ma.aui.scmtool.model;

import java.util.Collection;

public class CompilationUnit extends Scope
{

	Collection<Class> classes;
	
	public CompilationUnit(String codeString, Collection<Class> classes)
	{
		super(codeString);
		this.classes = classes;
	}

	/**
	 * Default constructor
	 */
	public CompilationUnit() 
	{
	}

}
