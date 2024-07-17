// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class MoreConstants extends MoreConst {

    private MoreConst MoreConst;
    private ConstAssign ConstAssign;

    public MoreConstants (MoreConst MoreConst, ConstAssign ConstAssign) {
        this.MoreConst=MoreConst;
        if(MoreConst!=null) MoreConst.setParent(this);
        this.ConstAssign=ConstAssign;
        if(ConstAssign!=null) ConstAssign.setParent(this);
    }

    public MoreConst getMoreConst() {
        return MoreConst;
    }

    public void setMoreConst(MoreConst MoreConst) {
        this.MoreConst=MoreConst;
    }

    public ConstAssign getConstAssign() {
        return ConstAssign;
    }

    public void setConstAssign(ConstAssign ConstAssign) {
        this.ConstAssign=ConstAssign;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MoreConst!=null) MoreConst.accept(visitor);
        if(ConstAssign!=null) ConstAssign.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MoreConst!=null) MoreConst.traverseTopDown(visitor);
        if(ConstAssign!=null) ConstAssign.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MoreConst!=null) MoreConst.traverseBottomUp(visitor);
        if(ConstAssign!=null) ConstAssign.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreConstants(\n");

        if(MoreConst!=null)
            buffer.append(MoreConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstAssign!=null)
            buffer.append(ConstAssign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreConstants]");
        return buffer.toString();
    }
}
