package ma.aui.scmtool.model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import ma.aui.scmtool.visitor.AstExplorerVisitor;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jface.text.Document;


public class Parser
{

	private AstExplorerVisitor astExplorerVisitor;
	//private IProject project;
	private Integer loc;
	
	
	public Parser(AstExplorerVisitor astExplorerVisitor) {
		super();
	this.astExplorerVisitor = astExplorerVisitor;
	}



	public AST parseFile(File sourceFile) throws IOException
	{
		
		//JavaCore.create(project);
		// Parse the file 
		BufferedReader br = new BufferedReader(new FileReader(sourceFile));
		char[] chars = new char[(int)sourceFile.length()];
		br.read(chars);
		br.close();

		//System.out.println(chars);
		//Generate the AST
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		System.out.println("Location of the file: "+ sourceFile.getParent());
	    parser.setEnvironment(new String[]{}, new String[]{sourceFile.getParent()}, null, false);
		parser.setUnitName(sourceFile.getName());
	    parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		parser.setSource(chars);
		CompilationUnit unit = (CompilationUnit) parser.createAST(null); 
		unit.recordModifications();
		AST ast = unit.getAST();
		unit.accept(this.astExplorerVisitor);
		Document doc = new Document(unit.toString());
		loc = doc.getNumberOfLines();
		
		/*System.out.println(astExplorerVisitor.getClassesStack().toString());
		System.out.println("*****************************************************");
		System.out.println(astExplorerVisitor.getMethodsStack().toString());*/
		//System.out.println("********************** Statements*******************************");
		//System.out.println(astExplorerVisitor.getStStack().toString());
		Vector<Statement> stmts = astExplorerVisitor.getStatementsList(); 
		System.out.println("Statements :"+stmts.size());
		Iterator<Statement> it = stmts.iterator();
		while(it.hasNext())
		{
			Statement st = it.next();
			System.out.println(st.getCodeString() + ":");
			System.out.println("\t" + st.getNumberOfOperators().getName() + ": " + ((IntegerMetric)st.getNumberOfOperators()).getValue());
			System.out.println("\t" + st.getNumberOfLevels().getName() + ": " + ((IntegerMetric)st.getNumberOfLevels()).getValue());
		}
		
		Vector<Class> classes = astExplorerVisitor.getClassesList();
		System.out.println("Classes :"+classes.size());
		
		Iterator<Class> itc = classes.iterator();
		while(itc.hasNext()){
			
			Class clazz = itc.next();
			
			System.out.println(clazz.toString() + ":");
			System.out.println("\t" + clazz.getNumberOfPublicMembers().getName() + ": " + ((IntegerMetric)clazz.getNumberOfPublicMembers()).getValue());
			
		}
		
		return ast;
	}



	public AstExplorerVisitor getAstExplorerVisitor() {
		return astExplorerVisitor;
	}

	public Integer getLoc() {
		return loc;
	}

	public void setAstExplorerVisitor(AstExplorerVisitor astExplorerVisitor) {
		this.astExplorerVisitor = astExplorerVisitor;
	}



	public void setLoc(Integer loc) {
		this.loc = loc;
	}



	



	
	
	
}
