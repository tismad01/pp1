// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class ForeachSt extends SingleStatement {

    private Designator Designator;
    private ForIdent ForIdent;
    private ForEachDummyStart ForEachDummyStart;
    private Statement Statement;
    private ForeachDummyEnd ForeachDummyEnd;

    public ForeachSt (Designator Designator, ForIdent ForIdent, ForEachDummyStart ForEachDummyStart, Statement Statement, ForeachDummyEnd ForeachDummyEnd) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.ForIdent=ForIdent;
        if(ForIdent!=null) ForIdent.setParent(this);
        this.ForEachDummyStart=ForEachDummyStart;
        if(ForEachDummyStart!=null) ForEachDummyStart.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.ForeachDummyEnd=ForeachDummyEnd;
        if(ForeachDummyEnd!=null) ForeachDummyEnd.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public ForIdent getForIdent() {
        return ForIdent;
    }

    public void setForIdent(ForIdent ForIdent) {
        this.ForIdent=ForIdent;
    }

    public ForEachDummyStart getForEachDummyStart() {
        return ForEachDummyStart;
    }

    public void setForEachDummyStart(ForEachDummyStart ForEachDummyStart) {
        this.ForEachDummyStart=ForEachDummyStart;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public ForeachDummyEnd getForeachDummyEnd() {
        return ForeachDummyEnd;
    }

    public void setForeachDummyEnd(ForeachDummyEnd ForeachDummyEnd) {
        this.ForeachDummyEnd=ForeachDummyEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(ForIdent!=null) ForIdent.accept(visitor);
        if(ForEachDummyStart!=null) ForEachDummyStart.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(ForeachDummyEnd!=null) ForeachDummyEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(ForIdent!=null) ForIdent.traverseTopDown(visitor);
        if(ForEachDummyStart!=null) ForEachDummyStart.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(ForeachDummyEnd!=null) ForeachDummyEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(ForIdent!=null) ForIdent.traverseBottomUp(visitor);
        if(ForEachDummyStart!=null) ForEachDummyStart.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(ForeachDummyEnd!=null) ForeachDummyEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForeachSt(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForIdent!=null)
            buffer.append(ForIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForEachDummyStart!=null)
            buffer.append(ForEachDummyStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForeachDummyEnd!=null)
            buffer.append(ForeachDummyEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForeachSt]");
        return buffer.toString();
    }
}
