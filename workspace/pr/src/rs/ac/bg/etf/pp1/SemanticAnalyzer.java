package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {


	Logger log = Logger.getLogger(getClass());
	
	
	
	private boolean errorDetected = false;
	
	private Struct currentType = null;
	private Struct currentClass = null;
	private Obj currentMethod = null;
	
	int nVars = 0;

	
	private boolean toReturn = false;
	
	private int methParams = 0;
	
	private int whileCount = 0;
	private boolean inForeach = false;
	
	private boolean isArray = false;
	
	private String currentOperation = null;
	
	private Stack<ArrayList<Struct>> actPars = new Stack<ArrayList<Struct>>();
	
	private Stack<Obj> bracketDesigSt = new Stack<Obj>();

	
	public void report_error(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	private boolean areCompatible(Struct first, Struct second) {
		
		
		if(first.assignableTo(second)) {
			return true;
		}
		
		if(first == Tab.noType && (second.getKind() == Struct.Class || second.getKind() == Struct.Array))
			return true;
		
		if(first.getKind() == Struct.Array && second.getKind() == Struct.Array) {
			first = first.getElemType();
			second = second.getElemType();
		}
		
		return true;
	}
	
	
	@Override
	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
	}
	
	@Override
	public void visit(Program program) {
		nVars = Tab.currentScope.getnVars();
		

		Obj main = Tab.find("main");
		if(main != Tab.noObj  &&	main.getKind() == Obj.Meth &&	main.getType() == Tab.noType &&	main.getLevel() == 0) 
			report_info("Main ispravno deklarisan", null);
		else
			report_error(": void main() neispravno deklarisan.", null);
		
		
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}
	
	
	@Override 
	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		if(typeNode == Tab.noObj) {
			report_error(" GRESKA na liniji: "  + type.getLine() + ". Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
    		type.struct = Tab.noType;
		}
		
		else {
			if(Obj.Type == typeNode.getKind()){
    			type.struct = typeNode.getType();
    		}else{
    			report_error(" GRESKA na liniji: "  + type.getLine() + ". Ime " + type.getTypeName() + " ne predstavlja tip!", null);
    			type.struct = Tab.noType;
    		}
		}
		
		currentType = type.struct;
	}
	
	//simbolicke konstante
	
	@Override 
	public void visit(FactorNumConst numConst) {
		numConst.struct = Tab.intType;
	}
	
	@Override 
	public void visit(FactorCharConst charConst) {
		charConst.struct = Tab.charType;
	}
	
	@Override 
	public void visit(FactorBoolConst boolConst) {
		boolConst.struct = Tab1.boolType; 
	}
	
	
	@Override
	public void visit(ConstDeclType constType) {
		currentType = constType.getType().struct;
		if(currentType != Tab.intType && currentType != Tab.charType && currentType != Tab1.boolType) {
			report_error(" GRESKA na liniji: "  + constType.getLine() + ". Konstanta je pogresnog tipa.", null);
			return;
		}
	}
	
	@Override
	public void visit(NumVal constValue) { 
		
		String name = constValue.getConstName();
		Obj constNode = Tab.find(name);
		if(constNode != Tab.noObj) {
			report_error(" GRESKA na liniji: "  + constValue.getLine() + ". Vec postoji konstanta sa " + name + " imenom.", null);
			return;
		}
		
		if(Tab.intType == currentType) {
			constNode = Tab.insert(Obj.Con, constValue.getConstName(), currentType);
			report_info("Kreirana je konstanta " + name, constValue);
			constNode.setAdr(constValue.getNumbeValue());
		}
		else {
			report_error(" GRESKA na liniji: "  + constValue.getLine() + ". Konstanta " + name + " je pogresnog tipa. ", null);
			return;
		}
	}
	
	@Override
	public void visit(CharVal constValue) { 
		
		String name = constValue.getConstName();
		Obj constNode = Tab.find(name);
		if(constNode != Tab.noObj) {
			report_error(" GRESKA na liniji: "  + constValue.getLine() + ". Vec postoji konstanta sa " + name + " imenom.", null);
			return;
		}
		
		if(Tab.charType == currentType) {
			constNode = Tab.insert(Obj.Con, constValue.getConstName(), currentType);
			report_info("Kreirana je konstanta " + name, constValue);
			constNode.setAdr(constValue.getCharValue());
		}
		else {
			report_error(" GRESKA na liniji: "  + constValue.getLine() + ". Konstanta " + name + " je pogresnog tipa. ", null);
			return;
		}
	}
	
	@Override
	public void visit(BoolVal constValue) { 
		
		String name = constValue.getConstName();
		Obj constNode = Tab.find(name);
		if(constNode != Tab.noObj) {
			report_error(" GRESKA na liniji: "  + constValue.getLine() + ". Vec postoji konstanta sa " + name + " imenom.", null);
			return;
		}
		
		if(Tab1.boolType == currentType) {
			constNode = Tab.insert(Obj.Con, constValue.getConstName(), currentType);
			report_info("Kreirana je konstanta " + name, constValue);
			if(constValue.getBoolValue().equals("true")) {
				constNode.setAdr(1);
			}
			else
				constNode.setAdr(0);
		}
		else {
			report_error(" GRESKA na liniji: "  + constValue.getLine() + ". Konstanta " + name + " je pogresnog tipa. ", null);
			return;
		}
	}


	
	//globalne promenljive
	
	@Override
	public void visit(PrevOneVarDecl varDecl) {
		
		String varName = varDecl.getVarName();
		Obj varNode = Tab.find(varName);
		if(varNode != Tab.noObj) {
			if(Tab.currentScope.findSymbol(varName) != null) {
				report_error(" GRESKA na liniji: "  + varDecl.getLine() + ". Promenljiva sa imenom " + varName + " je vec deklarisana.", null);
				return;
			} }
			
			Struct varType = currentType;
			if(isArray) {
				varType = new Struct(Struct.Array, currentType);
			}
			
			Tab.insert(Obj.Var, varName, varType);
			
			if(currentMethod == null) {
				report_info("Kreirana globalna promenljiva sa imenom " + varName + (isArray ? "[]" : "") , varDecl);
			}
			else {
				report_info("Kreirana lokalna promenljiva sa imenom " + varName + (isArray ? "[]" : "") , varDecl);
			}
			
			
			
			isArray = false;
	
		
	}
	
	@Override
	public void visit(PrevMultipleVarDecl varDecl) {
		String varName = varDecl.getVarName();
		Obj varNode = Tab.find(varName);
		if(varNode != Tab.noObj) {
			if(Tab.currentScope.findSymbol(varName) != null) {
				report_error(" GRESKA na liniji: "  + varDecl.getLine() + ". Promenljiva sa imenom " + varName + " je vec deklarisana.", null);
				return;
			}
		}
			Struct varType = currentType;
			if(isArray) {
				varType = new Struct(Struct.Array, currentType);
			}
			
			Tab.insert(Obj.Var, varName, varType);
			
			if(currentMethod == null) {
				report_info("Kreirana globalna promenljiva sa imenom " + varName + (isArray ? "[]" : "") , varDecl);
			}
			else {
				report_info("Kreirana lokalna promenljiva sa imenom " + varName + (isArray ? "[]" : "") , varDecl);
			}
	
			
			isArray = false;
		
	}
	
	@Override 
	public void visit(VarIsArray varIsArray) {
		this.isArray = true;
	}
	
	//klase
	
	@Override
	public void visit(ClassName className) {
		String name = className.getClassName();
		Obj classNode = Tab.find(name);
		if(classNode != Tab.noObj) {
			report_error(" GRESKA na liniji: " + className.getLine() + ". Klasa sa imenom " + name + " je vec deklarisana.", null); 
			className.obj = Tab.noObj;
    		Tab.openScope();
			return;
    	}
		
		currentClass = new Struct(Struct.Class);
		
		className.obj = Tab.insert(Obj.Type, className.getClassName(), currentClass);
        Tab.openScope();
        Tab.insert(Obj.Fld, "VTF_address", Tab.intType);
		
	}
	
	@Override
	public void visit(ClassOneVar classVar) {
		String varName = classVar.getClassVarName();
		Obj varNode = Tab.find(varName);
    	if(varNode != Tab.noObj) {
    		if(Tab.currentScope.findSymbol(varName) != null) {
				report_error(" GRESKA na liniji: " + classVar.getLine() + "Promenljiva  " + varName + " vec deklarisana. ", null);    		
				return;
    		}
    	}
    	
    	Struct varType = currentType;
    	if(isArray == true) {
    		varType = new Struct(Struct.Array, currentType);
    	}


    	varNode = Tab.insert(Obj.Fld, varName, varType);

    	
    	isArray = false;
	
	}
	
	
	//funkcije
	
	@Override
	public void visit(MethodDecl method) {
		String methodName = method.getMethodName().getMethodName();
		
		if(!( toReturn || currentMethod.getType() == Tab.noType)) {
			report_error(" GRESKA na liniji: "  + method.getLine() + ". Funkcija " + methodName + " nema pravilan return.", null);
		}
		
		currentMethod.setLevel(methParams);
		
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		
		methParams = 0;
		toReturn = false;
		currentMethod = null;
	}
	
	@Override 
	public void visit(MethodName method) {
		
		currentMethod = Tab.insert(Obj.Meth, method.getMethodName(), method.getMethodType().struct );
		
		method.obj = currentMethod;
		
		Tab.openScope();
		
		if(!method.getMethodName().equalsIgnoreCase("main"))
			report_info("Deklarisana globalna funkcija sa imenom " + method.getMethodName(), method);
		
	}
	
	@Override 
	public void visit(MethodHasType method) {
		method.struct = currentType;
	}
	
	@Override 
	public void visit(MethodVoid method) {
		method.struct = Tab.noType;
	}
	
	@Override
	public void visit(FormalDeclaration formal) {
		methParams++;
		String paramName = formal.getFormParName();
		Struct paramType = currentType;
		
		Obj paramNode = Tab.find(paramName);
		if(paramNode != Tab.noObj) {
			if(Tab.currentScope.findSymbol(paramName) != null) {
				report_error(" GRESKA na liniji: "  + formal.getLine() + ". Formalni parametar sa imenom " + paramName + " je vec deklarisan.", null);
				return;
			}
		}
		
		if(isArray) {
			paramType = new Struct(Struct.Array, currentType);
		}
		
		Tab.insert(Obj.Var, paramName, paramType);
		
		isArray = false;
		
	}
	
	@Override
	public void visit(ReturnSt returnSt) {
		toReturn = true;
		Struct methodType = currentMethod.getType();
		
		if(methodType == Tab.noType) {
			report_error(" GRESKA na liniji: "  + returnSt.getLine() + ". Void funkcije nemaju povratnu vrednost. ", null);
			return;
		}
		
		if(!methodType.equals(returnSt.getExpr().struct)) {
			report_error(" GRESKA na liniji: "  + returnSt.getLine() + ". Deklarisan povratni tip funkcije i ono sto ona stvarno vraca se ne poklapaju. ", null);
			return;
		}
	}
	
	//poziv funkcije
	
	@Override
	public void visit(FunctionDesignator funcDes) {
		funcDes.obj = funcDes.getDesignator().obj;
		actPars.push(new ArrayList<Struct>());
	}
	
	@Override
	public void visit(ParsList actParsList) {
		actPars.peek().add(actParsList.getExpr().struct);
	}
	
	@Override
	public void visit(OnePar actPar) {
		actPars.peek().add(actPar.getExpr().struct);
	}
	
	@Override
	public void visit(FactorFunc factorFunc) {
		Obj funcNode = factorFunc.getFunctionDesignator().obj;
		
		if(funcNode == null) {
			factorFunc.struct = Tab.noType;
			report_error(" GRESKA na liniji: " + factorFunc.getLine(), null);
			return;
		}
		
		Obj temp = Tab.currentScope().getOuter().getLocals().searchKey(funcNode.getName());
		
		if(temp == Tab.noObj) {
			report_error(" GRESKA na liniji: " + factorFunc.getLine() + ". Ime " + funcNode.getName() + " nije deklarisano. ", null);
			factorFunc.struct = Tab.noType;
			return;
		}
		
		
		
		if(funcNode.getKind() != Obj.Meth) {
			report_error(" GRESKA na liniji: " + factorFunc.getLine() + ". " + funcNode.getName() + " nije metoda. ", null);
			factorFunc.struct = Tab.noType;
			return;
		}
		
		if(funcNode.getType() == Tab.noType) {
			report_error(" GRESKA na liniji: " + factorFunc.getLine() + ". " + funcNode.getName() + " je void tipa i ne moze se pozivati u izrazima. ", null);
			factorFunc.struct = Tab.noType;
			return;
		}
		
		ArrayList<Struct> arguments = actPars.pop();
		int argNum = arguments.size();
		ArrayList<Obj> formalArgs = new ArrayList<Obj>();
		int index = 0;
		
		for(Obj obj: funcNode.getLocalSymbols()) {
			if(index < funcNode.getLevel() && obj != null) {
				formalArgs.add(obj);
				index++;
			}
		}
		
		int formalArgsNum = funcNode.getLevel();
		
		if(argNum != formalArgsNum) {
			report_error(" GRESKA na liniji: " + factorFunc.getLine() + ". Broj stvarnih i formalnih parametara mora biti isti. ", null);
			factorFunc.struct = Tab.noType;
			return;
		}
		
		
		for(int i = 0; i < argNum ; i++) {
			if(!areCompatible(arguments.get(i), formalArgs.get(i).getType())){
				report_error(" GRESKA na liniji: " + factorFunc.getLine() + ". Nekompatibilna dodela parametara. ", null);
				factorFunc.struct = Tab.noType;
				return;
			}
		}
		
		report_info("Pozvana metoda " + funcNode.getName(), factorFunc);
		
		factorFunc.struct = funcNode.getType();
		
		
	}
	
	@Override
	public void visit(DesignatorFunc designFunc) {
		Obj funcNode = designFunc.getFunctionDesignator().obj;
		
		if(funcNode == null) {
			report_error(" GRESKA na liniji: " + designFunc.getLine() + ". Metoda " + funcNode.getName() + " nije deklarisana. ", null);
			return;
		}
		
		Obj temp = Tab.currentScope().getOuter().getLocals().searchKey(funcNode.getName());
		
		if(temp == Tab.noObj) {
			report_error(" GRESKA na liniji: " + designFunc.getLine() + ". Metoda " + funcNode.getName() + " nije deklarisana. ", null);
			return;
		}
		
		if(funcNode.getKind() != Obj.Meth) {
			report_error(" GRESKA na liniji: " + designFunc.getLine() + ". " + funcNode.getName() + " nije metoda. ", null);
			return;
		}
		
		ArrayList<Struct> arguments = actPars.pop();
		int argNum = arguments.size();
		ArrayList<Obj> formalArgs = new ArrayList<Obj>();
		int index = 0;
		
		for(Obj obj: funcNode.getLocalSymbols()) {
			if(index < funcNode.getLevel() && obj != null) {
				formalArgs.add(obj);
				index++;
			}
		}
		
		int formalArgsNum = funcNode.getLevel();
		
		if(argNum != formalArgsNum) {
			report_error(" GRESKA na liniji: " + designFunc.getLine() + ". Broj stvarnih i formalnih parametara mora biti isti. ", null);
			return;
		}
		
		
		for(int i = 0; i < argNum ; i++) {
			if(!areCompatible(arguments.get(i), formalArgs.get(i).getType())) {
				report_error(" GRESKA na liniji: " + designFunc.getLine() + ". Nekompatibilna dodela parametara. ", null);
				return;
			}
		}
		
		report_info("Pozvana metoda " + funcNode.getName(), designFunc);
		
	}
	
	
	//Statements
	
	@Override
	public void visit(WhileDummyStart whileStart) {
		whileCount++;
	}
	
	@Override
	public void visit(WhileSt whileSt) {
		whileCount--;
	}
	
	
	@Override
	public void visit(ForeachSt foreachSt) {
		
		if(foreachSt.getDesignator().obj.getType().getKind() != Struct.Array) {
			report_error(" GRESKA na liniji " + foreachSt.getLine() + ". Foreach se moze vrsiti samo nad nizom. ", null);
		}
		
		Obj identVar = Tab.find(foreachSt.getForIdent().getForId());
		if(identVar.getType() == Tab.noType) {
			report_error(" GRESKA na liniji " + foreachSt.getLine() + ". Ne postoji trazena promenljiva. ", null);
			return;
		}
		
	
		if(identVar.getKind() != Obj.Var) {
			report_error(" GRESKA na liniji " + foreachSt.getLine() + ". Element mora biti promenljiva. ", null);
			return;
		}
		
		if(!areCompatible(identVar.getType(), foreachSt.getDesignator().obj.getType())){
			report_error(" GRESKA na liniji: " + foreachSt.getLine() + ". Nekompatibilna dodela. ", null);
			return;
		}
	}
	
	@Override
	public void visit(ForIdent forIdent) {
		Obj arrEl = Tab.find(forIdent.getForId());
		forIdent.obj = arrEl;
		
	}
	
	
	@Override
	public void visit(BreakSt breakSt) {
		if( (whileCount == 0) && !inForeach ) {
			report_error(": break ne moze da se nadje van petlje ", null);
			return;
		}
			
	}
	
	@Override
	public void visit(ContinueSt contSt) {
		if( (whileCount == 0) && !inForeach ) {
			report_error(": continue ne moze da se nadje van petlje ", null);
			return;
		}
	}
	
	@Override
	public void visit(ReadSt readSt) {
		Obj designator = readSt.getDesignator().obj;
		
		if(designator.getKind() != Obj.Var && designator.getKind() != Obj.Elem && designator.getKind() != Obj.Fld) {
			report_error(" GRESKA na liniji: " + readSt.getLine() + ". Read se moze raditi samo iz promenljive, elementa niza ili polja unutra objekta. ", null);
			return;
		}
		
		
		if(designator.getType() != Tab.intType && designator.getType() != Tab.charType && designator.getType() != Tab1.boolType ) {
			report_error(" GRESKA na liniji: " + readSt.getLine() + ". Read se moze raditi samo u promenljivu tipa int, char ili bool. ", null);
			return;
		}
		
	}
	
	
	@Override
	public void visit(PrintSt printSt) {
		Struct expr = printSt.getExpr().struct;
		if(expr != Tab.intType && expr != Tab.charType && expr != Tab1.boolType){
			report_error(" GRESKA na liniji: " + printSt.getLine() + ". Print se moze raditi samo sa int, char ili bool promenljivama. ", null);
			return;
		}
	}
	
	
	@Override
	public void visit(PrintStatementWidth printSt) {
		Struct expr = printSt.getExpr().struct;
		if(expr != Tab.intType && expr != Tab.charType && expr != Tab1.boolType){
			report_error(" GRESKA na liniji: " + printSt.getLine() + ". Print se moze raditi samo sa int, char ili bool promenljivama. ", null);
			return;
		}
	}
	
	
	//Designator
	
	@Override
	public void visit(DesigList designators) {
		bracketDesigSt.push(designators.getDesignator().obj);
	}
	
	@Override
	public void visit(DesignatorBr designator) {
		Obj arrayDesig = designator.getDesignator().obj;
		Struct arrayType = designator.getDesignator().obj.getType();	
		
		if(arrayDesig.getType().getKind() != Struct.Array) {
			report_error(" GRESKA na liniji: " + designator.getLine() + ". Sa desne strane = mora se naci niz", null);
			return;
		}
		
		
		for (Obj obj : bracketDesigSt) {

			if(obj.getKind() != Obj.Var && obj.getKind() != Obj.Elem && obj.getKind() != Obj.Fld) {
				report_error(" GRESKA na liniji: " + designator.getLine() + ". Sa leve strane = mora se naci promenljiva, element niza ili polje unutar objekta", null);
				return;
			}
			
			if(obj != null) {
				if( !areCompatible(arrayType, obj.getType())){
					report_error(" GRESKA na liniji: " + designator.getLine() + ". Nekompatibilna dodela. ", null);
					return;
				}
			}
		}
		
		bracketDesigSt.clear();
	}
	
	@Override
	public void visit(DesignatorInc designator) {
		Obj designObj = designator.getDesignator().obj;
		if(designObj.getKind() != Obj.Var && designObj.getKind() != Obj.Elem && designObj.getKind() != Obj.Fld) {
			report_error(" GRESKA na liniji: " + designator.getLine() + ". Inkrementiranje se moze vrsiti samo nad promenljivama, elementima niza i poljima objekta unutrasnje klase", null);
			return;
		}
		
		if(designObj.getType() != Tab.intType) {
			report_error(" GRESKA na liniji: " + designator.getLine() + ". Inkrementiranje moze da se vrsi samo nad int tipom. ", null);
			return;
		}
	}
	
	
	@Override
	public void visit(DesignatorDec designator) {
		Obj designObj = designator.getDesignator().obj;
		if(designObj.getKind() != Obj.Var && designObj.getKind() != Obj.Elem && designObj.getKind() != Obj.Fld) {
			report_error(" GRESKA na liniji: " + designator.getLine() + ". Dekrementiranje se moze vrsiti samo nad promenljivama, elementima niza i poljima objekta unutrasnje klase", null);
			return;
		}
		
		if(designObj.getType() != Tab.intType) {
			report_error(" GRESKA na liniji: " + designator.getLine() + ". Dekrementiranje moze da se vrsi samo nad int tipom. ", null);
			return;
		}
	}
	
	@Override
	public void visit(SimpleDesignator designator) {
		Obj designatorNode = Tab.find(designator.getDesignName());
		if(designatorNode == Tab.noObj) {
			report_error(" GRESKA na liniji: " + designator.getLine() + ". Ime " + designator.getDesignName() + " nije deklarisano. ", null);
			return;
		}
		designator.obj = designatorNode;
		
	}
	
	@Override
	public void visit(ArrayDesignator designator) {
		if(designator.getArrayName().getDesignator().obj.getType().getKind() != Struct.Array) {
			report_error(" GRESKA na liniji: " + designator.getLine() + ". Promenljiva mora biti niz. ", null);
			designator.obj = Tab.noObj;
			return;
		}
		
		if(designator.getExpr().struct != Tab.intType) {
			report_error(" GRESKA na liniji: " + designator.getLine() + ".  Velicina niza se moze inicirati samo sa int vrednoscu.", null);
			designator.obj = Tab.noObj;
			return;
		}
		
		report_info("Pristupa se elementu niza " + designator.getArrayName().getDesignator().obj.getName(), designator);
		designator.obj = new Obj(Obj.Elem, designator.getArrayName().getDesignator().obj.getName(), designator.getArrayName().getDesignator().obj.getType().getElemType() );
	}
	
	@Override
	public void visit(DesignatorAssign designator) {
		Obj dest = designator.getDesignator().obj;
    	Struct expr = designator.getExpr().struct;
    	
    	if((dest.getKind() != Obj.Var) && (dest.getKind() != Obj.Elem) && (dest.getKind() != Obj.Fld)) {
			report_error(" GRESKA na liniji: " + designator.getLine() + ". Dodela se moze vrsiti samo nad promenljivama, elementima niza i poljima objekta unutrasnje klase", null);
			return;
		}
    	
    	
    	if(!expr.assignableTo(dest.getType())){
    		report_error(" GRESKA na liniji " + designator.getLine() + ". Tipovi pri dodeli nisu kompatibilni. ", null);
    		return;
    	}
	}
	
	//Factor
	
  
	@Override
	public void visit(FactorExpr factor) {
		factor.struct = factor.getExpr().struct;
	}
	
	@Override
	public void visit(SimpleFactor factor) {
		
		if(factor.getDesignator().obj == null) {
			report_error("Greska na liniji: " + factor.getLine(), null);
			return;
		}
		
		factor.struct = factor.getDesignator().obj.getType();
		
		
		Obj factorObj = factor.getDesignator().obj;
		
		if(factorObj.getKind() == Obj.Var) {
				report_info("Pristupa se promenjivoj " + factorObj.getName(), factor);
		}
			
	}
	
	@Override
	public void visit(FactorNewArray factor) {
		if(factor.getExpr().struct != Tab.intType) {
			factor.struct = Tab.noType;
			report_error(" GRESKA na liniji: " + factor.getLine() + ". Velicina niza se moze inicirati samo sa int vrednoscu. ", null);
			return;
		}
		
		factor.struct = new Struct(Struct.Array, factor.getType().struct);
			
	}
	
	//CondFact
	
	@Override
	public void visit(ExprCondition condition) {
		if(!(condition.getExpr().struct == Tab1.boolType)) {
			report_error(" GRESKA na liniji: " + condition.getLine() + ". Uslov mora biti bool. ", null);
			return;
		}
	}
	
	@Override
	public void visit(RelOpCondFact condition) {
		Expr first = condition.getExpr();
    	Expr second = condition.getExpr1();
    	
    	if(!areCompatible(first.struct, second.struct)){
        	report_error(" GRESKA na liniji: " + condition.getLine() + ". Tipovi izraza nisu kompatibilni. ", null);  	    		
    	}
    	
    	if(first.struct.getKind() == Struct.Array || second.struct.getKind() == Struct.Array) {
    		if(!(currentOperation.equals("==") || currentOperation.equals("!="))) {
    			report_error(" GRESKA na liniji: " + condition.getLine() + ". Moze se koristiti samo != ili ==. ", null);
    			return;
    		}
    	}
	}
	
	//Operatori
	
	@Override
	public void visit(RelopEq operation) {
		currentOperation = "==";
	}
	
	@Override
	public void visit(RelopNotEq operation) {
		currentOperation = "!=";
	}
	
	@Override
	public void visit(RelopGt operation) {
		currentOperation = ">";
	}
	
	@Override
	public void visit(RelopGteq operation) {
		currentOperation = ">=";
	}
	
	@Override
	public void visit(RelopLs operation) {
		currentOperation = "<";
	}
	
	@Override
	public void visit(RelopLsEq operation) {
		currentOperation = "<=";
	}
	
	// term expr
	
	@Override 
	public void visit(OneFactor factor) {
		factor.struct = factor.getFactor().struct;
	}
	
	@Override 
	public void visit(MulOpFactors factor) {
    	if(factor.getFactor().struct != Tab.intType || factor.getFactorList().struct != Tab.intType) {
    		report_error(" GRESKA na liniji: " + factor.getLine() + ". Cinioci moraju biti int. ", null);        	
    		factor.struct = Tab.noType;
    		return;
    	}
    	factor.struct = Tab.intType;
	}
	
	@Override
    public void visit(Term term) {
    	term.struct = term.getFactorList().struct;
    } 
	
	@Override
    public void visit(SimpleExpr expr) {
		expr.struct = expr.getTerm().struct;
    } 
	
	@Override
    public void visit(NegExpr expr) {
    	if(expr.getTerm().struct != Tab.intType) {
    		report_error(" GRESKA na liniji: " + expr.getLine() + ". Tip mora biti int. ", null);            	
    		expr.struct = Tab.noType;
    		return;
    	}
    	expr.struct = Tab.intType;    
    } 
	
	@Override
    public void visit(AddopExpr expr) {
    	if(!areCompatible(expr.getExpr().struct, expr.getTerm().struct)){
    		report_error(" GRESKA na liniji: " + expr.getLine() + ". Nekompatibilni tipovi. ", null);        	
    		expr.struct = Tab.noType;
    		return;
    	}
    	if(expr.getExpr().struct != Tab.intType || expr.getTerm().struct != Tab.intType) {
    		report_error(" GRESKA na liniji: " + expr.getLine() + ". Tip mora biti int. ", null);    	
    		expr.struct = Tab.noType;
    		return;
    	}
    	expr.struct = Tab.intType;
    } 
	
	
	
	
	// ------------------
	
	public boolean passed() {
    	return !errorDetected;
    }


	
	
}
