package rs.ac.bg.etf.pp1;
import rs.ac.bg.etf.pp1.ast.PrintSt;
import rs.ac.bg.etf.pp1.ast.VarDecl;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import org.apache.log4j.*;

public class RuleVisitor extends VisitorAdaptor {
	
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

}
