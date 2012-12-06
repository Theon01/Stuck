package ma.aui.scmtool.visitor;
import java.util.Vector;

import ma.aui.scmtool.model.CuMetadata;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class AstExplorerVisitor extends ASTVisitor {
	
	CuMetadata cuMetadata;
	
	public AstExplorerVisitor() {
		super();
		cuMetadata = new CuMetadata(new Vector<String>());
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		
		if (! node.isInterface()){
			
			cuMetadata.getTypesNames().add(node.getName().toString());
			System.out.println(cuMetadata.getTypesNames().toString());
		}
		
		return true;
	}

	public CuMetadata getCuMetadata() {
		return cuMetadata;
	}

	public void setCuMetadata(CuMetadata cuMetadata) {
		this.cuMetadata = cuMetadata;
	}
	
	
	
	

}
