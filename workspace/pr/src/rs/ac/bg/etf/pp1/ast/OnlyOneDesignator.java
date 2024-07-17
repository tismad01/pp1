// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class OnlyOneDesignator extends MoreDes {

    private DesList DesList;

    public OnlyOneDesignator (DesList DesList) {
        this.DesList=DesList;
        if(DesList!=null) DesList.setParent(this);
    }

    public DesList getDesList() {
        return DesList;
    }

    public void setDesList(DesList DesList) {
        this.DesList=DesList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesList!=null) DesList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesList!=null) DesList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesList!=null) DesList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OnlyOneDesignator(\n");

        if(DesList!=null)
            buffer.append(DesList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OnlyOneDesignator]");
        return buffer.toString();
    }
}
