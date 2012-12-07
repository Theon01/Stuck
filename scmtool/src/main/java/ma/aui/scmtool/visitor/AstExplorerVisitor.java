package ma.aui.scmtool.visitor;
import java.util.Stack;
import ma.aui.scmtool.model.CompilationUnit;
import ma.aui.scmtool.model.Method;
import ma.aui.scmtool.model.Statement;
import ma.aui.scmtool.model.Class;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class AstExplorerVisitor extends ASTVisitor {

	//CuMetadata cuMetadata;

	private Stack stStack, methodsStack, classesStack, cuStack;

	public AstExplorerVisitor() {
		super();
		//	cuMetadata = new CuMetadata(new Vector<String>());
		stStack = new Stack<Statement>();
		methodsStack = new Stack<Method>();
		classesStack = new Stack<Class>();
		cuStack = new Stack<CompilationUnit>();
	}

	@Override
	public boolean visit(TypeDeclaration node) {

		if (! node.isInterface()){

			//			cuMetadata.getTypesNames().add(node.getName().toString());
			//			System.out.println(cuMetadata.getTypesNames().toString());
		}





		return true;
	}

	@Override
	public void postVisit(ASTNode node) {
		super.postVisit(node);


	}

	@Override
	public void preVisit(ASTNode node) {

		super.preVisit(node);
		if (node instanceof org.eclipse.jdt.core.dom.Statement){
			stStack.push(node);	
		}

		switch (node.getNodeType()){

		case ASTNode.TYPE_DECLARATION : classesStack.push(node); break;

		case ASTNode.METHOD_DECLARATION : methodsStack.push(node); break;

		case ASTNode.COMPILATION_UNIT : cuStack.push(node); break;

		default: break;
		}
	}

	public Stack getStStack() {
		return stStack;
	}

	public Stack getMethodsStack() {
		return methodsStack;
	}

	public Stack getClassesStack() {
		return classesStack;
	}

	public Stack getCuStack() {
		return cuStack;
	}

	public void setStStack(Stack stStack) {
		this.stStack = stStack;
	}

	public void setMethodsStack(Stack methodsStack) {
		this.methodsStack = methodsStack;
	}

	public void setClassesStack(Stack classesStack) {
		this.classesStack = classesStack;
	}

	public void setCuStack(Stack cuStack) {
		this.cuStack = cuStack;
	}



}
