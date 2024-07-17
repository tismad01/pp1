// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class DesignatorBr extends DesignatorStatement {

    private BrBegin BrBegin;
    private MoreDes MoreDes;
    private Designator Designator;
    private BrEnd BrEnd;

    public DesignatorBr (BrBegin BrBegin, MoreDes MoreDes, Designator Designator, BrEnd BrEnd) {
        this.BrBegin=BrBegin;
        if(BrBegin!=null) BrBegin.setParent(this);
        this.MoreDes=MoreDes;
        if(MoreDes!=null) MoreDes.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.BrEnd=BrEnd;
        if(BrEnd!=null) BrEnd.setParent(this);
    }

    public BrBegin getBrBegin() {
        return BrBegin;
    }

    public void setBrBegin(BrBegin BrBegin) {
        this.BrBegin=BrBegin;
    }

    public MoreDes getMoreDes() {
        return MoreDes;
    }

    public void setMoreDes(MoreDes MoreDes) {
        this.MoreDes=MoreDes;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public BrEnd getBrEnd() {
        return BrEnd;
    }

    public void setBrEnd(BrEnd BrEnd) {
        this.BrEnd=BrEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(BrBegin!=null) BrBegin.accept(visitor);
        if(MoreDes!=null) MoreDes.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
        if(BrEnd!=null) BrEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(BrBegin!=null) BrBegin.traverseTopDown(visitor);
        if(MoreDes!=null) MoreDes.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(BrEnd!=null) BrEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(BrBegin!=null) BrBegin.traverseBottomUp(visitor);
        if(MoreDes!=null) MoreDes.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(BrEnd!=null) BrEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorBr(\n");

        if(BrBegin!=null)
            buffer.append(BrBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MoreDes!=null)
            buffer.append(MoreDes.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(BrEnd!=null)
            buffer.append(BrEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorBr]");
        return buffer.toString();
    }
}
