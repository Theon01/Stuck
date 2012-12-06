package ma.aui.scmtool.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class AstExplorerVisitor extends ASTVisitor {

	@Override
	public boolean visit(TypeDeclaration node) {
		
		if (! node.isInterface()){
			
			
			System.out.println(node.getName());
		}
		
		return true;
	}
	
	

}
