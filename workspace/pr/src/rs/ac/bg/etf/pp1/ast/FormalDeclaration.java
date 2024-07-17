// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class FormalDeclaration extends FormDecl {

    private Type Type;
    private String formParName;
    private ArrayBr ArrayBr;

    public FormalDeclaration (Type Type, String formParName, ArrayBr ArrayBr) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.formParName=formParName;
        this.ArrayBr=ArrayBr;
        if(ArrayBr!=null) ArrayBr.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getFormParName() {
        return formParName;
    }

    public void setFormParName(String formParName) {
        this.formParName=formParName;
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
        if(Type!=null) Type.accept(visitor);
        if(ArrayBr!=null) ArrayBr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ArrayBr!=null) ArrayBr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ArrayBr!=null) ArrayBr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalDeclaration(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+formParName);
        buffer.append("\n");

        if(ArrayBr!=null)
            buffer.append(ArrayBr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalDeclaration]");
        return buffer.toString();
    }
}
