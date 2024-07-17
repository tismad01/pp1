// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class ClassVarDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ClassVarType ClassVarType;
    private ClassVarList ClassVarList;

    public ClassVarDecl (ClassVarType ClassVarType, ClassVarList ClassVarList) {
        this.ClassVarType=ClassVarType;
        if(ClassVarType!=null) ClassVarType.setParent(this);
        this.ClassVarList=ClassVarList;
        if(ClassVarList!=null) ClassVarList.setParent(this);
    }

    public ClassVarType getClassVarType() {
        return ClassVarType;
    }

    public void setClassVarType(ClassVarType ClassVarType) {
        this.ClassVarType=ClassVarType;
    }

    public ClassVarList getClassVarList() {
        return ClassVarList;
    }

    public void setClassVarList(ClassVarList ClassVarList) {
        this.ClassVarList=ClassVarList;
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
        if(ClassVarType!=null) ClassVarType.accept(visitor);
        if(ClassVarList!=null) ClassVarList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVarType!=null) ClassVarType.traverseTopDown(visitor);
        if(ClassVarList!=null) ClassVarList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVarType!=null) ClassVarType.traverseBottomUp(visitor);
        if(ClassVarList!=null) ClassVarList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassVarDecl(\n");

        if(ClassVarType!=null)
            buffer.append(ClassVarType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassVarList!=null)
            buffer.append(ClassVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassVarDecl]");
        return buffer.toString();
    }
}
