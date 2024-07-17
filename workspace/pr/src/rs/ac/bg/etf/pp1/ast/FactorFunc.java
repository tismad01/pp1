// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class FactorFunc extends Factor {

    private FunctionDesignator FunctionDesignator;
    private ActPars ActPars;

    public FactorFunc (FunctionDesignator FunctionDesignator, ActPars ActPars) {
        this.FunctionDesignator=FunctionDesignator;
        if(FunctionDesignator!=null) FunctionDesignator.setParent(this);
        this.ActPars=ActPars;
        if(ActPars!=null) ActPars.setParent(this);
    }

    public FunctionDesignator getFunctionDesignator() {
        return FunctionDesignator;
    }

    public void setFunctionDesignator(FunctionDesignator FunctionDesignator) {
        this.FunctionDesignator=FunctionDesignator;
    }

    public ActPars getActPars() {
        return ActPars;
    }

    public void setActPars(ActPars ActPars) {
        this.ActPars=ActPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FunctionDesignator!=null) FunctionDesignator.accept(visitor);
        if(ActPars!=null) ActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FunctionDesignator!=null) FunctionDesignator.traverseTopDown(visitor);
        if(ActPars!=null) ActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FunctionDesignator!=null) FunctionDesignator.traverseBottomUp(visitor);
        if(ActPars!=null) ActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorFunc(\n");

        if(FunctionDesignator!=null)
            buffer.append(FunctionDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActPars!=null)
            buffer.append(ActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorFunc]");
        return buffer.toString();
    }
}
