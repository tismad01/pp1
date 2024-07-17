package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;


public class Tab1 {
    static Struct boolType = new Struct(Struct.Bool); 
    public static void init() {
        Tab.insert(Obj.Type, "bool", Tab1.boolType);
    }
}
