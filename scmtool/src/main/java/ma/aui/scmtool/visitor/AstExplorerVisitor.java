package ma.aui.scmtool.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;


import ma.aui.scmtool.model.IntegerMetric;

import org.eclipse.jdt.core.dom.*;

public class AstExplorerVisitor extends ASTVisitor
{
	private Stack<ma.aui.scmtool.model.Statement> stStack;
	private Stack<ma.aui.scmtool.model.Method> methodsStack;
	private Stack<ma.aui.scmtool.model.Class> classesStack;
	private Stack<ma.aui.scmtool.model.CompilationUnit> cuStack;
	
	private Vector<ma.aui.scmtool.model.Statement> statementsList;
	private Vector<ma.aui.scmtool.model.Method> methodsList;
	private Vector<ma.aui.scmtool.model.Class> classesList;
	private Vector<ma.aui.scmtool.model.CompilationUnit> cunitsList;
	
	/**
	 * Current level in source code
	 */ 
	private Integer nblevelsCount = 0;
	
	/**
	 * Max level in source code
	 */
	private Integer maxLevel = 0;
	
	/**
	 * Max number of operators 
	 */
	
	private Integer maxNumberOfOperators = 0;
	
	/**
	 * Count of public members in current class
	 */ 
	private Integer nbOfPublicMembers = 0;
	
	private Integer maxDataFlow = 0;
	
	private Integer maxDataUsage = 0;
	
	ma.aui.scmtool.model.Method topMethod;
	
	/**
	 * Constructor
	 */
	public AstExplorerVisitor() 
	{
		super();
		
		stStack      = new Stack<ma.aui.scmtool.model.Statement>();
		methodsStack = new Stack<ma.aui.scmtool.model.Method>();
		classesStack = new Stack<ma.aui.scmtool.model.Class>();
		cuStack      = new Stack<ma.aui.scmtool.model.CompilationUnit>();
		
		statementsList = new Vector<ma.aui.scmtool.model.Statement>();
		methodsList    = new Vector<ma.aui.scmtool.model.Method>();
		classesList    = new Vector<ma.aui.scmtool.model.Class>();
		cunitsList     = new Vector<ma.aui.scmtool.model.CompilationUnit>();
		
		nblevelsCount = 0;
		
		maxLevel = 0;
		maxNumberOfOperators = 0;
		maxDataFlow = 0;
		maxDataUsage = 0;
		
		nbOfPublicMembers = 0;
	}

	/**
	 * Getters and setters
	 */ 
	public Vector<ma.aui.scmtool.model.CompilationUnit> getCunitsList()
	{
		return cunitsList;
	}
	
	public void setCunitsList(Vector<ma.aui.scmtool.model.CompilationUnit> cunitsList)
	{
		this.cunitsList = cunitsList;
	}
	
	public Vector<ma.aui.scmtool.model.Method> getMethodsList()
	{
		return methodsList;
	}

	public void setMethodsList(Vector<ma.aui.scmtool.model.Method> methodsList) 
	{
		this.methodsList = methodsList;
	}
	
	public Vector<ma.aui.scmtool.model.Class> getClassesList()
	{
		return classesList;
	}
	
	public void setClassesList(Vector<ma.aui.scmtool.model.Class> classesList)
	{
		this.classesList = classesList;
	}

	public Vector<ma.aui.scmtool.model.Statement> getStatementsList()
	{
		return statementsList;
	}

	public void setStatementsList(Vector<ma.aui.scmtool.model.Statement> statementsList)
	{
		this.statementsList = statementsList;
	}
	
	public Stack<ma.aui.scmtool.model.Statement> getStStack()
	{
		return stStack;
	}

	public Stack<ma.aui.scmtool.model.Method> getMethodsStack()
	{
		return methodsStack;
	}

	public Stack<ma.aui.scmtool.model.Class> getClassesStack()
	{
		return classesStack;
	}

	public Stack<ma.aui.scmtool.model.CompilationUnit> getCuStack()
	{
		return cuStack;
	}

	public void setStStack(Stack<ma.aui.scmtool.model.Statement> stStack)
	{
		this.stStack = stStack;
	}

	public void setMethodsStack(Stack<ma.aui.scmtool.model.Method> methodsStack)
	{
		this.methodsStack = methodsStack;
	}

	public void setClassesStack(Stack<ma.aui.scmtool.model.Class> classesStack)
	{
		this.classesStack = classesStack;
	}

	public void setCuStack(Stack<ma.aui.scmtool.model.CompilationUnit> cuStack)
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
		
		/**
		 * Push statement for processing
		 */
		if(node instanceof Expression || node.getNodeType() == ASTNode.VARIABLE_DECLARATION_STATEMENT )
		{
			stStack.push(new ma.aui.scmtool.model.Statement(node.toString()));
		}

		switch (node.getNodeType())
		{
			case ASTNode.TYPE_DECLARATION : 
				classesStack.push(new ma.aui.scmtool.model.Class(node.toString())); 
				break;
				
			case ASTNode.METHOD_DECLARATION : 
				methodsStack.push(new ma.aui.scmtool.model.Method(node.toString()));
				maxLevel = 0; // Reset max level (new method)
				maxNumberOfOperators = 0; // Reset max of operators
				maxDataFlow = 0; // Reset max of data flow
				maxDataUsage = 0; // Reset max of data usage
				break;
				
			case ASTNode.COMPILATION_UNIT : 
				cuStack.push(new ma.aui.scmtool.model.CompilationUnit()); 
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
					if(nblevelsCount > maxLevel)
					{
						maxLevel = nblevelsCount;
					}
					break;				
			}
		}
	}
	
	/**
	 * AST post-visitor
	 * Pop symbols off and add them to their corresponding vectors
	 */
	@Override
	public void postVisit(ASTNode node)
	{	
		super.postVisit(node);
		
		/**
		 * Update level
		 */
		if(node.getNodeType() == ASTNode.BLOCK)
		{
			switch(node.getParent().getNodeType())
			{
				case ASTNode.WHILE_STATEMENT : 
				case ASTNode.FOR_STATEMENT : 
				case ASTNode.ENHANCED_FOR_STATEMENT : 
				case ASTNode.IF_STATEMENT: 
					nblevelsCount --;
					break;	
					
				default: break;
			}
		}
		
		/**
		 * Post-process statements
		 */
		if(node instanceof Expression || node.getNodeType() == ASTNode.VARIABLE_DECLARATION_STATEMENT)
		{
			ma.aui.scmtool.model.Statement st = stStack.pop();
			
			Integer nbOps = calculateNumberOfOperators(node);
			if(nbOps > maxNumberOfOperators)
			{
				maxNumberOfOperators = nbOps;
			}
			
			st.setNumberOfOperators(new IntegerMetric("Number of Operators", nbOps));
			st.setNumberOfLevels(new IntegerMetric("Number of Levels", nblevelsCount));
			/* TODO: Update next two lines */
			st.setDataFlow(new IntegerMetric("Total of Data Flow", 0));
			st.setDataUsage(new IntegerMetric("Total of Data Usage", 0));
			
			statementsList.add(st);
			
			/* Set method metrics in which this stmt exists */
			if (! methodsStack.empty())
			{
				 topMethod = methodsStack.peek();
				
				/* Update total of operators */
				IntegerMetric totalOfOperators = (IntegerMetric) topMethod.getTotalOfOperators();
				IntegerMetric stTotalOfOperators = (IntegerMetric) st.getNumberOfOperators();
				totalOfOperators.addToValue(stTotalOfOperators.getValue());
				
				/* Update total of levels */
				IntegerMetric totalOfLevels = (IntegerMetric) topMethod.getTotalOfLevels();
				IntegerMetric stTotalOflevels = (IntegerMetric) st.getNumberOfLevels();
				totalOfLevels.addToValue(stTotalOflevels.getValue());
				
				/* Update data flow */
				IntegerMetric totalOfDataFlow = (IntegerMetric) topMethod.getTotalDataFlow();
				IntegerMetric stTotalOfDataFlow = (IntegerMetric) st.getDataFlow();
				totalOfDataFlow.addToValue(stTotalOfDataFlow.getValue());
				
				/* Update data usage */
				IntegerMetric totalOfDataUsage = (IntegerMetric) topMethod.getTotalDataUsage();
				IntegerMetric stTotalOfDataUsage = (IntegerMetric) st.getDataUsage();
				totalOfDataUsage.addToValue(stTotalOfDataUsage.getValue());
				
				/* Add stmt to list of stmts in method */
				topMethod.getStatements().add(st);
			}
		}
		
		/**
		 * Post-process methods
		 */
		 if(node instanceof MethodDeclaration)
		 {
			 ma.aui.scmtool.model.Method method = methodsStack.pop();
			 
			 IntegerMetric maxOfOperators = (IntegerMetric) method.getMaximumOfOperators();
			 maxOfOperators.setValue(maxNumberOfOperators);
			 
			 IntegerMetric maxOfLevels = (IntegerMetric) method.getMaximumOfLevels();
			 maxOfLevels.setValue(maxLevel);
			 
			 IntegerMetric maxOfDataFlow = (IntegerMetric) method.getMaximumDataFlow();
			 maxOfDataFlow.setValue(maxDataFlow);
			 
			 IntegerMetric maxOfDataUsage = (IntegerMetric) method.getMaximumDataUsage();
			 maxOfDataUsage.setValue(maxDataUsage);
			 
			 /* TODO: remove next two lines */
			 IntegerMetric totalOfOperators = (IntegerMetric) method.getTotalOfOperators();
			 IntegerMetric totalOfLevels = (IntegerMetric) method.getTotalOfLevels();
			 
			 /* TODO: remove printing code from here */
			 System.out.println("Method statements count: " + topMethod.getStatements().size());
			 System.out.println("\t" +"Total of Operators : " + totalOfOperators.getValue());
			 System.out.println("\t" +"Total of Levels : " + totalOfLevels.getValue());
			 System.out.println("\tMax of Levels : "+maxLevel);
			 System.out.println("\tMax of Operators : "+maxNumberOfOperators);
			 System.out.println("\tMax of data flow : "+maxDataFlow);
			 System.out.println("\tMax of data usage : "+maxDataUsage);
			 
			 /* Set class metrics in which this method exists */
			 ma.aui.scmtool.model.Class topClass = classesStack.peek();
			 
			 /* Update total of operators */
			 IntegerMetric classTotalOfOperators = (IntegerMetric) topClass.getTotalOfOperators();
			 IntegerMetric methodClassTotalOfOperators = (IntegerMetric) method.getTotalOfOperators();
			 classTotalOfOperators.addToValue(methodClassTotalOfOperators.getValue());
				
			 /* TODO: Update total of max operators */
			 IntegerMetric classTotalOfMaxOfOperators = (IntegerMetric) topClass.getTotalOfMaximumOperators();
			 IntegerMetric methodClassMaxOfOperators = (IntegerMetric) method.getMaximumOfOperators();
			 classTotalOfMaxOfOperators.addToValue(methodClassMaxOfOperators.getValue());
			 
			 /* TODO: Update total of levels */
			 /* TODO: Update total of max levels */
			 /* TODO: Update total of data flow */
			 /* TODO: Update total of max data flow */
			 /* TODO: Update total of data usage */
			 /* TODO: Update total of max data usage */
		 }
		
		/**
		 * Post-process classes
		 */
		if (node instanceof TypeDeclaration)
		{		
	           ma.aui.scmtool.model.Class clazz = classesStack.pop();
	           
	           clazz.setNumberOfPublicMembers(new IntegerMetric ("Number of Public Members", nbOfPublicMembers));
	           classesList.add(clazz);
	           
	           /* TODO: Update max of operators */
	           /* TODO: Update max of total operators */
	           /* TODO: Update max of levels */
	           /* TODO: Update max of total levels */
	           /* TODO: Update max of data flow */
	           /* TODO: Update max of total data flow */
	           /* TODO: Update max of data usage */
	           /* TODO: Update max of total data usage */
	           
	           /* TODO: remove printing code */
	           IntegerMetric clazzTotalOfOperators = (IntegerMetric) clazz.getTotalOfOperators();
	           IntegerMetric clazzTotalOfMaximumOfOperators = (IntegerMetric) clazz.getTotalOfMaximumOperators();
	           
	           System.out.println("Class " + clazz.toString());
	           System.out.println("\t" +"Total of Operators : " + clazzTotalOfOperators.getValue());
	           System.out.println("\t" +"Total of Maximum of Operators : " + clazzTotalOfMaximumOfOperators.getValue());
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
	    			nbOfPublicMembers += classField.fragments().size();
	    			System.out.println("public field(s) detected ");
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
