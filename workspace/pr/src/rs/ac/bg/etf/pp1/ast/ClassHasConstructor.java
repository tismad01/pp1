// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class ClassHasConstructor extends ClassBody {

    private ClassVars ClassVars;
    private ConstructorDecl ConstructorDecl;

    public ClassHasConstructor (ClassVars ClassVars, ConstructorDecl ConstructorDecl) {
        this.ClassVars=ClassVars;
        if(ClassVars!=null) ClassVars.setParent(this);
        this.ConstructorDecl=ConstructorDecl;
        if(ConstructorDecl!=null) ConstructorDecl.setParent(this);
    }

    public ClassVars getClassVars() {
        return ClassVars;
    }

    public void setClassVars(ClassVars ClassVars) {
        this.ClassVars=ClassVars;
    }

    public ConstructorDecl getConstructorDecl() {
        return ConstructorDecl;
    }

    public void setConstructorDecl(ConstructorDecl ConstructorDecl) {
        this.ConstructorDecl=ConstructorDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassVars!=null) ClassVars.accept(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVars!=null) ClassVars.traverseTopDown(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVars!=null) ClassVars.traverseBottomUp(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassHasConstructor(\n");

        if(ClassVars!=null)
            buffer.append(ClassVars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstructorDecl!=null)
            buffer.append(ConstructorDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassHasConstructor]");
        return buffer.toString();
    }
}
