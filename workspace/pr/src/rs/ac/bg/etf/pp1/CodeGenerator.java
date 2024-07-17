package rs.ac.bg.etf.pp1;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import rs.ac.bg.etf.pp1.CounterVisitor.*;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;
	
	private boolean inForeach = false;
	private boolean inWhile = false;
	
	public int getMainPc() {
		return mainPc;
	}

	public void setMainPc(int mainPc) {
		this.mainPc = mainPc;
	}
	
	public CodeGenerator() {
		Obj chrMeth = Tab.find("chr");
		Obj ordMeth = Tab.find("ord");
		
		ordMeth.setAdr(Code.pc);
		chrMeth.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);
		
		Obj lenMeth = Tab.find("len");
	
		lenMeth.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
		Code.put(Code.arraylength);
		Code.put(Code.exit);
		Code.put(Code.return_);
		
		
	}
	
	@Override
	public void visit(PrintSt printSt) {
		
		
		if((printSt.getExpr().struct == Tab.intType) || (printSt.getExpr().struct == Tab1.boolType)) {
			Code.loadConst(5);
			Code.put(Code.print);
		}
		else {
			Code.loadConst(5);
			Code.put(Code.bprint);
		}
	}
	
	@Override
	public void visit(PrintStatementWidth printSt) {
	
		Code.loadConst(printSt.getNumVal());
		
		if((printSt.getExpr().struct == Tab.intType) || (printSt.getExpr().struct == Tab1.boolType)) {
			Code.put(Code.print);
		}  else {
			Code.put(Code.bprint);
		}		
	}
	

	
	@Override
	public void visit(FactorNumConst factor) {
		Obj con = Tab.insert(Obj.Con, "$", Tab.intType);
		con.setLevel(0);
		con.setAdr(factor.getNumConst());
				
		Code.load(con);
	}
	
	@Override
	public void visit(FactorCharConst factor) {
		
		Obj con = Tab.insert(Obj.Con, "$", Tab.charType);
		con.setLevel(0);
		con.setAdr(factor.getCharConst());
				
		Code.load(con);
	}
	
	@Override
	public void visit(FactorBoolConst factor) {
		Obj con = Tab.insert(Obj.Con, "$", Tab1.boolType);
		con.setLevel(0);
				
		if(factor.getBoolConst().booleanValue() ) {
			con.setAdr(1);
		} else {
			con.setAdr(0);
		}

		Code.load(con);
	}
	
	@Override
	public void visit(MethodName methodName) {
		
		if("main".equalsIgnoreCase(methodName.getMethodName())){
			mainPc = Code.pc;
		}
		
		methodName.obj.setAdr(Code.pc);
		
		SyntaxNode methodNode = methodName.getParent();
		
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormalParamCounter fpCnt = new FormalParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount()); 
		
	}
	
	
	@Override
	public void visit(MethodDecl methodDecl) {
		
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(DesignatorAssign designatorAssign) {
		Code.store(designatorAssign.getDesignator().obj);
	}
	
	@Override 
	public void visit(SimpleFactor factor) {
		SyntaxNode parent = factor.getParent();
		
		if(DesignatorAssign.class != parent.getClass() && FactorFunc.class != parent.getClass() && !(inForeach)) {
			Code.load(factor.getDesignator().obj);
		}
	}
	
	@Override
	public void visit(FactorNewArray factor) {
		Code.put(Code.newarray);
		
		if(factor.struct.getElemType() == Tab.charType) {
			Code.put(0);
		} else {
			Code.put(1);
		}
	}
	
	
	@Override
	public void visit(FactorFunc func) {
		Obj functionObj = func.getFunctionDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		
	}
	
	@Override
	public void visit(DesignatorFunc func) {
		Obj functionObj = func.getFunctionDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		
		if(functionObj.getType() != Tab.noType) {
			Code.put(Code.pop);
		}
	}
	
	@Override
	public void visit(ReturnSt returnSt) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(ReturnNothingSt returnSt) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(NegExpr expr) {
		if(!(expr.getParent() instanceof ForeachSt)) {
			Code.put(Code.neg);
		}
		
	}
	
	@Override
	public void visit(AddopExpr expr) {
		SyntaxNode op = expr.getAddop();
		
		if(op instanceof AddopPl)
			Code.put(Code.add);
		if(op instanceof AddopMin)
			Code.put(Code.sub);
	}
	
	@Override
	public void visit(MulOpFactors factors) {
		if(factors.getMulop() instanceof MulopMul) {
			Code.put(Code.mul); 
		} else if(factors.getMulop() instanceof MulopDiv) {
			Code.put(Code.div);  
 		} else {
			Code.put(Code.rem); 
		}
	}
	
	
	@Override
	public void visit(ReadSt statement) {
		
		if(statement.getDesignator().obj.getType() == Tab.charType) {
			Code.put(Code.bread);			
		} else {
			Code.put(Code.read);
		}		

		Code.store(statement.getDesignator().obj);
	}
	
	
	@Override
	public void visit(ArrayName designator) {
		Code.load(designator.getDesignator().obj);

	}
	

	@Override
	public void visit(DesignatorInc designator) {
		
		if(designator.getDesignator().obj.getKind() == Obj.Elem)
			Code.put(Code.dup2); 
		
		Code.load(designator.getDesignator().obj);
		Code.put(Code.const_1);
		Code.put(Code.add);
		Code.store(designator.getDesignator().obj);
	}
	
	@Override
	public void visit(DesignatorDec designator) {
		if(designator.getDesignator().obj.getKind() == Obj.Elem)
			Code.put(Code.dup2); 
		
		Code.load(designator.getDesignator().obj);
		Code.put(Code.const_1);
		Code.put(Code.sub);
		Code.store(designator.getDesignator().obj);
	}
	
	private int desigCnt = 0;
	private Stack<Obj> ldesig = new Stack<Obj>();
		
	@Override
	public void visit(DesigList designator) {
		desigCnt++;
		ldesig.push(designator.getDesignator().obj);
	}
	
	@Override
	public void visit(NoDesigList designator) {
		desigCnt++;
		ldesig.push(Tab.noObj);
	}
	

	private int arrayErr = 0;
	private int noErr = 0;
	
	
	
		
	@Override 
	public void visit(DesignatorBr designator) {
		Code.load(designator.getDesignator().obj);
		Code.put(Code.arraylength);
		Code.loadConst(desigCnt);
		Code.putFalseJump(Code.ge, 0);
		arrayErr = Code.pc - 2;
				
		Stack<Obj> desigs = new Stack<Obj>();
		while(!ldesig.isEmpty()) {
			desigs.push(ldesig.pop());
		}
		int index = 0;
		int indexVar = 0;
		boolean jedanProsao = false;
		
		int leftVals = desigCnt;
		
		while(desigCnt > 0) {
			Obj curr = desigs.pop();
			
			if(curr == Tab.noObj) {
				if(jedanProsao) {
					index++;
				}
				indexVar++;
				desigCnt--;
				continue;
			
					
			}
			else {
					jedanProsao = true;
					Code.load(designator.getDesignator().obj);
					if(curr.getKind() != Obj.Var) {
						Code.put(Code.const_n + leftVals);
						Code.put(Code.const_n + index + 1);
						Code.put(Code.sub);
					}
					else {

						Code.put(Code.const_n + indexVar);
					}
					Code.put(Code.aload);
					if(curr.getKind() != Obj.Var)
						Code.put(Code.astore);
					else {
						Code.store(curr);
					}
				desigCnt--;
				index++;
				indexVar++;
			}
			
		}
		
		Code.putJump(0);
		noErr = Code.pc - 2;
		
		Code.fixup(arrayErr);
		Code.put(Code.trap);
		Code.put(1);
		
		Code.fixup(noErr);
	}
	
	
	//petlje
	
	
	private Stack<Integer> jmpAnd = new Stack<Integer>();
	private Stack<Integer> jmpOr = new Stack<Integer>();
	private Stack<Integer> jmpAdr = new Stack<Integer>();

	@Override
	public void visit(ExprCondition cond) {
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0);
		jmpAnd.push(Code.pc - 2);
	}
	
	@Override
	public void visit(RelOpCondFact cond) {
		SyntaxNode type = cond.getRelop();
		
		if(type instanceof RelopEq) 
			Code.putFalseJump(Code.eq, 0);
		if(type instanceof RelopNotEq)
			Code.putFalseJump(Code.ne, 0);
		if(type instanceof RelopGt)
			Code.putFalseJump(Code.gt, 0);
		if(type instanceof RelopGteq)
			Code.putFalseJump(Code.ge, 0);
		if(type instanceof RelopLs)
			Code.putFalseJump(Code.lt, 0);
		if(type instanceof RelopLsEq)
			Code.putFalseJump(Code.le, 0);
		
		
		jmpAnd.push(Code.pc - 2);
	}
	
	@Override
	public void visit(CondTerm cond) {
		Code.putJump(0);
		jmpOr.push(Code.pc - 2);
		while(!jmpAnd.isEmpty()) {
			Code.fixup(jmpAnd.pop());
		}
	}
	
	@Override
	public void visit(Condition cond){
		Code.putJump(0);
		jmpAdr.push(Code.pc - 2);
		while(!jmpOr.isEmpty()) {
			Code.fixup(jmpOr.pop());
		}
	}
	
	
	private Stack<List<Integer>> breakPatch = new Stack<>();
	private Stack<Integer> forechStart = new Stack<Integer>();
	
	@Override
	public void visit(BreakSt breakSt) {
		Code.putJump(0);
		breakPatch.peek().add(Code.pc - 2);
	}
	
	@Override
	public void visit(ContinueSt continueSt) {
		if(inWhile)
			Code.putJump(whileStart.peek());
		else {
			Code.putJump(forechStart.peek());
		}
	}
	
	private Stack<Integer> skipElse = new Stack<Integer>();
	
	@Override
	public void visit(IfCondition ifCond) {
		breakPatch.push(new ArrayList<Integer>());
	}
	
	@Override
	public void visit(IfEndDummy ifDummy) {
		Code.fixup(jmpAdr.pop());
	}
	
	@Override
	public void visit(ElseDummy elseDummy) {
		Code.putJump(0);
		skipElse.push(Code.pc - 2);
		Code.fixup(jmpAdr.pop());
	}
	
	@Override
	public void visit(IfElseSt ifelseSt) {
		Code.fixup(skipElse.pop());
	}
	
	private Stack<Integer> whileStart = new Stack<Integer>();
	
	@Override
	public void visit(WhileDummyConditionStart whileSt) {
		inWhile = true;
		breakPatch.push(new ArrayList<Integer>());
		whileStart.push(Code.pc);
	}
	
	@Override
	public void visit(WhileDummyEnd whileSt) {
		Code.putJump(whileStart.pop());
		Code.fixup(jmpAdr.pop());
	}
	
	@Override
	public void visit(WhileSt whileSt) {
		inWhile = false;
		while(!breakPatch.peek().isEmpty())
			Code.fixup(breakPatch.peek().remove(0));
	}
	
	
	
	
	Obj  target = new Obj(Obj.Var, null, null);
	
	@Override
	public void visit(ForeachSt foreachSt) {
		
		Code.put(Code.dup);
		Code.loadConst('k');
		Code.putFalseJump(Code.eq, forechStart.pop());
		
		
		while(!breakPatch.peek().isEmpty())
			Code.fixup(breakPatch.peek().remove(0));	
	}
	
	@Override
	public void visit(ForIdent forIdent) {

		forechStart.push(Code.pc);;
		Code.store(forIdent.obj);	
	}

	 
	@Override 
	public void visit(SimpleDesignator designator) {
		if(designator.getParent() instanceof ForeachSt) {
			breakPatch.push(new ArrayList<Integer>());
			int currI = 1;
			Code.loadConst('k');
			
			Stack<Integer> loopFix = new Stack<Integer>();
			
			
			while(currI < 6) {
				Code.load(designator.obj);
				Code.load(designator.obj);
				Code.put(Code.arraylength);
				Code.put(Code.const_n + currI);
				Code.put(Code.sub);
				Code.put(Code.aload);
				currI++;
				Code.put(Code.const_n + currI);
				Code.load(designator.obj);
				Code.put(Code.arraylength);
				Code.putFalseJump(Code.le, 0);
				loopFix.push(Code.pc - 2);
			}

			while(!loopFix.isEmpty()) {
				Code.fixup(loopFix.pop());
			}
				
			

		}
	}
	

	

	
	

	
	

}
