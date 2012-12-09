package ma.aui.scmtool.model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import ma.aui.scmtool.visitor.AstExplorerVisitor;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;


public class Parser
{

	private AstExplorerVisitor astExplorerVisitor;
	
	
	public Parser(AstExplorerVisitor astExplorerVisitor) {
		super();
	this.astExplorerVisitor = astExplorerVisitor;
	}



	public AST parseFile(File sourceFile) throws IOException
	{
		// Parse the file 
		BufferedReader br = new BufferedReader(new FileReader(sourceFile));
		char[] chars = new char[(int)sourceFile.length()];
		br.read(chars);
		br.close();

		//System.out.println(chars);
		//Generate the AST
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(chars);
		CompilationUnit unit = (CompilationUnit) parser.createAST(null); 
		unit.recordModifications();
		AST ast = unit.getAST(); 
		unit.accept(this.astExplorerVisitor);
		/*System.out.println(astExplorerVisitor.getClassesStack().toString());
		System.out.println("*****************************************************");
		System.out.println(astExplorerVisitor.getMethodsStack().toString());*/
		//System.out.println("********************** Statements*******************************");
		//System.out.println(astExplorerVisitor.getStStack().toString());
		Vector<Statement> stmts = astExplorerVisitor.getStatementsList();
		Iterator<Statement> it = stmts.iterator();
		while(it.hasNext())
		{
			Statement st = it.next();
			System.out.println(st.getCodeString() + ":");
			System.out.println("\t" + st.getNumberOfOperators().getName() + ": " + ((IntegerMetric)st.getNumberOfOperators()).getValue());
		}
		
		return ast;
	}



	



	
	
	
}
