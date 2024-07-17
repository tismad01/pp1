// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class SimpleDesignator extends Designator {

    private String designName;

    public SimpleDesignator (String designName) {
        this.designName=designName;
    }

    public String getDesignName() {
        return designName;
    }

    public void setDesignName(String designName) {
        this.designName=designName;
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
        buffer.append("SimpleDesignator(\n");

        buffer.append(" "+tab+designName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SimpleDesignator]");
        return buffer.toString();
    }
}
