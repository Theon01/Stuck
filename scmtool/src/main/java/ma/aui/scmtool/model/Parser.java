package ma.aui.scmtool.model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
		unit.accept(this.getAstExplorerVisitor());
		return ast;
	}



	public AstExplorerVisitor getAstExplorerVisitor() {
		return astExplorerVisitor;
	}



	public void setAstExplorerVisitor(AstExplorerVisitor astExplorerVisitor) {
		this.astExplorerVisitor = astExplorerVisitor;
	}
	
	
}
