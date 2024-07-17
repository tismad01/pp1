// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class OneClassvAR extends ClassVarList {

    private ClassOneVar ClassOneVar;

    public OneClassvAR (ClassOneVar ClassOneVar) {
        this.ClassOneVar=ClassOneVar;
        if(ClassOneVar!=null) ClassOneVar.setParent(this);
    }

    public ClassOneVar getClassOneVar() {
        return ClassOneVar;
    }

    public void setClassOneVar(ClassOneVar ClassOneVar) {
        this.ClassOneVar=ClassOneVar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassOneVar!=null) ClassOneVar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassOneVar!=null) ClassOneVar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassOneVar!=null) ClassOneVar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OneClassvAR(\n");

        if(ClassOneVar!=null)
            buffer.append(ClassOneVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneClassvAR]");
        return buffer.toString();
    }
}
