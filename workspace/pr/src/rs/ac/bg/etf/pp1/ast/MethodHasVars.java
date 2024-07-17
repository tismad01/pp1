// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class MethodHasVars extends MethodVars {

    private MethodVars MethodVars;
    private VarDecl VarDecl;

    public MethodHasVars (MethodVars MethodVars, VarDecl VarDecl) {
        this.MethodVars=MethodVars;
        if(MethodVars!=null) MethodVars.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public MethodVars getMethodVars() {
        return MethodVars;
    }

    public void setMethodVars(MethodVars MethodVars) {
        this.MethodVars=MethodVars;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodVars!=null) MethodVars.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodVars!=null) MethodVars.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodVars!=null) MethodVars.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodHasVars(\n");

        if(MethodVars!=null)
            buffer.append(MethodVars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodHasVars]");
        return buffer.toString();
    }
}
