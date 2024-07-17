// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class WhileSt extends SingleStatement {

    private WhileDummyConditionStart WhileDummyConditionStart;
    private Condition Condition;
    private WhileDummyStart WhileDummyStart;
    private Statement Statement;
    private WhileDummyEnd WhileDummyEnd;

    public WhileSt (WhileDummyConditionStart WhileDummyConditionStart, Condition Condition, WhileDummyStart WhileDummyStart, Statement Statement, WhileDummyEnd WhileDummyEnd) {
        this.WhileDummyConditionStart=WhileDummyConditionStart;
        if(WhileDummyConditionStart!=null) WhileDummyConditionStart.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.WhileDummyStart=WhileDummyStart;
        if(WhileDummyStart!=null) WhileDummyStart.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.WhileDummyEnd=WhileDummyEnd;
        if(WhileDummyEnd!=null) WhileDummyEnd.setParent(this);
    }

    public WhileDummyConditionStart getWhileDummyConditionStart() {
        return WhileDummyConditionStart;
    }

    public void setWhileDummyConditionStart(WhileDummyConditionStart WhileDummyConditionStart) {
        this.WhileDummyConditionStart=WhileDummyConditionStart;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public WhileDummyStart getWhileDummyStart() {
        return WhileDummyStart;
    }

    public void setWhileDummyStart(WhileDummyStart WhileDummyStart) {
        this.WhileDummyStart=WhileDummyStart;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public WhileDummyEnd getWhileDummyEnd() {
        return WhileDummyEnd;
    }

    public void setWhileDummyEnd(WhileDummyEnd WhileDummyEnd) {
        this.WhileDummyEnd=WhileDummyEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(WhileDummyConditionStart!=null) WhileDummyConditionStart.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(WhileDummyStart!=null) WhileDummyStart.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(WhileDummyEnd!=null) WhileDummyEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(WhileDummyConditionStart!=null) WhileDummyConditionStart.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(WhileDummyStart!=null) WhileDummyStart.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(WhileDummyEnd!=null) WhileDummyEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(WhileDummyConditionStart!=null) WhileDummyConditionStart.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(WhileDummyStart!=null) WhileDummyStart.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(WhileDummyEnd!=null) WhileDummyEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("WhileSt(\n");

        if(WhileDummyConditionStart!=null)
            buffer.append(WhileDummyConditionStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(WhileDummyStart!=null)
            buffer.append(WhileDummyStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(WhileDummyEnd!=null)
            buffer.append(WhileDummyEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [WhileSt]");
        return buffer.toString();
    }
}
