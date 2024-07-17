// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class MultipleClassVars extends ClassVarList {

    private ClassVarList ClassVarList;
    private ClassOneVar ClassOneVar;

    public MultipleClassVars (ClassVarList ClassVarList, ClassOneVar ClassOneVar) {
        this.ClassVarList=ClassVarList;
        if(ClassVarList!=null) ClassVarList.setParent(this);
        this.ClassOneVar=ClassOneVar;
        if(ClassOneVar!=null) ClassOneVar.setParent(this);
    }

    public ClassVarList getClassVarList() {
        return ClassVarList;
    }

    public void setClassVarList(ClassVarList ClassVarList) {
        this.ClassVarList=ClassVarList;
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
        if(ClassVarList!=null) ClassVarList.accept(visitor);
        if(ClassOneVar!=null) ClassOneVar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVarList!=null) ClassVarList.traverseTopDown(visitor);
        if(ClassOneVar!=null) ClassOneVar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVarList!=null) ClassVarList.traverseBottomUp(visitor);
        if(ClassOneVar!=null) ClassOneVar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleClassVars(\n");

        if(ClassVarList!=null)
            buffer.append(ClassVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassOneVar!=null)
            buffer.append(ClassOneVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleClassVars]");
        return buffer.toString();
    }
}
