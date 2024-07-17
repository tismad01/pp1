package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {

	Logger log = Logger.getLogger(getClass());
	int printCallCount = 0;
	int varDeclCount = 0;
	
	@Override
	public void visit(PrintSt PrintSt) { 
		printCallCount++;
		log.info("Prepoznata naredba print.");
	}
	
	@Override
	public void visit(VarDecl varDecl) { 
		varDeclCount++;
	}
	
	@Override
	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
	}
	
	@Override
	public void visit(Program program) {
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}
}
