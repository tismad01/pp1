// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class IfSt extends SingleStatement {

    private IfCond IfCond;
    private Statement Statement;
    private IfEndDummy IfEndDummy;

    public IfSt (IfCond IfCond, Statement Statement, IfEndDummy IfEndDummy) {
        this.IfCond=IfCond;
        if(IfCond!=null) IfCond.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.IfEndDummy=IfEndDummy;
        if(IfEndDummy!=null) IfEndDummy.setParent(this);
    }

    public IfCond getIfCond() {
        return IfCond;
    }

    public void setIfCond(IfCond IfCond) {
        this.IfCond=IfCond;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public IfEndDummy getIfEndDummy() {
        return IfEndDummy;
    }

    public void setIfEndDummy(IfEndDummy IfEndDummy) {
        this.IfEndDummy=IfEndDummy;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfCond!=null) IfCond.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(IfEndDummy!=null) IfEndDummy.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfCond!=null) IfCond.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(IfEndDummy!=null) IfEndDummy.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfCond!=null) IfCond.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(IfEndDummy!=null) IfEndDummy.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfSt(\n");

        if(IfCond!=null)
            buffer.append(IfCond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfEndDummy!=null)
            buffer.append(IfEndDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfSt]");
        return buffer.toString();
    }
}
