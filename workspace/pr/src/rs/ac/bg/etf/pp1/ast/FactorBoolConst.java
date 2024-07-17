// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class FactorBoolConst extends Factor {

    private Boolean boolConst;

    public FactorBoolConst (Boolean boolConst) {
        this.boolConst=boolConst;
    }

    public Boolean getBoolConst() {
        return boolConst;
    }

    public void setBoolConst(Boolean boolConst) {
        this.boolConst=boolConst;
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
        buffer.append("FactorBoolConst(\n");

        buffer.append(" "+tab+boolConst);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorBoolConst]");
        return buffer.toString();
    }
}
