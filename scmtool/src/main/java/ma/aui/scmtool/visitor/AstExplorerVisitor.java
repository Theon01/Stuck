package ma.aui.scmtool.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import ma.aui.scmtool.model.CompilationUnit;
import ma.aui.scmtool.model.IntegerMetric;
import ma.aui.scmtool.model.Method;
import ma.aui.scmtool.model.Statement;
import ma.aui.scmtool.model.Class;

import org.eclipse.jdt.core.dom.*;

public class AstExplorerVisitor extends ASTVisitor
{
	private Stack<Statement> stStack;
	private Stack<Method> methodsStack;
	private Stack<Class> classesStack;
	private Stack<CompilationUnit> cuStack;
	
	private Vector<Statement> statementsList;
	private Vector<Method> methodsList;
	private Vector<Class> classesList;
	private Vector<CompilationUnit> cunitsList;
	
	/**
	 * Current level in source code
	 */ 
	private Integer nblevelsCount = 0;
	
	/**
	 * Count of public members in current class
	 */ 
	private Integer nbOfPublicMembers = 0;
	
	/**
	 * Constructor
	 */
	public AstExplorerVisitor() 
	{
		super();
		
		stStack      = new Stack<Statement>();
		methodsStack = new Stack<Method>();
		classesStack = new Stack<Class>();
		cuStack      = new Stack<CompilationUnit>();
		
		statementsList =  new Vector<Statement>();
		methodsList    = new Vector<Method>();
		classesList    = new Vector<Class>();
		cunitsList     = new Vector<CompilationUnit>();
	}

	/**
	 * Getters and setters
	 */ 
	public Vector<CompilationUnit> getCunitsList()
	{
		return cunitsList;
	}
	
	public void setCunitsList(Vector<CompilationUnit> cunitsList)
	{
		this.cunitsList = cunitsList;
	}
	
	public Vector<Method> getMethodsList()
	{
		return methodsList;
	}

	public void setMethodsList(Vector<Method> methodsList) 
	{
		this.methodsList = methodsList;
	}
	
	public Vector<Class> getClassesList()
	{
		return classesList;
	}
	
	public void setClassesList(Vector<Class> classesList)
	{
		this.classesList = classesList;
	}

	public Vector<Statement> getStatementsList()
	{
		return statementsList;
	}

	public void setStatementsList(Vector<Statement> statementsList)
	{
		this.statementsList = statementsList;
	}
	
	public Stack<Statement> getStStack()
	{
		return stStack;
	}

	public Stack<Method> getMethodsStack()
	{
		return methodsStack;
	}

	public Stack<Class> getClassesStack()
	{
		return classesStack;
	}

	public Stack<CompilationUnit> getCuStack()
	{
		return cuStack;
	}

	public void setStStack(Stack<Statement> stStack)
	{
		this.stStack = stStack;
	}

	public void setMethodsStack(Stack<Method> methodsStack)
	{
		this.methodsStack = methodsStack;
	}

	public void setClassesStack(Stack<Class> classesStack)
	{
		this.classesStack = classesStack;
	}

	public void setCuStack(Stack<CompilationUnit> cuStack)
	{
		this.cuStack = cuStack;
	}

	/**
	 * AST pre-visitor
	 * Push symbols into their corresponding stack to prepare them for processing
	 */
	@Override
	public void preVisit(ASTNode node)
	{
		super.preVisit(node);
		
		/* TODO: create stmt instance and push it into stack instead of node */

		/**
		 * Push statement for processing
		 */
		if(node instanceof Expression)
		{
			stStack.push(node);
		}

		switch (node.getNodeType())
		{
			case ASTNode.TYPE_DECLARATION : 
				classesStack.push(node); 
				break;
				
			case ASTNode.METHOD_DECLARATION : 
				methodsStack.push(node); 
				break;
				
			case ASTNode.COMPILATION_UNIT : 
				cuStack.push(node); 
				break;
		
			default: break;
		}
		
		/**
		 * Update level
		 */
		if(node.getNodeType() == ASTNode.BLOCK)
		{
			switch(node.getParent().getNodeType())
			{
				default: break;
	
				case ASTNode.WHILE_STATEMENT : 
				case ASTNode.FOR_STATEMENT : 
				case ASTNode.ENHANCED_FOR_STATEMENT : 
				case ASTNode.IF_STATEMENT: 
					nblevelsCount ++;
					break;				
			}
		}
	}

	/**
	 * AST visiting routines
	 * Cut-off by returning false to avoid going deeper down the AST
	 */ 
	@Override
	public boolean visit(Assignment node)
	{
		return false;
	}

	@Override
	public boolean visit(ArrayAccess node)
	{
		return false;
	}

	@Override
	public boolean visit(TypeDeclaration node)
	{
		FieldDeclaration[] classFields =  new FieldDeclaration[node.getFields().length];
		System.out.println(node.getFields().length);
	    	classFields= node.getFields();
	    
	    	for (FieldDeclaration classField :classFields)
	    	{
	    		int modifiers = classField.getModifiers();
	    		System.out.println(classField.toString());
	   
	    		if (Modifier.isPublic(modifiers))
	    		{
	    			nbOfPublicMembers++;
	    			System.out.println("public field detected ");
	    		}
	    	}
		return super.visit(node);
	}

	@Override
	public boolean visit(ArrayCreation node)
	{	
		return false;
	}

	@Override
	public boolean visit(ArrayInitializer node)
	{
		return false;
	}

	@Override
	public boolean visit(ClassInstanceCreation node)
	{
		
		return false;
	}

	@Override
	public boolean visit(ConditionalExpression node)
	{	
		return false;
	}

	@Override
	public boolean visit(FieldAccess node)
	{
		return false;
	}

	@Override
	public boolean visit(InfixExpression node) 
	{	
		return false;
	}

	@Override
	public boolean visit(ParenthesizedExpression node)
	{	
		return false;
	}

	@Override
	public boolean visit(PostfixExpression node)
	{	
		return false;
	}

	@Override
	public boolean visit(PrefixExpression node)
	{	
		return false;
	}

	@Override
	public boolean visit(VariableDeclarationExpression node)
	{	
		return false;
	}
	
	@Override
	public boolean visit(VariableDeclarationFragment node)
	{
		return false;
	}
	
	/**
	 * AST post-visitor
	 * Pop symbols off and add them to their corresponding vectors
	 */
	@Override
	public void postVisit(ASTNode node)
	{	
		super.postVisit(node);
		
		if(node.getNodeType() == ASTNode.BLOCK)
		{
			switch(node.getParent().getNodeType())
			{
				case ASTNode.WHILE_STATEMENT : 
				case ASTNode.FOR_STATEMENT : 
				case ASTNode.ENHANCED_FOR_STATEMENT : 
				case ASTNode.IF_STATEMENT: 
					nblevelsCount--;
					break;	
					
				default: break;
			}
		}
		
		if(node instanceof Expression)
		{
			Statement st = new Statement(node.toString());
			st.setNumberOfOperators(new IntegerMetric("numberOfOperators", calculateNumberOfOperators(node)));
			st.setNumberOfLevels(new IntegerMetric("numberOfLevels", nblevelsCount));
			statementsList.add(st);
			stStack.pop();
			/* Set method metrics in which this stmt exists */
		}
		
		if (node instanceof TypeDeclaration)
		{		
	           Class clazz = new Class(node.toString());
	           
	           clazz.setNumberOfPublicMembers(new IntegerMetric ("numberOfPublicMembers",nbOfPublicMembers));
	           classesList.add(clazz);
	           classesStack.pop();		
		}
		
	}

	/**
	 * Helper method for calculating the number of operators in a the given statement node
	 * Works by recursively processing nodes in the subtree for this statement
	 */
	private Integer calculateNumberOfOperators(ASTNode stmt)
	{
		Integer opcount = 0;
		
		switch(stmt.getNodeType())
		{
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
				
			default: break;
		}
		
		return opcount;
	}
}
