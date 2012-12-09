package ma.aui.scmtool.visitor;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import ma.aui.scmtool.model.CompilationUnit;
import ma.aui.scmtool.model.IntegerMetric;
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
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jface.text.projection.Fragment;

public class AstExplorerVisitor extends ASTVisitor {

	//CuMetadata cuMetadata;
	
	Vector<Statement> statementsList;
	Vector<Method> methodsList;
	Vector<Class> classesList;
	Vector<CompilationUnit> cunitsList;

	public Vector<CompilationUnit> getCunitsList() {
		return cunitsList;
	}



	public void setCunitsList(Vector<CompilationUnit> cunitsList) {
		this.cunitsList = cunitsList;
	}



	public Vector<Method> getMethodsList() {
		return methodsList;
	}



	public Vector<Class> getClassesList() {
		return classesList;
	}



	public void setMethodsList(Vector<Method> methodsList) 
	{
		this.methodsList = methodsList;
	}



	public void setClassesList(Vector<Class> classesList) {
		this.classesList = classesList;
	}

	private Stack stStack, methodsStack, classesStack, cuStack;

	public AstExplorerVisitor() 
	{
		super();
		//	cuMetadata = new CuMetadata(new Vector<String>());
		stStack = new Stack<ASTNode>();
		methodsStack = new Stack<ASTNode>();
		classesStack = new Stack<ASTNode>();
		cuStack = new Stack<ASTNode>();
		
		statementsList =  new Vector<Statement>();
		methodsList = new Vector<Method>();
		classesList = new Vector<Class>();
		cunitsList = new Vector<CompilationUnit>();
	}



	public Vector<Statement> getStatementsList() {
		return statementsList;
	}



	public void setStatementsList(Vector<Statement> statementsList) {
		this.statementsList = statementsList;
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
	public boolean visit(VariableDeclarationFragment node)
	{
		return false;
	}

	@Override
	public void postVisit(ASTNode node) {
		
		
		super.postVisit(node);
		
		switch(node.getNodeType())
		{
			default: break;
		}

		/*switch(node.getNodeType())
		{
			case ASTNode.ARRAY_INITIALIZER :
			case ASTNode.ASSIGNMENT : 
			//case ASTNode.CONDITIONAL_EXPRESSION :
			case ASTNode.INFIX_EXPRESSION :
			case ASTNode.INITIALIZER : 
			case ASTNode.POSTFIX_EXPRESSION:
			case ASTNode.PREFIX_EXPRESSION:
			case ASTNode.VARIABLE_DECLARATION_STATEMENT:
				Statement st = new Statement(node.toString());
				st.setNumberOfOperators(new IntegerMetric("numberOfOperators", calculateNumberOfOperators(node)));
				statementsList.add(st);
		}*/
		if(node instanceof Expression)
		{
			Statement st = new Statement(node.toString());
			st.setNumberOfOperators(new IntegerMetric("numberOfOperators", calculateNumberOfOperators(node)));
			statementsList.add(st);
			stStack.pop();
			
			/* Set method metrics in which this stmt exists */
			
		}
	}

	@Override
	public void preVisit(ASTNode node) {

		super.preVisit(node);
		/*if (node instanceof org.eclipse.jdt.core.dom.Statement){
			stStack.push(node);	
		}*/

		if(node instanceof org.eclipse.jdt.core.dom.Expression)
		{
			stStack.push(node);
		}

		switch (node.getNodeType()){

		case ASTNode.TYPE_DECLARATION : classesStack.push(node); break;

		case ASTNode.METHOD_DECLARATION : methodsStack.push(node); break;

		case ASTNode.COMPILATION_UNIT : cuStack.push(node); break;

		/*case ASTNode.ARRAY_INITIALIZER :
		case ASTNode.ASSIGNMENT : 
		case ASTNode.CONDITIONAL_EXPRESSION :
		case ASTNode.INFIX_EXPRESSION :
		case ASTNode.INITIALIZER : 
		case ASTNode.POSTFIX_EXPRESSION:
		case ASTNode.PREFIX_EXPRESSION:
		case ASTNode.VARIABLE_DECLARATION_STATEMENT:	
			stStack.push(node); break;*/
		

		default: break;
		}
	}


	Integer calculateNumberOfOperators(ASTNode stmt)
	{
		Integer opcount = 0;
		switch(stmt.getNodeType())
		{
			default: break;
			
			case ASTNode.ARRAY_ACCESS:
				ArrayAccess arrAccess = (ArrayAccess) stmt;
				opcount = calculateNumberOfOperators(arrAccess.getArray()) +
						calculateNumberOfOperators(arrAccess.getIndex());
				break;
				
			case ASTNode.ARRAY_INITIALIZER:
				ArrayInitializer arrInit = (ArrayInitializer) stmt;
				List<Expression> exprs = arrInit.expressions();
				for(Expression expr: exprs)
				{
					opcount += calculateNumberOfOperators(expr);
				}
				break;
			
			case ASTNode.INFIX_EXPRESSION:
				InfixExpression inExpr = (InfixExpression) stmt;
				opcount = 1 + calculateNumberOfOperators(inExpr.getLeftOperand())
						+ calculateNumberOfOperators(inExpr.getRightOperand());
				break;
			case ASTNode.POSTFIX_EXPRESSION:
				PostfixExpression postExpr = (PostfixExpression) stmt;
				opcount = 1 + calculateNumberOfOperators(postExpr.getOperand());
				break;
			case ASTNode.PREFIX_EXPRESSION:
				PrefixExpression preExpr = (PrefixExpression) stmt;
				opcount = 1 + calculateNumberOfOperators(preExpr.getOperand());
				break;
				
			case ASTNode.ASSIGNMENT :
				Assignment rhs = (Assignment) stmt;
				opcount = 1 + calculateNumberOfOperators(rhs.getLeftHandSide()) +
						calculateNumberOfOperators(rhs.getRightHandSide());
				break;
				
			/*case ASTNode.INITIALIZER:
				Initializer init = (Initializer) stmt;
				List<org.eclipse.jdt.core.dom.Statement> sts = init.getBody().statements();
				for(org.eclipse.jdt.core.dom.Statement s: sts)
				{
					opcount += calculateNumberOfOperators(s);
				}
				break;*/
				
			case ASTNode.VARIABLE_DECLARATION_STATEMENT:
				VariableDeclarationStatement vds = (VariableDeclarationStatement) stmt;
				List<VariableDeclarationFragment> frags = vds.fragments();
				for(VariableDeclarationFragment frag: frags)
				{
					opcount += calculateNumberOfOperators(frag);
				}
				break;
				
			case ASTNode.VARIABLE_DECLARATION_FRAGMENT:
				VariableDeclarationFragment vdf = (VariableDeclarationFragment) stmt;
				Expression exp = vdf.getInitializer();
				if (exp != null) opcount = 1 + calculateNumberOfOperators(exp);
				
				break;
		}
		return opcount;
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
