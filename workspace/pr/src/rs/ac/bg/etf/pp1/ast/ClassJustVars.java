// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class ClassJustVars extends ClassBody {

    private ClassVars ClassVars;

    public ClassJustVars (ClassVars ClassVars) {
        this.ClassVars=ClassVars;
        if(ClassVars!=null) ClassVars.setParent(this);
    }

    public ClassVars getClassVars() {
        return ClassVars;
    }

    public void setClassVars(ClassVars ClassVars) {
        this.ClassVars=ClassVars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassVars!=null) ClassVars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVars!=null) ClassVars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVars!=null) ClassVars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassJustVars(\n");

        if(ClassVars!=null)
            buffer.append(ClassVars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassJustVars]");
        return buffer.toString();
    }
}
