// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclaration extends ConstDecl {

    private ConstDeclType ConstDeclType;
    private ConstAssign ConstAssign;
    private MoreConst MoreConst;

    public ConstDeclaration (ConstDeclType ConstDeclType, ConstAssign ConstAssign, MoreConst MoreConst) {
        this.ConstDeclType=ConstDeclType;
        if(ConstDeclType!=null) ConstDeclType.setParent(this);
        this.ConstAssign=ConstAssign;
        if(ConstAssign!=null) ConstAssign.setParent(this);
        this.MoreConst=MoreConst;
        if(MoreConst!=null) MoreConst.setParent(this);
    }

    public ConstDeclType getConstDeclType() {
        return ConstDeclType;
    }

    public void setConstDeclType(ConstDeclType ConstDeclType) {
        this.ConstDeclType=ConstDeclType;
    }

    public ConstAssign getConstAssign() {
        return ConstAssign;
    }

    public void setConstAssign(ConstAssign ConstAssign) {
        this.ConstAssign=ConstAssign;
    }

    public MoreConst getMoreConst() {
        return MoreConst;
    }

    public void setMoreConst(MoreConst MoreConst) {
        this.MoreConst=MoreConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclType!=null) ConstDeclType.accept(visitor);
        if(ConstAssign!=null) ConstAssign.accept(visitor);
        if(MoreConst!=null) MoreConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclType!=null) ConstDeclType.traverseTopDown(visitor);
        if(ConstAssign!=null) ConstAssign.traverseTopDown(visitor);
        if(MoreConst!=null) MoreConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclType!=null) ConstDeclType.traverseBottomUp(visitor);
        if(ConstAssign!=null) ConstAssign.traverseBottomUp(visitor);
        if(MoreConst!=null) MoreConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclaration(\n");

        if(ConstDeclType!=null)
            buffer.append(ConstDeclType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstAssign!=null)
            buffer.append(ConstAssign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MoreConst!=null)
            buffer.append(MoreConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclaration]");
        return buffer.toString();
    }
}
