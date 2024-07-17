// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class PrevMultipleVarDecl extends MultipleVarDecl {

    private String varName;
    private ArrayBr ArrayBr;

    public PrevMultipleVarDecl (String varName, ArrayBr ArrayBr) {
        this.varName=varName;
        this.ArrayBr=ArrayBr;
        if(ArrayBr!=null) ArrayBr.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public ArrayBr getArrayBr() {
        return ArrayBr;
    }

    public void setArrayBr(ArrayBr ArrayBr) {
        this.ArrayBr=ArrayBr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ArrayBr!=null) ArrayBr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrayBr!=null) ArrayBr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrayBr!=null) ArrayBr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrevMultipleVarDecl(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(ArrayBr!=null)
            buffer.append(ArrayBr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrevMultipleVarDecl]");
        return buffer.toString();
    }
}
