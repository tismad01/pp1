// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class NumVal extends ConstAssign {

    private String constName;
    private Integer numbeValue;

    public NumVal (String constName, Integer numbeValue) {
        this.constName=constName;
        this.numbeValue=numbeValue;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public Integer getNumbeValue() {
        return numbeValue;
    }

    public void setNumbeValue(Integer numbeValue) {
        this.numbeValue=numbeValue;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NumVal(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        buffer.append(" "+tab+numbeValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumVal]");
        return buffer.toString();
    }
}
