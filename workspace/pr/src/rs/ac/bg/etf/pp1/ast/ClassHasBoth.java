// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class ClassHasBoth extends ClassBody {

    private ClassVars ClassVars;
    private ConstructorDecl ConstructorDecl;
    private ClassMethods ClassMethods;

    public ClassHasBoth (ClassVars ClassVars, ConstructorDecl ConstructorDecl, ClassMethods ClassMethods) {
        this.ClassVars=ClassVars;
        if(ClassVars!=null) ClassVars.setParent(this);
        this.ConstructorDecl=ConstructorDecl;
        if(ConstructorDecl!=null) ConstructorDecl.setParent(this);
        this.ClassMethods=ClassMethods;
        if(ClassMethods!=null) ClassMethods.setParent(this);
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

    public ClassMethods getClassMethods() {
        return ClassMethods;
    }

    public void setClassMethods(ClassMethods ClassMethods) {
        this.ClassMethods=ClassMethods;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassVars!=null) ClassVars.accept(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.accept(visitor);
        if(ClassMethods!=null) ClassMethods.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVars!=null) ClassVars.traverseTopDown(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.traverseTopDown(visitor);
        if(ClassMethods!=null) ClassMethods.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVars!=null) ClassVars.traverseBottomUp(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.traverseBottomUp(visitor);
        if(ClassMethods!=null) ClassMethods.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassHasBoth(\n");

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

        if(ClassMethods!=null)
            buffer.append(ClassMethods.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassHasBoth]");
        return buffer.toString();
    }
}
