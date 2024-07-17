// generated with ast extension for cup
// version 0.8
// 17/0/2023 15:25:58


package rs.ac.bg.etf.pp1.ast;

public class MultipleFormPars extends FormParsList {

    private FormDecl FormDecl;
    private FormParsList FormParsList;

    public MultipleFormPars (FormDecl FormDecl, FormParsList FormParsList) {
        this.FormDecl=FormDecl;
        if(FormDecl!=null) FormDecl.setParent(this);
        this.FormParsList=FormParsList;
        if(FormParsList!=null) FormParsList.setParent(this);
    }

    public FormDecl getFormDecl() {
        return FormDecl;
    }

    public void setFormDecl(FormDecl FormDecl) {
        this.FormDecl=FormDecl;
    }

    public FormParsList getFormParsList() {
        return FormParsList;
    }

    public void setFormParsList(FormParsList FormParsList) {
        this.FormParsList=FormParsList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormDecl!=null) FormDecl.accept(visitor);
        if(FormParsList!=null) FormParsList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormDecl!=null) FormDecl.traverseTopDown(visitor);
        if(FormParsList!=null) FormParsList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormDecl!=null) FormDecl.traverseBottomUp(visitor);
        if(FormParsList!=null) FormParsList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleFormPars(\n");

        if(FormDecl!=null)
            buffer.append(FormDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParsList!=null)
            buffer.append(FormParsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleFormPars]");
        return buffer.toString();
    }
}
