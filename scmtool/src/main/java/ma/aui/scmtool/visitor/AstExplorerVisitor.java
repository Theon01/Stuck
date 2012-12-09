package ma.aui.scmtool.visitor;
import java.util.Stack;
import ma.aui.scmtool.model.CompilationUnit;
import ma.aui.scmtool.model.Method;
import ma.aui.scmtool.model.Statement;
import ma.aui.scmtool.model.Class;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;

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
	public boolean visit(Assignment node) {

		return false;
	}
	
	

	@Override
	public boolean visit(ArrayAccess node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(ArrayCreation node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(ArrayInitializer node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(ClassInstanceCreation node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(ConditionalExpression node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(FieldAccess node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(InfixExpression node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(ParenthesizedExpression node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(PostfixExpression node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(PrefixExpression node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(VariableDeclarationExpression node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void postVisit(ASTNode node) {
		super.postVisit(node);
		
		


	}

	@Override
	public void preVisit(ASTNode node) {

		super.preVisit(node);
		/*if (node instanceof org.eclipse.jdt.core.dom.Statement){
			stStack.push(node);	
		}*/
		
		/*if(node instanceof org.eclipse.jdt.core.dom.Expression)
		{
			stStack.push(node);
		}*/


		switch (node.getNodeType()){

		case ASTNode.TYPE_DECLARATION : classesStack.push(node); break;

		case ASTNode.METHOD_DECLARATION : methodsStack.push(node); break;

		case ASTNode.COMPILATION_UNIT : cuStack.push(node); break;

		case ASTNode.ARRAY_INITIALIZER :
		case ASTNode.ASSIGNMENT : 
		case ASTNode.CONDITIONAL_EXPRESSION :
		//case ASTNode.EXPRESSION_STATEMENT :
		case ASTNode.INFIX_EXPRESSION :
		case ASTNode.INITIALIZER : 
		case ASTNode.POSTFIX_EXPRESSION:
		case ASTNode.PREFIX_EXPRESSION:
		case ASTNode.VARIABLE_DECLARATION_STATEMENT:	
			stStack.push(node); break;
			
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
