// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class ClassOneVar implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String classVarName;
    private ArrayBr ArrayBr;

    public ClassOneVar (String classVarName, ArrayBr ArrayBr) {
        this.classVarName=classVarName;
        this.ArrayBr=ArrayBr;
        if(ArrayBr!=null) ArrayBr.setParent(this);
    }

    public String getClassVarName() {
        return classVarName;
    }

    public void setClassVarName(String classVarName) {
        this.classVarName=classVarName;
    }

    public ArrayBr getArrayBr() {
        return ArrayBr;
    }

    public void setArrayBr(ArrayBr ArrayBr) {
        this.ArrayBr=ArrayBr;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
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
        buffer.append("ClassOneVar(\n");

        buffer.append(" "+tab+classVarName);
        buffer.append("\n");

        if(ArrayBr!=null)
            buffer.append(ArrayBr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassOneVar]");
        return buffer.toString();
    }
}
